package com.yzs.data.common

import java.sql.{Connection, PreparedStatement}
import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSONObject
import com.yzs.data.common.configUtil.ckPoolUtil
import com.yzs.data.sql.tableUtils
import com.yzs.data.utils.CommonUtil.{dealDefaultValue, transDefault}
import com.yzs.data.utils.DateUtil.toTimeStamp2Date

import scala.collection.JavaConversions._

class clickHouseInsert extends Serializable {


  def insertDataDeal(conn: Connection, sql: String, tableTemp: String,
                     tableGetColumns: Array[String], newDataTemp: JSONObject): Unit = {
    //(STATUS , CREATION_TIME ) values(?,?)
    val sqlInsert = sql + insertSqlValuesSplit(newDataTemp)
    println("sqlInsert=================" + sqlInsert)
    var indexTemp = 1
    var typeTemp: String = null
    val prepareState = conn.prepareStatement(sqlInsert)
    var columnNameTemp: String = ""
    var columnValueTemp: String = ""
    try {
      for (entry <- newDataTemp.entrySet()) {
        columnNameTemp = entry.getKey
        columnValueTemp = transDefault(entry.getValue)
        typeTemp = tableUtils.getColumnsType(tableTemp, columnNameTemp)
        dealColumnsInsertType(columnValueTemp, typeTemp, prepareState, indexTemp)
        indexTemp = indexTemp + 1
      }



      /* val prepareState = conn.prepareStatement(sql)
       try {
         for (i <- 1 to newDataTemp.size()) {
           val columnNameTemp = tableGetColumns(i - 1)
           val typeTemp = tableUtils.getColumnsType(tableTemp, columnNameTemp)

           dealColumnsInsertType(newDataTemp,typeTemp, prepareState, columnNameTemp, i)
         }*/


      println(prepareState.toString)
      prepareState.executeUpdate //执行sql语句
    }

    catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      // ckPoolUtil.closeStatement(prepareState)
      ckPoolUtil.closeConnection(conn)
    }
  }


  def dealColumnsInsertType(columnValueTemp: String, typeTemp: String, prepareState: PreparedStatement, i: Int): Unit = {
    var dataTypeTemp: String = null
    var dataTemp: Any = null
    typeTemp match {
      case "String" | "Some(String)" => {
        prepareState.setString(i, columnValueTemp)
      }
      case "Date" | "Some(Date)" => {
        dataTypeTemp = "Date"
        dataTemp = dealDefaultValue(toTimeStamp2Date(columnValueTemp, "yyyy-MM-dd"), dataTypeTemp)
        prepareState.setString(i, dataTemp.toString)
      }
      case "DateTime" | "Some(DateTime)" => {
        dataTypeTemp = "DateTime"
        dataTemp = dealDefaultValue(toTimeStamp2Date(columnValueTemp, "yyyy-MM-dd HH:mm:ss"), dataTypeTemp)
        prepareState.setString(i, dataTemp.toString)
      }
      case "Int8" | "Int16" | "Int32" | "UInt8" | "UInt16" | "UInt32" |
           "Some(Int8)" | "Some(Int16)" | "Some(Int32)" | "Some(UInt8)" | "Some(UInt16)" | "Some(UInt32)" => {
        //    val Ck = println(11111)
        dataTypeTemp = "Int"
        dataTemp = dealDefaultValue(columnValueTemp, dataTypeTemp)
        prepareState.setInt(i, dataTemp.toString.toInt)
      }
      case "Float" | "Float32" | "Float64" |
           "Some(Float)" | "Some(Float32)" | "Some(Float64)" => {
        //     val Ck = println(11111)
        dataTypeTemp = "Float"
        dataTemp = dealDefaultValue(columnValueTemp, dataTypeTemp)
        prepareState.setDouble(i, dataTemp.toString.toInt)
      }
      case _ => {
        prepareState.setString(i, columnValueTemp)
      }
    }
  }


  /*
  *typeTemp:String
  * prepareState:PreparedStatement
  * columnNameTemp:id
  * id:1
   */
  /*def dealColumnsInsertType(newDataTemp: JSONObject, typeTemp: String, prepareState: PreparedStatement, columnNameTemp: String, i: Int): Unit = {
    typeTemp match {
      case "String" | "Some(String)" => {
        prepareState.setString(i, newDataTemp.getString(columnNameTemp))
      }
      case "Date" | "Some(Date)" => {
        val dateTemp = toTimeStamp2Date(newDataTemp.getString(columnNameTemp), "yyyy-MM-dd")
        prepareState.setString(i, dateTemp)
      }
      case "DateTime" | "Some(DateTime)" => {
        val timeStampTemp = toTimeStamp2Date(newDataTemp.getString(columnNameTemp), "yyyy-MM-dd HH:mm:ss")
        prepareState.setString(i, timeStampTemp)
      }
      case "Int8" | "Int16" | "Int32" | "UInt8" | "UInt16" | "UInt32" |
           "Some(Int8)" | "Some(Int16)" | "Some(Int32)" | "Some(UInt8)" | "Some(UInt16)" | "Some(UInt32)" => {
        //    val Ck = println(11111)

        prepareState.setInt(i, newDataTemp.getInteger(columnNameTemp))
      }
      case "Float" | "Float32" | "Float64" |
           "Some(Float)" | "Some(Float32)" | "Some(Float64)" => {
        //     val Ck = println(11111)
        prepareState.setFloat(i, newDataTemp.getFloat(columnNameTemp))
      }
      case _ => {
        prepareState.setString(i, newDataTemp.getString(columnNameTemp))
      }
    }
  }
*/
  //{"CREATION_TIME":"2021-01-07 17:12:31","STATUS":"COMPLETED"}
  //(id,job_name,task_id)
  def insertSqlValuesSplit(newDataTemp: JSONObject): String = {
    var sqlNameStringTemp: String = ""

    var dataTemp: String = " values("
    for (entry <- newDataTemp.entrySet()) {
      if (sqlNameStringTemp.equals("")) {
        sqlNameStringTemp = "(" + entry.getKey + ""
        dataTemp = dataTemp + "?"
      } else {
        sqlNameStringTemp = sqlNameStringTemp + " , " + entry.getKey
        dataTemp = dataTemp + ",?"
      }
    }
    sqlNameStringTemp = sqlNameStringTemp + " )" + dataTemp + ")"
    sqlNameStringTemp
  }


  def insertJobStatusTraceLogOld(conn: Connection, sql: String, tableGetColumns: Array[String], sqlType: JSONObject): Unit = {
    val prepareState = conn.prepareStatement(sql)
    try {
      for (i <- 1 to sqlType.size()) {
        prepareState.setString(i, sqlType.getString(tableGetColumns(i - 1)))
      }
      println(prepareState.toString)
      prepareState.executeUpdate //执行sql语句
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      // ckPoolUtil.closeStatement(prepareState)
      ckPoolUtil.closeConnection(conn)
    }
  }
}


object clickHouseInsert {
  def getClickHouseInsertSingleton(): clickHouseInsert = {
    new clickHouseInsert()
  }
}
