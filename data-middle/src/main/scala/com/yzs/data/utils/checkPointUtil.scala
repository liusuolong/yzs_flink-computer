package com.yzs.data.utils

import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import java.io.IOException
import java.util.concurrent.TimeUnit

import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.common.time.Time

class checkPointUtil(filePath: String) extends Serializable {
  val properties = PropertiesUtil.getProperties(filePath)
  println(properties)

  //配置kafka
  val interval = properties.getProperty("Checkpointing.interval").toInt

  val setMinPauseBetweenCheckpoints = properties.getProperty("Checkpointing.setMinPauseBetweenCheckpoints").toInt
  val setCheckpointTimeout = properties.getProperty("Checkpointing.setCheckpointTimeout").toInt
  val setMaxConcurrentCheckpoints = properties.getProperty("Checkpointing.setMaxConcurrentCheckpoints").toInt
  val dataUrl = properties.getProperty("Checkpointing.dataUrl")

  def setCheckpointConfig(env: StreamExecutionEnvironment): Unit = {
    //checkpoint配置
    env.enableCheckpointing(interval); //检查点间隔1000ms
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE); //set mode to exactly-once (this is the default)设置模式
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(setMinPauseBetweenCheckpoints); //确保检查点之间有至少500 ms的间隔【checkpoint最小间隔】
    env.getCheckpointConfig.setCheckpointTimeout(setCheckpointTimeout); //检查点必须在一分钟内完成，或者被丢弃【checkpoint的超时时间】
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(setMaxConcurrentCheckpoints); //同一时间只允许进行一个检查点

    //env.enableCheckpointing(1000L,CheckpointingMode.AT_LEAST_ONCE)
     env.setRestartStrategy(RestartStrategies.noRestart())
    env.getCheckpointConfig.enableExternalizedCheckpoints(
      CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    env.getCheckpointConfig.setFailOnCheckpointingErrors(true)
    //重启策略
    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
      3, // 尝试重启的次数
      Time.of(10, TimeUnit.SECONDS) // 间隔
    ));
    //设置statebackend
    try (

      env.setStateBackend(new FsStateBackend(dataUrl))
      // env.setStateBackend(new RocksDBStateBackend("hdfs://node1.hadoop:9000/flink/checkpoint", true))
      ) catch {
      case e: IOException =>
        e.printStackTrace()
    }

    //表示一旦Flink处理程序被cancel后，会保留Checkpoint数据，以便根据实际需要恢复到指定的Checkpoint
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);


    //设置statebackend
    //  env.setStateBackend(new MemoryStateBackend());
    //  env.setStateBackend(new RocksDBStateBackend("hdfs://47.103.34.147:9000/flink/checkpoints",true));
  }
}

object checkPointUtil extends Serializable {
  def getCheckPointUtilSingleton(filePath: String): checkPointUtil = {
    new checkPointUtil(filePath)
  }
}