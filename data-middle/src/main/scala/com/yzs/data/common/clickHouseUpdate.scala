package com.yzs.data.common

import java.sql.Connection

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.collection.JavaConversions._
import com.alibaba.fastjson.JSONObject
import com.yzs.data.common.configUtil.ckPoolUtil

class clickHouseUpdate extends Serializable{

  def updateJobStatusTraceLog(conn: Connection,sql:String,sqlType:JSONObject,oldData:JSONObject,keyName:Array[String]): Unit ={

    //complete_time=?  AND is_success=?

      val newColumnNameTemp = updateSqlSetSplit(oldData)
      val KeyNameTemp = updateSqlWhereSplit(keyName)
      val sqlUpdate = sql + newColumnNameTemp + " where " + KeyNameTemp
      println(sqlUpdate)
      val prepareState = conn.prepareStatement(sqlUpdate)
      var cloNameTemp: String = ""
      var i = 1
    try {
      for (entry <- oldData.entrySet()) {
        cloNameTemp = entry.getKey
        prepareState.setString(i, sqlType.getString(cloNameTemp))
        i = i + 1
      }
      for (entry <- 0 to keyName.length - 1) {
        cloNameTemp == keyName(entry)
        prepareState.setString(i, sqlType.getString(cloNameTemp))
        i = i + 1
      }
      prepareState.executeUpdate
    }catch {
      case e :Exception=>e.printStackTrace()
    }finally {
      ckPoolUtil.closeStatement(prepareState)
      ckPoolUtil.closeConnection(conn)
    }

  }

def updateSqlWhereSplit(keyName:Array[String]): String ={
  var sqlNameWhereStringTmp: String = ""
  for (entry <- 0 to keyName.length-1) {
    val cloName=keyName(entry)
    if (sqlNameWhereStringTmp.equals("")){
      sqlNameWhereStringTmp=cloName+"=? "
    }else{
      sqlNameWhereStringTmp=sqlNameWhereStringTmp+" AND "+cloName+"=? "
    }
  }
  sqlNameWhereStringTmp=sqlNameWhereStringTmp+";"
  sqlNameWhereStringTmp
}

  //complete_time=?  AND is_success=?
  def updateSqlSetSplit(oldData:JSONObject): String ={
    var sqlNameStringTemp: String = ""
    var cloNameTemp:String =""
    for (entry <- oldData.entrySet()) {
      cloNameTemp=entry.getKey
      if (sqlNameStringTemp.equals("")){
        sqlNameStringTemp=cloNameTemp+"=? "
      }else{
        sqlNameStringTemp=sqlNameStringTemp+" AND "+cloNameTemp+"=? "

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