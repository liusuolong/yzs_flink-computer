package com.yzs.data.common

import java.sql.{Connection, PreparedStatement}
import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSONObject
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time
import com.yzs.data.common.configUtil.ckPoolUtil
import com.yzs.data.sql.tableUtils
import com.yzs.data.utils.DateUtil
import jdk.nashorn.internal.objects.Global.setDate

class clickHouseInsert extends Serializable {

  def insertJobStatusTraceLog(conn: Connection, sql: String, tableTemp: String,
                              tableGetColumns: Array[String], sqlType: JSONObject): Unit = {
    val prepareState = conn.prepareStatement(sql)
    try {
      for (i <- 1 to sqlType.size()) {
        val columnNameTemp = tableGetColumns(i - 1)
        val typeTemp = tableUtils.getColumnsType(tableTemp, columnNameTemp)
        /*   typeTemp match {
             case "String" | "Some(String)" => {
               prepareState.setString(i, sqlType.getString(columnNameTemp))
             }
             case "Date" |"Some(Date)"  => {
               prepareState.setDate(i, sqlType.getSqlDate(columnNameTemp))
             }
             case "DateTime"|"Some(DateTime)" => {
               prepareState.setTimestamp(i, sqlType.getTimestamp(columnNameTemp))
             }
             case "Int8" | "Int16" | "Int32" | "UInt8" | "UInt16" | "UInt32" |
                  "Some(Int8)" | "Some(Int16)" | "Some(Int32)" | "Some(UInt8)" | "Some(UInt16)" | "Some(UInt32)" => {
               val  Ck =println(11111)

               prepareState.setInt(i, sqlType.getInteger(columnNameTemp))
             }
             case "Float" | "Float32" | "Float64"|
                  "Some(Float)" | "Some(Float32)" | "Some(Float64)"=> {
              val  Ck =println(11111)
               prepareState.setFloat(i, sqlType.getFloat(columnNameTemp))
             }
             case _ => {
               prepareState.setString(i, sqlType.getString(columnNameTemp))
             }
           }*/
        dealColumnsInsertType(sqlType,typeTemp, prepareState, columnNameTemp, i)
      }

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

  /*
  *typeTemp:String
  * prepareState:PreparedStatement
  * columnNameTemp:id
  * id:1
   */
  def dealColumnsInsertType( sqlType: JSONObject,typeTemp: String, prepareState: PreparedStatement, columnNameTemp: String, i: Int): Unit = {
    typeTemp match {
      case "String" | "Some(String)" => {
        prepareState.setString(i, sqlType.getString(columnNameTemp))
      }
      case "Date" | "Some(Date)" => {
        prepareState.setDate(i, sqlType.getSqlDate(columnNameTemp))
      }
      case "DateTime" | "Some(DateTime)" => {
        prepareState.setTimestamp(i, sqlType.getTimestamp(columnNameTemp))
      }
      case "Int8" | "Int16" | "Int32" | "UInt8" | "UInt16" | "UInt32" |
           "Some(Int8)" | "Some(Int16)" | "Some(Int32)" | "Some(UInt8)" | "Some(UInt16)" | "Some(UInt32)" => {
        val Ck = println(11111)

        prepareState.setInt(i, sqlType.getInteger(columnNameTemp))
      }
      case "Float" | "Float32" | "Float64" |
           "Some(Float)" | "Some(Float32)" | "Some(Float64)" => {
        val Ck = println(11111)
        prepareState.setFloat(i, sqlType.getFloat(columnNameTemp))
      }
      case _ => {
        prepareState.setString(i, sqlType.getString(columnNameTemp))
      }
    }
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
