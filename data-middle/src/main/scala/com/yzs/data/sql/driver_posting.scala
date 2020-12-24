package com.yzs.data.sql

object driver_posting {
  val tableName = "src_driver_posting"
  val Columns=Array(
    "ID"
    ,"CREATED_BY"
    ,"CREATION_TIME"
    ,"LAST_UPDATE_TIME"
    ,"UPDATED_BY"
    ,"VERSION"
    ,"RATING"
    ,"REF_ID"
    ,"STATUS"
    ,"CONTENT"
    ,"DURATION"
    ,"IS_URGENT"
    ,"TYPE"
    ,"LATEST_DISPATCHER_REPLY_ID"
    ,"TARGET_ID"
    ,"PARENT_ID"
    ,"DISPATCHE_RGROUP"
    ,"LOG"
    ,"COMPLETED_TIME"
    ,"TRANSFER_TO"
    ,"TRANSFER_BY"
    ,"TRANSFER_TIME"
    ,"PENDING_BY"
    ,"PENDING_TIME"
    ,"COMPLETED_BY"
    ,"has_read"
    ,"container_id"
  )
  val KeyColumns=Array("id")
  var InsertSql = """
             INSERT INTO yzs_src.src_driver_posting  (
ID
,CREATED_BY
,CREATION_TIME
,LAST_UPDATE_TIME
,UPDATED_BY
,VERSION
,RATING
,REF_ID
,STATUS
,CONTENT
,DURATION
,IS_URGENT
,TYPE
,LATEST_DISPATCHER_REPLY_ID
,TARGET_ID
,PARENT_ID
,DISPATCHE_RGROUP
,LOG
,COMPLETED_TIME
,TRANSFER_TO
,TRANSFER_BY
,TRANSFER_TIME
,PENDING_BY
,PENDING_TIME
,COMPLETED_BY
,has_read
,container_id
             )
             VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,
                    ?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """

  //alter table  yzs_src.src_driver_posting update job_name='10' where id ='12' ;
  var UpdateSql ="alter table zs_src.src_driver_posting update  "

}
