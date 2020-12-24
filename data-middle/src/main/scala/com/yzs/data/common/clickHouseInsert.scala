package com.yzs.data.common

import java.sql.Connection

import com.alibaba.fastjson.JSONObject
import com.yzs.data.common.configUtil.ckPoolUtil

class clickHouseInsert extends Serializable{


  def insertJobStatusTraceLog(conn: Connection,sql:String,tableGetColumns:Array[String],sqlType:JSONObject): Unit ={
    val prepareState = conn.prepareStatement(sql)
    try {
      for (i <- 1 to sqlType.size()) {
        prepareState.setString(i, sqlType.getString(tableGetColumns(i - 1)))
      }
      println(prepareState.toString)
      prepareState.executeUpdate //执行sql语句
    }catch{
      case e:Exception=>e.printStackTrace()
    }finally {
      ckPoolUtil.closeStatement(prepareState)
      ckPoolUtil.closeConnection(conn)
    }
  }
}

object clickHouseInsert {
  def getClickHouseInsertSingleton(): clickHouseInsert = {
  new clickHouseInsert()
}
}
