package com.yzs.data.sql

object driver_vip_application {
  val tableName = "src_driver_vip_application"
  val Columns=Array(
    "ID"
    ,"CREATED_BY"
    ,"CREATION_TIME"
    ,"LAST_UPDATE_TIME"
    ,"UPDATED_BY"
    ,"VERSION"
    ,"APPLICANT"
    ,"APPLY_TIME"
    ,"APPROVE_TIME"
    ,"APPROVER"
    ,"FAIL_REASON"
    ,"NEED_APP_INSTRACTION"
    ,"VIP_RULE_NOTIFIED"
    ,"STATUS_ID"
    ,"VIP_GRADE"
    ,"REMARK"
    ,"OUT_REASON"
  )
  val KeyColumns=Array("ID")
  var InsertSql = """
             INSERT INTO yzs_src.src_driver_vip_application  (
              ID
,CREATED_BY
,CREATION_TIME
,LAST_UPDATE_TIME
,UPDATED_BY
,VERSION
,APPLICANT
,APPLY_TIME
,APPROVE_TIME
,APPROVER
,FAIL_REASON
,NEED_APP_INSTRACTION
,VIP_RULE_NOTIFIED
,STATUS_ID
,VIP_GRADE
,REMARK
,OUT_REASON
)
             VALUES(?,?,?,?,?,?,?,?,?,?,?)
            """

  //alter table  yzs_src.src_driver_vip_application update job_name='10' where id ='12' ;
  var UpdateSql ="alter table  yzs_src.src_driver_vip_application update  "
  val columnTypeMap = Map(
    "ID"->"Int32",
    "CREATED_BY"->"String",
    "CREATION_TIME"->"DateTime",
    "LAST_UPDATE_TIME"->"DateTime",
    "UPDATED_BY"->"String",
    "VERSION"->"Int32",
    "APPLICANT"->"String",
    "APPLY_TIME"->"DateTime",
    "APPROVE_TIME"->"DateTime",
    "APPROVER"->"String",
    "FAIL_REASON"->"String",
    "NEED_APP_INSTRACTION"->"String",
    "VIP_RULE_NOTIFIED"->"String",
    "STATUS_ID"->"String",
    "VIP_GRADE"->"String",
    "REMARK"->"String",
    "OUT_REASON"->"Int32"
  )

}
