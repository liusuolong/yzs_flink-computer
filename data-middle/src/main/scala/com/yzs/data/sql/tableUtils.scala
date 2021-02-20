package com.yzs.data.sql

import com.yzs.data.sql.systemTable.{ColumnArray, ColumnType, KeyColumnArray}
import kafka.utils.json.JsonObject
import com.alibaba.fastjson.JSONObject
import com.yzs.data.sql.tableList.columnTypeObject

import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.collection.mutable.ArrayBuffer


object tableUtils extends Serializable {


  /**
   * 获取数据库名
   *
   * @param tableName
   * @return
   */
  def getDatabase(tableName: String): String = {
    //val temp = getTableClass(tableName)
    /* temp match {
       case 0 => job_status_trace_log.columnTypeMap.get(ColumnName).toString
       case 1 => job_execution_log.columnTypeMap.get(ColumnName).toString
       case 0 => driver_posting.columnTypeMap.get(ColumnName).toString
       case 1=> truck_order_container.columnTypeMap.get(ColumnName).toString
       case 2 => truck_order.columnTypeMap.get(ColumnName).toString
       case 3 => user.columnTypeMap.get(ColumnName).toString
       case 4 => driver_vip_application.columnTypeMap.get(ColumnName).toString
       case 5 => belonged_driver.columnTypeMap.get(ColumnName).toString

       case _ => ""
     }*/
    var databaseTemp = ""
    databaseTemp = columnTypeObject.getString(tableName)
    databaseTemp
  }


  /**
   * 判断表字段是否存在
   *
   * @param conn
   * @param database
   * @param tableName
   * @param columnName
   * @param mysqlType
   * @return
   */
  def isExistColumns(conn: Connection, database: String, tableName: String, columnName: String, mysqlType: JSONObject): String = {
    var ClickHouseColumnType: String = "";
    val CKTypeStatement = conn.prepareStatement(ColumnType);
    try {
      CKTypeStatement.setString(1, database)
      CKTypeStatement.setString(2, tableName)
      CKTypeStatement.setString(3, columnName)
      val rs = CKTypeStatement.executeQuery()
      if (rs.next()) {
        ClickHouseColumnType = rs.getString(1)
      } else {
        //没有字段进行类型匹配
        val ClickHouseColumnType = getTypeChange(mysqlType, columnName)
        //没有字段CK自动增加
        getAddColumn(conn, database, tableName, columnName, ClickHouseColumnType)
      }
      ClickHouseColumnType
    } finally {
      CKTypeStatement.close()
    }
  }

  /**
   * 增加字段
   *
   * @param conn                 连接
   * @param database             库名
   * @param tableName            表名
   * @param columnName           列名
   * @param ClickHouseColumnType 列类型
   */
  def getAddColumn(conn: Connection, database: String, tableName: String, columnName: String, ClickHouseColumnType: String): Unit = {

    val AddSql = "alter table " + database + "." + tableName + " add column " + columnName + " " + ClickHouseColumnType + ";"
    val AddState = conn.prepareStatement(AddSql)
    try {
      AddState.executeUpdate()
    } finally {
      AddState.close()
    }
  }

 /* /**
   * 删除字段问题 直接将字段的值 0 => 1
   *
   * @param conn
   * @param database
   * @param tableName
   * @param ck_is_status
   * @param ID
   */
  def deleteColumn(conn: Connection, database: String, tableName: String, ck_is_status: Int, ID: Int): Unit = {
    val deleteSql = "alter table " + database + "." + tableName + " update " + ck_is_status  + "where" + ID + ";"
    val deleteState = conn.prepareStatement(deleteSql)
    try {
      deleteState.executeUpdate()
    } finally {
      deleteState.close()
    }
  }*/

  /**
   * mysql与ck数据类型问题
   *
   * @param mysqlType
   * @param columnName
   * @return
   */
  def getTypeChange(mysqlType: JSONObject, columnName: String): String = {
    val mysqlColumnType = mysqlType.getString(columnName)
    var ClickHouseColumnType: String = "";

    if (mysqlColumnType.contains("int")) {
      ClickHouseColumnType = "Int32"
    } else if (mysqlColumnType.contains("Date")) {
      ClickHouseColumnType = "Date"
    } else if (mysqlColumnType.contains("datetime")) {
      ClickHouseColumnType = "Datetime"
    } else if (mysqlColumnType.contains("decimal") ||
      mysqlColumnType.contains("double")) {
      ClickHouseColumnType = "Float"
    } else {
      ClickHouseColumnType = "String"
    }
    ClickHouseColumnType
  }

  def getColumns(conn: Connection, database: String, tableName: String): Array[String] = {
    var columnsTemp: ArrayBuffer[String] = ArrayBuffer()
    val statement = conn.prepareStatement(ColumnArray)
    try {
      statement.setString(1, database)
      statement.setString(2, tableName)
      val rs = statement.executeQuery()
      while (rs.next()) {
        columnsTemp += rs.getString(1)
      }
    } finally {
      statement.close()
    }

    columnsTemp.toArray
  }

  def getInsertSql(tableName: String): String = {
    val getInsertSql = " INSERT INTO yzs_src." + tableName
    getInsertSql
  }


  def getUpdateSql(tableName: String): String = {
    val getUpdateSql = "alter table  yzs_src." + tableName + " update "
    getUpdateSql
  }

  def getKeyColumns(conn: Connection, database: String, tableName: String): Array[String] = {
    var columnsTemp: ArrayBuffer[String] = ArrayBuffer();
    val statement = conn.prepareStatement(KeyColumnArray);
    try {
      statement.setString(1, database)
      statement.setString(2, tableName)
      val rs = statement.executeQuery()
      while (rs.next()) {
        columnsTemp += rs.getString(1)
      }
    } finally {
      statement.close()
    }

    columnsTemp.toArray
  }


  /* def getTableClass(tableName: String): Int = {
     var temp: Int = 0
     for (entry <- 0 to tableListArray.length - 1) {
       if (tableListArray(entry).toString().contains(tableName)) {
         temp = entry
       }
     }
     temp
   }*/


}
