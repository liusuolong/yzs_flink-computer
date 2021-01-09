package com.yzs.data.common


import com.yzs.data.utils.{checkPointUtil, clickHousePoolUtil, kafkaDealUtil}

object configUtil extends Serializable {
  var configFilePath = "application.properties"
//  lazy val config = PropertiesUtil.getProperties(configFilePath)

  var kafkaFilePath = "online/kafka.properties"
  var clickHousePath="online/clickhouse.properties"
  var checkPonintPath="online/checkPoint.properties"
  var sysDeployment = System.getProperty("STREAMING_ENV")
 //  var sysDeployment = System.getProperty(System.getenv("STREAMING_ENV"))

  println("....ConfigUtil..............." + sysDeployment)
  if (null != sysDeployment && "dev".equals(sysDeployment)) {
    kafkaFilePath = "dev/kafka.properties"
    checkPonintPath="dev/checkPoint.properties"
    clickHousePath="dev/clickhouse.properties"
  }
  lazy val kafkaUtil = kafkaDealUtil.getKafkaUtilSingleton(kafkaFilePath)
 // lazy val clickHouse = clickHouseUtil.getClickHouseUtil(clickHousePath)
  lazy val ckPoolUtil = clickHousePoolUtil.getClickHousePoolUtil(clickHousePath)
 // lazy val tableListUtil = tableList.gettableListSingleton
 lazy val checkPointUtils = checkPointUtil.getCheckPointUtilSingleton(checkPonintPath)
}
