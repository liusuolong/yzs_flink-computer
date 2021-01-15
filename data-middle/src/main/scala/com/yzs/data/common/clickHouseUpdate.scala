package com.yzs.data.common

import java.sql.{Connection, PreparedStatement}

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.collection.JavaConversions._
import com.alibaba.fastjson.JSONObject
import com.yzs.data.common.configUtil.ckPoolUtil
import com.yzs.data.sql.tableUtils
import com.yzs.data.utils.CommonUtil.{dealDefaultValue, transDefault}
import com.yzs.data.utils.DateUtil.toTimeStamp2Date

class clickHouseUpdate extends Serializable {


  def updateDataDeal(conn: Connection, sql: String, tableTemp: String, newDataTemp: JSONObject,
                     oldData: JSONObject, keyName: Array[String]): Unit = {

    //complete_time=?  AND is_success=?

    val newColumnNameTemp = updateSqlSetSplit(oldData)
    val KeyNameTemp = updateSqlWhereSplit(keyName)
    val sqlUpdate = sql + newColumnNameTemp + " where " + KeyNameTemp
    println(sqlUpdate)
    val prepareState = conn.prepareStatement(sqlUpdate)
    var cloNameTemp: String = ""
    var i = 1
    var typeTemp: String = ""
    var columnValueTemp: String = ""
    try {
      for (entry <- oldData.entrySet()) {
        cloNameTemp = entry.getKey
        columnValueTemp = transDefault(entry.getValue)
        // prepareState.setString(i, sqlType.getString(cloNameTemp))
        typeTemp = tableUtils.getColumnsType(tableTemp, cloNameTemp)
        //  dealColumnsUpdateType(oldData,typeTemp, prepareState, cloNameTemp, i)
        dealColumnsUpdateType(columnValueTemp, typeTemp, prepareState, cloNameTemp, i)
        i = i + 1
      }
      for (entry <- 0 to keyName.length - 1) {
        prepareState.setString(i, newDataTemp.getString(keyName(entry)))
        i = i + 1
      }
      prepareState.executeUpdate
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
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
  def dealColumnsUpdateType(columnValueTemp: String, typeTemp: String, prepareState: PreparedStatement, columnNameTemp: String, i: Int): Unit = {
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
        // prepareState.setTimestamp(i, sqlType.getTimestamp(columnNameTemp))
        dataTypeTemp = "DateTime"
        dataTemp = dealDefaultValue(toTimeStamp2Date(columnValueTemp, "yyyy-MM-dd HH:mm:ss"), dataTypeTemp)
        prepareState.setString(i, dataTemp.toString)
      }
      case "Int8" | "Int16" | "Int32" | "UInt8" | "UInt16" | "UInt32" |
           "Some(Int8)" | "Some(Int16)" | "Some(Int32)" | "Some(UInt8)" | "Some(UInt16)" | "Some(UInt32)" => {
        //    val Ck = println(11111)

        //   prepareState.setInt(i, sqlType.getInteger(columnNameTemp))
        dataTypeTemp = "Int"
        dataTemp = dealDefaultValue(columnValueTemp, dataTypeTemp)
        prepareState.setInt(i, dataTemp.toString.toInt)
      }
      case "Float" | "Float32" | "Float64" |
           "Some(Float)" | "Some(Float32)" | "Some(Float64)" => {
        //    val Ck = println(11111)
        //        prepareState.setFloat(i, sqlType.getFloat(columnNameTemp))
        dataTypeTemp = "Float"
        dataTemp = dealDefaultValue(columnValueTemp, dataTypeTemp)
        prepareState.setDouble(i, dataTemp.toString.toFloat)
      }
      case _ => {
        // prepareState.setString(i, sqlType.getString(columnNameTemp))
        prepareState.setString(i, columnValueTemp)
      }
    }
  }

  //
  //  def updateJobStatusTraceLogOld(conn: Connection,sql:String,sqlType:JSONObject,oldData:JSONObject,keyName:Array[String]): Unit ={
  //
  //    //complete_time=?  AND is_success=?
  //
  //      val newColumnNameTemp = updateSqlSetSplit(oldData)
  //      val KeyNameTemp = updateSqlWhereSplit(keyName)
  //      val sqlUpdate = sql + newColumnNameTemp + " where " + KeyNameTemp
  //      println(sqlUpdate)
  //      val prepareState = conn.prepareStatement(sqlUpdate)
  //      var cloNameTemp: String = ""
  //      var i = 1
  //    try {
  //      for (entry <- oldData.entrySet()) {
  //        cloNameTemp = entry.getKey
  //        prepareState.setString(i, sqlType.getString(cloNameTemp))
  //        i = i + 1
  //      }
  //      for (entry <- 0 to keyName.length - 1) {
  //        cloNameTemp == keyName(entry)
  //        prepareState.setString(i, sqlType.getString(cloNameTemp))
  //        i = i + 1
  //      }
  //      prepareState.executeUpdate
  //    }catch {
  //      case e :Exception=>e.printStackTrace()
  //    }finally {
  //     // ckPoolUtil.closeStatement(prepareState)
  //      ckPoolUtil.closeConnection(conn)
  //    }
  //
  //  }

  def updateSqlWhereSplit(keyName: Array[String]): String = {
    var sqlNameWhereStringTmp: String = ""
    for (entry <- 0 to keyName.length - 1) {
      val cloName = keyName(entry)
      if (sqlNameWhereStringTmp.equals("")) {
        sqlNameWhereStringTmp = cloName + "=?"
      } else {
        sqlNameWhereStringTmp = sqlNameWhereStringTmp + " AND " + cloName + "=? "
      }
    }
    sqlNameWhereStringTmp = sqlNameWhereStringTmp + ";"
    sqlNameWhereStringTmp
  }

  //complete_time=?  AND is_success=?
  def updateSqlSetSplit(oldData: JSONObject): String = {
    var sqlNameStringTemp: String = ""
    var cloNameTemp: String = ""
    for (entry <- oldData.entrySet()) {
      cloNameTemp = entry.getKey
      if (sqlNameStringTemp.equals("")) {
        sqlNameStringTemp = cloNameTemp + "=? "
      } else {
        sqlNameStringTemp = sqlNameStringTemp + " , " + cloNameTemp + "=? "

      }
    }
    sqlNameStringTemp
  }


}


object clickHouseUpdate {
  def getClickHouseUpdateSingleton(): clickHouseUpdate = {
    new clickHouseUpdate()
  }
}