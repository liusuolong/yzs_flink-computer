/*

package com.yzs.data.sql

object tableUtils_bak extends Serializable {
  val tableListArray = Array(
    "com.yzs.data.sql.driver_posting"
    , "com.yzs.data.sql.truck_order_container"
    , "com.yzs.data.sql.truck_order"
    , "com.yzs.data.sql.user"
    , "com.yzs.data.sql.driver_vip_application"
    , "com.yzs.data.sql.belonged_driver"

  )

  def getColumnsType(tableName: String, ColumnName: String): String = {
    val temp = getTableClass(tableName)
    temp match {
      case 0 => job_status_trace_log.columnTypeMap.get(ColumnName).toString
      case 1 => job_execution_log.columnTypeMap.get(ColumnName).toString
      case 0 => driver_posting.columnTypeMap.get(ColumnName).toString
      case 1=> truck_order_container.columnTypeMap.get(ColumnName).toString
      case 2 => truck_order.columnTypeMap.get(ColumnName).toString
      case 3 => user.columnTypeMap.get(ColumnName).toString
      case 4 => driver_vip_application.columnTypeMap.get(ColumnName).toString
      case 5 => belonged_driver.columnTypeMap.get(ColumnName).toString

      case _ => ""
    }

  }


  def gettableName(tableName: String): String = {
    val temp = getTableClass(tableName)

    val gettableNameTemp = temp match {
      case 0 => job_status_trace_log.tableName
      case 1 => job_execution_log.tableName
      case 0 => driver_posting.tableName
      case 1 => truck_order_container.tableName
      case 2 => truck_order.tableName
      case 3 => user.tableName
      case 4 => driver_vip_application.tableName
      case 5 => belonged_driver.tableName

      case _ => ""
    }
    gettableNameTemp
  }

  def getColumns(tableName: String): Array[String] = {
    val temp = getTableClass(tableName)

    val getColumnsTemp = temp match {
      case 1 => job_execution_log.Columns
      case 0 => job_status_trace_log.Columns
      case 0 => driver_posting.Columns
      case 1 => truck_order_container.Columns
      case 2 => truck_order.Columns
      case 3 => user.Columns
      case 4 => driver_vip_application.Columns
      case 5 => belonged_driver.Columns

      case _ => Array[String] {
        ""
      }
    }
    getColumnsTemp
  }

  def getInsertSql(tableName: String): String = {
    val temp = getTableClass(tableName)

    val getInsertSql = temp match {
      case 0 => job_status_trace_log.InsertSql
      case 1 => job_execution_log.InsertSql
      case 0 => driver_posting.InsertSql
      case 1 => truck_order_container.InsertSql
      case 2 => truck_order.InsertSql
      case 3 => user.InsertSql
      case 4 => driver_vip_application.InsertSql
      case 5 => belonged_driver.InsertSql
      case _ => ""
    }
    getInsertSql
  }


  def getUpdateSql(tableName: String): String = {
    val temp = getTableClass(tableName)

    val getUpdateSql = temp match {
      case 0 => job_status_trace_log.UpdateSql
      case 1 => job_execution_log.UpdateSql
      case 0 => driver_posting.UpdateSql
      case 1 => truck_order_container.UpdateSql
      case 2 => truck_order.UpdateSql
      case 3 => user.UpdateSql
      case 4 => driver_vip_application.UpdateSql
      case 5 => belonged_driver.UpdateSql
      case _ => ""
    }
    getUpdateSql
  }

  def getKeyColumns(tableName: String): Array[String] = {
    val temp = getTableClass(tableName)

    temp match {
      case 0 => job_status_trace_log.KeyColumns
      case 1 => job_execution_log.KeyColumns
      case 0 => driver_posting.KeyColumns
      case 1 => truck_order_container.KeyColumns
      case 2 => truck_order.KeyColumns
      case 3 => user.KeyColumns
      case 4 => driver_vip_application.KeyColumns
      case 5 => belonged_driver.KeyColumns
      case _ => Array {
        ""
      }
    }
  }


  def getTableClass(tableName: String): Int = {
    var temp: Int = 0
    for (entry <- 0 to tableListArray.length - 1) {
      if (tableListArray(entry).toString().contains(tableName)) {
        temp = entry
      }
    }
    temp
  }


}

*/

