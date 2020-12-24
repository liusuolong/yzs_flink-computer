package com.yzs.data.sql

object job_status_trace_log {
  val tableName = "src_job_status_trace_log"
  val Columns=Array("id","job_name","original_task_id",
    "task_id","slave_id","source","execution_type",
    "sharding_item","state","message","creation_time"
  )
  val KeyColumns=Array("id")
  var InsertSql = """
             INSERT INTO yzs_src.src_job_status_trace_log  (id,job_name,original_task_id,task_id,slave_id,
                                     source,execution_type,sharding_item,state,message,creation_time)
             VALUES(?,?,?,?,?,?,?,?,?,?,?)
            """

  //alter table  yzs_src.src_job_status_trace_log update job_name='10' where id ='12' ;
  var UpdateSql ="alter table  yzs_src.src_job_status_trace_log update  "

}

