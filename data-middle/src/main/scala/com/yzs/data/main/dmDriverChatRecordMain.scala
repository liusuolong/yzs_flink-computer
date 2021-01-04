package com.yzs.data.main

import com.yzs.data.common.configUtil.{checkPointUtils, kafkaUtil}
import com.yzs.data.common.{clickHouseInsert, clickHouseUpdate}
import com.yzs.data.main.driverPostingMain.groupName
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.log4j.Logger

object dmDriverChatRecordMain {
  final val driverPostingMainLog = Logger.getLogger(dmDriverChatRecordMain.getClass.getName)
  final val insert = clickHouseInsert.getClickHouseInsertSingleton()
  final val update = clickHouseUpdate.getClickHouseUpdateSingleton()
  val groupName = "dmDriverChatRecord"

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

//    setCheckpointConfig(env)
    checkPointUtils.setCheckpointConfig(env)
    val dmDriverChatRecord = kafkaUtil.getDirectStream(env, groupName)

    //myConsumer.setStartFromEarliest
    val source = env.addSource(dmDriverChatRecord)
    source.print()
    env.execute("driverPostingMain")
  }

}
