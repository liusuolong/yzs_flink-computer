package com.yzs.data.sql

import com.yzs.data.sql.systemTable.{ColumnArray, ColumnType, KeyColumnArray}
import kafka.utils.json.JsonObject
import com.alibaba.fastjson.JSONObject

import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.collection.mutable.ArrayBuffer


object tableUtils extends Serializable {


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
    databaseTemp = tableList.columnTypeMap.get(tableName).toString
    databaseTemp
  }


  def getColumnsType(conn: Connection, database: String, tableName: String, columnName: String, mysqlType: JSONObject): String = {
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

  def getAddColumn(conn: Connection, database: String, tableName: String, columnName: String,ClickHouseColumnType:String): Unit ={

    val AddSql="alter table "+database+"."+tableName+ " add column "+ columnName+" "+ClickHouseColumnType+";"
    val AddState = conn.prepareStatement(AddSql)
   try {
    AddState.executeUpdate()
   }finally {
     AddState.close()
   }
  }

  def getTypeChange(mysqlType:JSONObject,columnName:String): String ={
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
    var columnsTemp: ArrayBuffer[String] = ArrayBuffer();
    val statement = conn.prepareStatement(ColumnArray);
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
