package com.yzs.data.utils

import java.sql.{Connection, ResultSet, SQLException, Statement}
import java.util.Properties

import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource
import org.apache.log4j.Logger

class clickHousePoolUtil(filePath:String) {

  private val LOG = Logger.getLogger(clickHousePoolUtil.getClass.getName)
  println(filePath)
  val dataSource: Option[DataSource] = {
    try {
      val druidProps = new Properties()
      // 获取Druid连接池的配置文件
      val druidConfig = getClass().getClassLoader().getResourceAsStream(filePath)
      // 倒入配置文件
      druidProps.load(druidConfig)
      Some(DruidDataSourceFactory.createDataSource(druidProps))
    } catch {
      case error: Exception =>
        LOG.error("Error Create Click Connection", error)
        None
    }
  }

  /**
   * 得到数据源
   */
  def  getDataSource():DataSource= {
    return dataSource.get
  }

  // 连接方式
  def getConnection(): Option[Connection] = {
    dataSource match {
      case Some(ds) => Some(ds.getConnection())
      case None => None
    }
  }

  /**
   * 得到连接对象
   */
  def getConn: Connection = {
    try {
      return getConnection().get
    } catch {
      case e: SQLException => throw new RuntimeException(e)
    }
  }

  def executeStatement(conn: Connection, sql: String, args: Array[Any]): Unit = {
    var preparedStatement = conn.prepareStatement(sql)
    try {
      for (index <- 1 to args.length) {
        preparedStatement.setObject(index, args(index - 1))
      }
      preparedStatement.executeUpdate()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      closeStatement(preparedStatement)
    }
  }

  /**
   * 释放资源
   */
  def close(conn: Connection, stmt: Statement, rs: ResultSet) = {
    if (rs != null) {
      try {
        rs.close();
      } catch {
        case e: SQLException => e.printStackTrace()
      }
    }
    if (stmt != null) {
      try {
        stmt.close();
      } catch {
        case e: SQLException => e.printStackTrace()
      }
    }
    if (conn != null) {
      try {
        conn.close();
      } catch {
        case e: SQLException => e.printStackTrace()
      }
    }
  }

  def closeStatement(stmt: Statement) = {
    if (stmt != null) {
      try {
        stmt.close();
      } catch {
        case e: SQLException => e.printStackTrace()
      }
    }
  }

  def closeConnection(conn: Connection) = {
    if (conn != null) {
      try {
        conn.close();
      } catch {
        case e: SQLException => e.printStackTrace()
      }
    }
  }
  def close(conn: Connection, stmt: Statement) {
    close(conn, stmt, null);
  }
}

object clickHousePoolUtil  {


  def getClickHousePoolUtil(filePath:String): clickHousePoolUtil = {
    new clickHousePoolUtil(filePath)
  }

}
