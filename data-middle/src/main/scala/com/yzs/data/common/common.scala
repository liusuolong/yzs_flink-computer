/*
package com.yzs.data.common

import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.runtime.state.filesystem.FsStateBackend

object common {


  def setCheckpointConfig(env: StreamExecutionEnvironment): Unit = {
    //checkpoint配置
    env.enableCheckpointing(5000); //检查点间隔1000ms
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE); //set mode to exactly-once (this is the default)设置模式
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500); //确保检查点之间有至少500 ms的间隔【checkpoint最小间隔】
    env.getCheckpointConfig.setCheckpointTimeout(60000); //检查点必须在一分钟内完成，或者被丢弃【checkpoint的超时时间】
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1); //同一时间只允许进行一个检查点

    import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
    import java.io.IOException
    //设置statebackend
    try (

      env.setStateBackend(new FsStateBackend("file:///D:\\linux\\checkpoint"))
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
*/
