package com.yzs.data.common


import com.yzs.data.utils.{checkPointUtil, clickHousePoolUtil, kafkaDealUtil}

object configUtil extends Serializable {
  var configFilePath = "application.properties"
//  lazy val config = PropertiesUtil.getProperties(configFilePath)

  var kafkaFilePath = "dev/kafka.properties"
  var clickHousePath="dev/clickhouse.properties"
  var checkPonintPath="dev/checkPoint.properties"
  var sysDeployment = System.getProperty("STREAMING_ENV")
 //  var sysDeployment = System.getProperty(System.getenv("STREAMING_ENV"))

  println("....ConfigUtil..............." + sysDeployment)
  if (null != sysDeployment && "online".equals(sysDeployment)) {
    kafkaFilePath = "online/kafka.properties"
    checkPonintPath="online/checkPoint.properties"
    clickHousePath="online/clickhouse.properties"
  }
  lazy val kafkaUtil = kafkaDealUtil.getKafkaUtilSingleton(kafkaFilePath)
 // lazy val clickHouse = clickHouseUtil.getClickHouseUtil(clickHousePath)
  lazy val ckPoolUtil = clickHousePoolUtil.getClickHousePoolUtil(clickHousePath)
 // lazy val tableListUtil = tableList.gettableListSingleton
 lazy val checkPointUtils = checkPointUtil.getCheckPointUtilSingleton(checkPonintPath)
}
