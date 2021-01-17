package com.yzs.data.sql

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.util.parsing.json
import scala.util.parsing.json.JSONObject

object tableList {
  val TableObject = "{'src_driver_posting':'yzs_src'," +
             "'src_truck_order_container':'yzs_src'," +
             "'src_truck_order':'yzs_src'," +
             "'src_user':'yzs_src'," +
             "'src_driver_vip_application':'yzs_src'," +
             "'src_belonged_driver':'yzs_src'}"
  val columnTypeObject = JSON.parseObject(TableObject)
 /* val columnTypeMap = Map(
    "src_driver_posting"->"yzs_src",
    "src_truck_order_container"->"yzs_src",
    "src_truck_order"->"yzs_src",
    "src_user"->"yzs_src",
    "src_driver_vip_application"->"yzs_src",
    "src_belonged_driver"->"yzs_src"
  )*/
  /*val columnTypeMap = json.JSONObject("{'a':'a'}" )
*/
}
