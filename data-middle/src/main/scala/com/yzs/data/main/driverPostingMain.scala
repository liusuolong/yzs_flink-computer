package com.yzs.data.main


import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONObject}
import com.yzs.data.common.{clickHouseInsert, clickHouseUpdate}
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import com.yzs.data.common.configUtil._
import com.yzs.data.sql.tableUtils
import com.yzs.data.utils.DateUtil.getSysDateStamp
import com.yzs.data.utils.clickHousePoolUtil
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.runtime.state.memory.MemoryStateBackend
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.log4j.{Level, Logger}
//隐式转换
import org.apache.flink.api.scala._


object driverPostingMain {
  final val driverPostingMainLog = Logger.getLogger(driverPostingMain.getClass.getName)
  final val insert = clickHouseInsert.getClickHouseInsertSingleton()
  final val update = clickHouseUpdate.getClickHouseUpdateSingleton()
  val groupName = "driverPostingMain2"

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //setCheckpointConfig(env)
    checkPointUtils.setCheckpointConfig(env)

    val myConsumer = kafkaUtil.getDirectStream(env, groupName)
    driverPostingMainLog.info("执行INSERT before==============")


    // Flink从topic中指定的group上次消费的位置开始消费，所以必须配置group.id参数
    myConsumer.setStartFromGroupOffsets();

    val source = env.addSource(myConsumer)

    source.setParallelism(4).filter(line => {
      dealTableFilter(dealParseObject(line))

    }).map(
      temp => {
        dealDataConn(temp)
      }
    )

    //val value = mapFliter.keyBy("reduce").timeWindow(Time.seconds(2))

    //   source.timeWindowAll(Time.seconds(2),Time.seconds(1))
    env.execute("driverPostingMain")


  }

  /*

    def setCheckpointConfig(env: StreamExecutionEnvironment): Unit = {
      //checkpoint配置
      env.enableCheckpointing(5000); //检查点间隔1000ms
      env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE); //set mode to exactly-once (this is the default)设置模式
      env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500); //确保检查点之间有至少500 ms的间隔【checkpoint最小间隔】
      env.getCheckpointConfig.setCheckpointTimeout(60000); //检查点必须在一分钟内完成，或者被丢弃【checkpoint的超时时间】
      env.getCheckpointConfig.setMaxConcurrentCheckpoints(1); //同一时间只允许进行一个检查点
      //表示一旦Flink处理程序被cancel后，会保留Checkpoint数据，以便根据实际需要恢复到指定的Checkpoint
      env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

      //设置statebackend
      //  env.setStateBackend(new MemoryStateBackend());
      //  env.setStateBackend(new RocksDBStateBackend("hdfs://47.103.34.147:9000/flink/checkpoints",true));
    }
  */


  def dealDataConn(temp: String): Unit = {

    // conn = ckPoolUtil.getConn
    val conn = ckPoolUtil.getConn()
    try {
      println(getSysDateStamp() + "ALL============" + temp)
      val line = dealParseObject(temp)
      val tableTemp = line.getString("table")
      val execTemp = line.getString("type")
      val tableKeyColumns = tableUtils.getKeyColumns(tableTemp)
      val tableGetColumns = tableUtils.getColumns(tableTemp)
      val tableInsertSql = tableUtils.getInsertSql(tableTemp)
      val tableGetUpdateSql = tableUtils.getUpdateSql(tableTemp)
      //  val newDataTemp = dealParseObject(line.getString("data"))
      val newDataTemp = line.getJSONArray("data").getJSONObject(0)
      println(getSysDateStamp() + "NEW===================" + newDataTemp)

      // driverPostingMainLog.info("执行INSERT before==============")

      execTemp match {
        case "INSERT" => {
          //driverPostingMainLog.info("执行INSERT==============")
          insert.insertDataDeal(conn, tableInsertSql, tableTemp, tableGetColumns, newDataTemp)


        }
        case "UPDATE" => {
          // driverPostingMainLog.info("执行Update==============")
          val oldDataTemp = line.getJSONArray("old").getJSONObject(0)
          println(getSysDateStamp() + "old=============== " + oldDataTemp)
          update.updateDataDeal(conn, tableGetUpdateSql, tableTemp, newDataTemp, oldDataTemp, tableKeyColumns)
        }
        case _ => ""
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      // ckPoolUtil.closeConnection(conn)
      ckPoolUtil.closeConnection(conn)

    }
  }

  def dealTableFilter(line: JSONObject): Boolean = {

    var filterDataBoolean = true
    val table: String = line.getString("table")
    //"com.yzs.data.sql.job_status_trace_log"
    // com.yzs.data.sql.job_status_trace_log
    if (tableUtils.tableListArray.contains("com.yzs.data.sql." + table)) {
      //if (table.equals("driver_vip_application")) {

      filterDataBoolean = true
    } else {
      filterDataBoolean = false
    }
    filterDataBoolean
  }

  def dealParseObject(line: String): JSONObject = {
    //  println("原始接收到的数据："+line)

    driverPostingMainLog.info(line)
    JSON.parseObject(line)

  }


}
