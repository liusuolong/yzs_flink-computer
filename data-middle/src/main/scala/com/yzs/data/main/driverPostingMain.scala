package com.yzs.data.main


import java.sql.Connection

import com.alibaba.fastjson.{JSON, JSONObject}
import com.yzs.data.common.{clickHouseInsert, clickHouseUpdate}
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import com.yzs.data.common.configUtil._
import com.yzs.data.sql.tableUtils
import com.yzs.data.utils.clickHousePoolUtil
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.log4j.{Level, Logger}


object driverPostingMain {
  private val driverPostingMainLog = Logger.getLogger(driverPostingMain.getClass.getName)

  def main(args: Array[String]): Unit = {
    @transient
    //   var conn: Connection = null
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val insert = clickHouseInsert.getClickHouseInsertSingleton()
    val update = clickHouseUpdate.getClickHouseUpdateSingleton()

    val groupName = "driverPostingMain2"
    //隐式转换
    import org.apache.flink.api.scala._
    //checkpoint配置
    env.enableCheckpointing(5000);
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500);
    env.getCheckpointConfig.setCheckpointTimeout(60000);
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1);
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

    //设置statebackend
    //  env.setStateBackend(new RocksDBStateBackend("hdfs://47.103.34.147:9000/flink/checkpoints",true));

    val myConsumer = kafkaUtil.getDirectStream(env, groupName)
    driverPostingMainLog.info("执行INSERT before==============")

    //指定偏移量
    //myConsumer.setStartFromEarliest

    val source = env.addSource(myConsumer)
    // source.print()
    source.setParallelism(5).filter(line => {
      dealTableFilter(dealParseObject(line))

    }).map(
      temp => {
        // conn = ckPoolUtil.getConn
        val conn = ckPoolUtil.getConn()
        try {

          val line = dealParseObject(temp)
          val tableTemp = line.getString("table")
          val execTemp = line.getString("type")
          val tableKeyColumns = tableUtils.getKeyColumns(tableTemp)
          val tableGetColumns = tableUtils.getColumns(tableTemp)
          val tableInsertSql = tableUtils.getInsertSql(tableTemp)
          val tableGetUpdateSql = tableUtils.getUpdateSql(tableTemp)
          val newDataTemp = dealParseObject(line.getString("sqlType"))
          driverPostingMainLog.info("执行INSERT before==============")

          execTemp match {
            case "INSERT" => {
              driverPostingMainLog.info("执行INSERT==============")
              insert.insertJobStatusTraceLog(conn, tableInsertSql, tableTemp, tableGetColumns, newDataTemp)


            }
            case "UPDATE" => {
              driverPostingMainLog.info("执行Update==============")
              val oldDataTemp = line.getJSONArray("old").getJSONObject(0)
                     update.updateJobStatusTraceLog(conn, tableGetUpdateSql,tableTemp,newDataTemp,oldDataTemp, tableKeyColumns)
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

    )

    //val value = mapFliter.keyBy("reduce").timeWindow(Time.seconds(2))

    //   source.timeWindowAll(Time.seconds(2),Time.seconds(1))
    env.execute("driverPostingMain")


  }

  def dealTableFilter(line: JSONObject): Boolean = {

    var filterDataBoolean = true

    val table: String = line.getString("table")
    //"com.yzs.data.sql.job_status_trace_log"
    // com.yzs.data.sql.job_status_trace_log
    if (tableUtils.tableListArray.contains("com.yzs.data.sql." + table)) {
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
