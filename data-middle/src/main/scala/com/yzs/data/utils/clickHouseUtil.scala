package com.yzs.data.utils


package com.yzs.data.utils

import java.sql.Statement
import java.sql.Connection
import java.sql.DriverManager

class clickHouseUtil(filePath: String) extends Serializable {
  val properties = PropertiesUtil.getProperties(filePath)
  println(properties)
  //配置kafka
  val forName = properties.getProperty("Class.forName")
  val url = properties.getProperty("url")
  val user = properties.getProperty("user")
  val password = properties.getProperty("password")

  def getClickHouseStatement(): Statement = {
    Class.forName("ru.yandex.clickhouse.ClickHouseDriver")
    //连接
    val connection: Connection = DriverManager.getConnection(url, user, password)
    val statement: Statement = connection.createStatement()
    statement
  }

  def getClickHouseConn(): Connection = {
    Class.forName("ru.yandex.clickhouse.ClickHouseDriver")
    //连接
    val connection: Connection = DriverManager.getConnection(url, user, password)
    connection
  }

  def closeClickHouseConnStat(conn: Connection, stmt: Statement): Unit = {
    if (stmt != null) {
      try {
        stmt.close();
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
    if (conn != null) {
      try {
        conn.close();
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }

  def closeClickHouseStmt(stmt: Statement): Unit = {
    if (stmt != null) {
      try {
        stmt.close();
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }

  }


}


object clickHouseUtil {
  def getClickHouseUtil(filePath: String): clickHouseUtil = {
    new clickHouseUtil(filePath)
  }

  def closeClickHouseConn(conn: Connection): Unit = {
    if (conn != null) {
      try {
        conn.close();
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }
}

