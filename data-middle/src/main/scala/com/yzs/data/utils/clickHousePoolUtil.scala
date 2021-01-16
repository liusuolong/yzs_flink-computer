package com.yzs.data.utils

import java.sql.{Connection, PreparedStatement, ResultSet, SQLException, Statement}
import java.util
import java.util.Properties
import javax.sql.DataSource
import org.apache.log4j.Logger

import java.util.concurrent.ConcurrentHashMap
//import com.jolbox.bonecp.{ BoneCPConfig, BoneCP }
import java.util.ResourceBundle
import java.util.LinkedList
import java.sql.DriverManager
import java.sql.Connection

/**
 * 数据库连接池工具类
 * 语言：scala
 * 时间：2016-07-09
 */
class clickHousePoolUtil(filePath:String) {
  val properties = PropertiesUtil.getProperties(filePath)
  println(properties)

  private val max_connection = properties.getProperty("clickhouse.max_connection") //连接池总数
  private val connection_num = properties.getProperty("clickhouse.connection_num") //产生连接数
  private var current_num = 0 //当前连接池已产生的连接数
  private val pools = new LinkedList[Connection]() //连接池
  private val driver = properties.getProperty("clickhouse.driver")
  private val url = properties.getProperty("clickhouse.url")
  private val username = properties.getProperty("clickhouse.username")
  private val password = properties.getProperty("clickhouse.password")

  /**
   * 加载驱动
   */
  private def before() {
    if (current_num > max_connection.toInt && pools.isEmpty()) {
      print("busyness")
      Thread.sleep(2000)
      before()
    } else {
      Class.forName(driver)
    }
  }
  /**
   * 获得连接
   */
  private def initConn(): Connection = {
    val conn = DriverManager.getConnection(url, username, password)
    conn
  }
  /**
   * 初始化连接池
   */
  private def initConnectionPool(): LinkedList[Connection] = {
    AnyRef.synchronized({
      if (pools.isEmpty()) {
        before()
        for (i <- 1 to connection_num.toInt) {
          pools.push(initConn())
          current_num += 1
        }
      }
      pools
    })
  }
  /**
   * 获得连接
   */
  def getConn():Connection={
    initConnectionPool()
    pools.poll()
  }
  /**
   * 释放连接
   */
  def closeConnection(con:Connection){
    pools.push(con)
  }

  def closeStatement(statement:PreparedStatement){
    statement.close()
  }


}
object clickHousePoolUtil  {


  def getClickHousePoolUtil(filePath:String): clickHousePoolUtil = {
    new clickHousePoolUtil(filePath)
  }

}
