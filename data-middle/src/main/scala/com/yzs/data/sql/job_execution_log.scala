package com.yzs.data.sql

object job_execution_log extends Serializable {
  val tableName = "src_job_execution_log"
  val Columns = Array("id"
    , "job_name"
    , "task_id"
    , "hostname"
    , "ip"
    , "sharding_item"
    , "execution_source"
    , "failure_cause"
    , "is_success"
    , "start_time"
    , "complete_time"
  )
  val KeyColumns = Array("id")
  var InsertSql =
    """
             INSERT INTO yzs_src.src_job_execution_log  (
              id
,job_name
,task_id
,hostname
,ip
,sharding_item
,execution_source
,failure_cause
,is_success
,start_time
,complete_time
)
             VALUES(?,?,?,?,?,?,?,?,?,?,?)
            """

  //alter table  yzs_src.src_job_execution_log update job_name='10' where id ='12' ;
  var UpdateSql = "alter table  yzs_src.src_job_execution_log update  "
  val columnTypeMap = Map(
    "id" -> "String",
    "job_name" -> "String",
    "task_id" -> "String",
    "hostname" -> "String",
    "ip" -> "String",
    "sharding_item" -> "String",
    "execution_source" -> "String",
    "failure_cause" -> "String",
    "is_success" -> "String",
    "start_time" -> "String",
    "complete_time" -> "String"
  )

}
