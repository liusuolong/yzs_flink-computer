package scala

import com.alibaba.fastjson.JSON
import com.yzs.data.common.configUtil.ckPoolUtil
import com.yzs.data.sql.tableUtils
import com.yzs.data.sql.tableUtils.getColumns

import java.sql.Connection

object test02 {

  def main(args: Array[String]): Unit = {
    val conn = ckPoolUtil.getConn()
//    val typeTemp = tableUtils.getColumnsType(conn,"yzs_src",
//      "src_driver_posting", "CREATED_BY")
//
//    print(typeTemp)
//
//  val temp=getColumns(conn,"yzs_src","src_driver_posting")
//    print(temp)

/*
    val temp=getColumns(conn,"yzs_src","src_driver_posting")
    print(temp(0))*/
//
//    tableUtils.getColumnsType(conn,"yzs_src",tableTemp, columnNameTemp,mysqlType)
    import com.alibaba.fastjson.JSONObject
    val str = "{'id':'int(10)','name':'varchar(10)','age':'int(10)','a2':'datetime'}"
    val jsonObject = JSON.parseObject(str)

    tableUtils.getColumnsType(conn,"yzs_src","test_210116", "a2",jsonObject)
  }
}
