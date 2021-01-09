package com.yzs.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern

object DateUtil {


  /**
    * 时间戳转换成日期格式字符串
    *
    * @param seconds 精确到秒的字符串
    * @return
    */
    //2021-01-06 10:34:45  20210108
  def toTimeStamp2Date(seconds: String, format: String): String = {
  var inFormat:String=format
      var dataValueTemp:String =null

      if (format == null || format.isEmpty()) {
        inFormat = "yyyy-MM-dd HH:mm:ss"
      }
     if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
        dataValueTemp = new SimpleDateFormat(inFormat).format(new Date())
     }else{

       dataValueTemp= new SimpleDateFormat(inFormat).format(
         new SimpleDateFormat(inFormat).parse(seconds))
     }

      dataValueTemp
  }

  /**
    * 判断字符串是否是数字
    * @param str  字符串数字
    * @return
    */
  def isNumeric(str: String): Boolean = {
    val pattern = Pattern.compile("[0-9]*")
    pattern.matcher(str).matches
  }

  /**
    * 日期格式字符串转换成时间戳
    *
    * @param date_str   字符串日期
    * @param format 如：yyyy-MM-dd HH:mm:ss
    * @return
    */
  def date2TimeStamp(date_str: String, format: String): String = {
    try {
      var sdf = new SimpleDateFormat(format);
      return (sdf.parse(date_str).getTime() / 1000).toString
    } catch {
      case e: Exception => e.printStackTrace()
    }
    return ""
  }

  /**
    * 取得当前时间戳（精确到秒）
    *
    * @return
    */
  def timeStamp(): String = {
    var time = System.currentTimeMillis()
    var t = String.valueOf(time / 1000)
    return t
  }

//获取 系统日期
  def getSysDate(): String = {
     new SimpleDateFormat("yyyyMMdd").format(new Date())
  }
  //获取 系统日期
  def getSysDateStamp(): String = {
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
  }
  /**
    * 时间字符串转化为  格式：yyyymmdd
    */
  def  getDayStr(timeStr:String):String={
    return timeStr .split(" ")(0).replace("-", "")
  }


}