package com.yzs.data.utils

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

class kafkaDealUtil(filePath: String) extends Serializable {
  val properties = PropertiesUtil.getProperties(filePath)
  println(properties)

  //配置kafka
  val topic = properties.getProperty("topic")

  val group = Set(properties.getProperty("group.id"))
  val brokers = properties.getProperty("brokers")
  val serializerClass = properties.getProperty("serializerClass")
  val offset = properties.getProperty("auto.offset.reset")
  val zkHost = properties.getProperty("zookeeper.connect")
  val enableAuto = properties.getProperty("enable.auto.commit")

  def getDirectStream(env: StreamExecutionEnvironment, groupName: String) = {
    val prop = new Properties()
    prop.setProperty("bootstrap.servers", brokers)
    prop.setProperty("group.id", groupName)
    val myConsumer = new FlinkKafkaConsumer011[String](topic.toString(), new SimpleStringSchema(), prop)
    myConsumer
  }


  def getKafkaUtilSingleton(filePath: String): kafkaDealUtil = {
    new kafkaDealUtil(filePath)
  }
}


object kafkaDealUtil extends Serializable {
  def getKafkaUtilSingleton(filePath: String): kafkaDealUtil = {
    new kafkaDealUtil(filePath)
  }
}
