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
  val KeyColumns=Array("ID")

  var InsertSql = """  INSERT INTO yzs_src.src_driver_posting """
  /*var InsertSql = """
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
*/
  //alter table  yzs_src.src_driver_posting update job_name='10' where id ='12' ;
  var UpdateSql ="alter table yzs_src.src_driver_posting update  "
  val columnTypeMap = Map(
    "ID"->"Int32",
    "CREATED_BY"->"String",
    "CREATION_TIME"->"DateTime",
    "LAST_UPDATE_TIME"->"DateTime",
    "UPDATED_BY"->"String",
    "VERSION"->"Int32",
    "RATING"->"Int32",
    "REF_ID"->"Int32",
    "STATUS"->"String",
    "CONTENT"->"String",
    "DURATION"->"Int32",
    "IS_URGENT"->"String",
    "TYPE"->"String",
    "LATEST_DISPATCHER_REPLY_ID"->"Int32",
    "TARGET_ID"->"String",
    "PARENT_ID"->"Int32",
    "DISPATCHE_RGROUP"->"String",
    "LOG"->"String",
    "COMPLETED_TIME"->"DateTime",
    "TRANSFER_TO"->"String",
    "TRANSFER_BY"->"String",
    "TRANSFER_TIME"->"DateTime",
    "PENDING_BY"->"String",
    "PENDING_TIME"->"DateTime",
    "COMPLETED_BY"->"String",
    "has_read"->"String",
    "container_id"->"Int32"
  )
}
