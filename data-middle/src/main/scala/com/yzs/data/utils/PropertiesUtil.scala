package com.yzs.data.utils

import java.util.Properties

/**
 * @author admin
 */
object PropertiesUtil {

  def getFilePath(fileName: String): String = {
    val classLoader = this.getClass.getClassLoader
    val path = classLoader.getResource(fileName).getPath
    //val path = Thread.currentThread().getContextClassLoader.getResource(fileName).getPath //文件要放到resource文件夹下
    path
  }

  def getProperties(filePath:String):Properties={
    val properties = new Properties()
    //文件要放到resource文件夹下
    val path = getClass().getClassLoader().getResourceAsStream(filePath)
    properties.load(path)
    properties
  }
  
  def main(args: Array[String]): Unit = {
    // var filePath = PropertiesUtil.getFilePath("link_msg.txt")
    // println(filePath)
    var filePath = "dev/redis.properties"
    val properties = getProperties(filePath)
    println(properties.getProperty("redis.host"))
    println(properties.getProperty( "redis.port"))
  }

}