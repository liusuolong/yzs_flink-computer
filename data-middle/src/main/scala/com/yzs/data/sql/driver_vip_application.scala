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
  val KeyColumns=Array("id")
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

}
