package com.yzs.data.utils

import java.text.SimpleDateFormat
import java.util.Date

object CommonUtil {


  def transDefault(dataValue: Any): String = {
    var dataValueTemp:String =""

    if (dataValue == null || dataValue.equals("null")) {
      dataValueTemp=""
    }else{
      dataValueTemp=dataValue.toString
    }
    dataValueTemp
  }


  def dealDefaultValue(dataValue:String,typeTemp:String): Any ={
    var dataValueTemp:Any=null
    typeTemp match {
      case "Date"=>{
        if (dataValue == null || dataValue.isEmpty() || dataValue.equals("null")
          ||dataValue.equals("")) {
          dataValueTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        }else{
          dataValueTemp=dataValue
        }
      }
      case "DateTime"=>{
        if (dataValue == null || dataValue.isEmpty() || dataValue.equals("null")
          ||dataValue.equals("")) {
          dataValueTemp = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        }else{
          dataValueTemp=dataValue
        }
      }
      case "Int"|"Float"=>{
        if (dataValue == null || dataValue.isEmpty() || dataValue.equals("null")
          ||dataValue.equals("")) {
          dataValueTemp = 0
        }else{
          dataValueTemp=dataValue
        }
      }
      case _=>{
        dataValueTemp=dataValue
      }
    }
    dataValueTemp
  }



}
