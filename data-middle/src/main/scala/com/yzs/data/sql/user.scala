package com.yzs.data.sql

object user {
  val tableName = "src_user"
  val Columns = Array("TYPE"
    , "ID"
    , "CREATED_BY"
    , "CREATION_TIME"
    , "LAST_UPDATE_TIME"
    , "UPDATED_BY"
    , "VERSION"
    , "COST_INVOICE_MATCH_TIME"
    , "COST_INVOICE_MATCHER"
    , "COST_REMARK"
    , "HAS_COST_INVOICE_MATCHED"
    , "ACCEPT_ORDER_TIME"
    , "DISPATCH_TIME"
    , "DISPATCHER"
    , "DISPATH_TYPE"
    , "DRIVER_NAME"
    , "DRIVER_PHONE"
    , "DROP_REASON"
    , "DROP_TIME"
    , "IS_ACTIVE"
    , "ORDER_TAKER"
    , "SEND_ORDER_TIME"
    , "TAKER_ADDRESS"
    , "TAKER_PHONE"
    , "TRUCK_PLATE_NUMBER"
    , "CONTAINER_RECEIPT_ID"
    , "STATUS_ID"
    , "TASK_TYPE_ID"
    , "DRIVER_ID"
    , "TASK_PACKAGE_ID"
    , "PLAN_GATE_IN_TIME_FROM"
    , "PLAN_GATE_IN_TIME_TO"
    , "REAMRK"
    , "CASH_PAYED"
    , "OIL_PAYED"
    , "VIP_SUBSIDY_PAYED"
    , "CONFIRMED_TIME"
    , "HAS_CONFIRMED"
    , "SEND_ORDER_REMARK"
    , "DROP_DEPOT"
    , "PICK_UP_DEPOT"
    , "REMARK"
    , "REQUIRED_SUPPLIER_TIME"
    , "REQUIRED_TIME"
    , "SUB_TYPE"
    , "SUPPLIER_REMARK"
    , "SUPPLIER_ID"
    , "IS_BEFOREHAND_EMPTY_PICKUP"
    , "RESET_COST_REMARK"
    , "NON_VIP_SEND_ORDER_TIME"
    , "ASSIGN_SUPPLIER_TIME"
    , "GRAB_ORDER_TYPE"
    , "EIR_DESPATCHER_TYPE"
    , "EIR_HAULIER_NAME"
    , "EIR_HAULIER_CODE"
    , "is_delay"
    , "transport_schedule"
    , "DROP_DEPOT_ID"
    , "PREV_STATUS_ID"
  )
  val KeyColumns = Array("ID")

  var InsertSql ="""   INSERT INTO yzs_src.src_user """
 /* var InsertSql =
    """
             INSERT INTO yzs_src.src_user  (
             TYPE
,ID
,CREATED_BY
,CREATION_TIME
,LAST_UPDATE_TIME
,UPDATED_BY
,VERSION
,COST_INVOICE_MATCH_TIME
,COST_INVOICE_MATCHER
,COST_REMARK
,HAS_COST_INVOICE_MATCHED
,ACCEPT_ORDER_TIME
,DISPATCH_TIME
,DISPATCHER
,DISPATH_TYPE
,DRIVER_NAME
,DRIVER_PHONE
,DROP_REASON
,DROP_TIME
,IS_ACTIVE
,ORDER_TAKER
,SEND_ORDER_TIME
,TAKER_ADDRESS
,TAKER_PHONE
,TRUCK_PLATE_NUMBER
,CONTAINER_RECEIPT_ID
,STATUS_ID
,TASK_TYPE_ID
,DRIVER_ID
,TASK_PACKAGE_ID
,PLAN_GATE_IN_TIME_FROM
,PLAN_GATE_IN_TIME_TO
,REAMRK
,CASH_PAYED
,OIL_PAYED
,VIP_SUBSIDY_PAYED
,CONFIRMED_TIME
,HAS_CONFIRMED
,SEND_ORDER_REMARK
,DROP_DEPOT
,PICK_UP_DEPOT
,REMARK
,REQUIRED_SUPPLIER_TIME
,REQUIRED_TIME
,SUB_TYPE
,SUPPLIER_REMARK
,SUPPLIER_ID
,IS_BEFOREHAND_EMPTY_PICKUP
,RESET_COST_REMARK
,NON_VIP_SEND_ORDER_TIME
,ASSIGN_SUPPLIER_TIME
,GRAB_ORDER_TYPE
,EIR_DESPATCHER_TYPE
,EIR_HAULIER_NAME
,EIR_HAULIER_CODE
,is_delay
,transport_schedule
,DROP_DEPOT_ID
,PREV_STATUS_ID
)
             VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,
?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,
?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """
*/
  //alter table  yzs_src.src_user update job_name='10' where id ='12' ;
  var UpdateSql = "alter table  yzs_src.src_user update  "


  val columnTypeMap = Map(
    "TYPE"->"String",
    "ID"->"String",
    "CREATED_BY"->"String",
    "CREATION_TIME"->"DateTime",
    "LAST_UPDATE_TIME"->"DateTime",
    "UPDATED_BY"->"String",
    "VERSION"->"Int32",
    "ATTEMPTS"->"Int32",
    "EMAIL"->"String",
    "INVITER"->"String",
    "INVITER_ROOT"->"String",
    "IS_ACTIVE"->"String",
    "IS_ADMIN"->"String",
    "IS_ADMINISTRATOR"->"String",
    "IS_FOR_TEST"->"String",
    "LAST_ATTEMPT_TIME"->"DateTime",
    "MOBILE_PHONE"->"String",
    "NAME"->"String",
    "PARTNER_CODE"->"String",
    "PARTNER_USERID"->"String",
    "PASSWORD"->"String",
    "PHONE"->"String",
    "POINTS"->"Float32",
    "QQ"->"String",
    "REGISTRATION_SOURCE"->"String",
    "REMEMBER_ME_TOKEN"->"String",
    "RESET_TEMP_KEY"->"String",
    "RESET_TEMP_KEY_EXPIRE_TIME"->"DateTime",
    "AUDIT_SUBMIT_TIME"->"DateTime",
    "AUDIT_TIME"->"DateTime",
    "AVATOR_IMAGE_PATH"->"String",
    "BENEFICIARY_ACCOUNT_IMAGE_PATH"->"String",
    "BENEFICIARY_ACCOUNT_NUMBER"->"String",
    "BENEFICIARY_BANK_NAME"->"String",
    "BENEFICIARY_NAME"->"String",
    "ID_CARD_IMAGE_PATH"->"String",
    "ID_CARD_IMAGE_PATH_FRONT"->"String",
    "ID_CARD_NUMBER"->"String",
    "IMEI"->"String",
    "IS_BANK_AUTHORED"->"String",
    "IS_PLANFORM_BUY_TRUCK"->"String",
    "IS_SEND_BACK_RECEIPT"->"String",
    "IS_VIP"->"String",
    "IS_SELF_SUPPORT"->"String",
    "MOBILE_PHONE_CITY"->"String",
    "MOBILE_PHONE_PROVINCE"->"String",
    "PUSH_CLIENT_ID"->"String",
    "REMARK"->"String",
    "WECHAT_UNION_ID"->"String",
    "LANGUAGE_ID"->"String",
    "COMPANY_ID"->"Int32",
    "AUDIT_STATUS_ID"->"String",
    "LATEST_AUDIT_LOG_ID"->"Int32",
    "SETTLEMENT_COMPANY_ID"->"Int32",
    "VIP_APPLICATION_ID"->"Int32",
    "DEPARTMENT"->"String",
    "FEIDAN_INVITER"->"String",
    "PORT"->"String",
    "PORT_ID"->"String",
    "DISPATCHE_RGROUP"->"String",
    "OPENID"->"String",
    "POSITION"->"String",
    "WEB_PHONE_LOGINID"->"String",
    "WEB_PHONE_LOGIN_TYPE"->"String",
    "WEB_PHONE_PASSWORD"->"String",
    "BD_CODE"->"String",
    "DRIVER_TYPE_ID"->"String",
    "CHARACTER_POSITION"->"String",
    "EIR_ACCOUNT_MOBILE_PHONE"->"String",
    "EIR_ACCOUNT_NAME"->"String",
    "BENEFICIARY_ID_CARD_NUMBER"->"String",
    "READ_NOTICE_IDS"->"String",
    "IS_RECORD_HOLDER"->"String",
    "BENEFICIARY_PLACE"->"String",
    "NEW_SYSTEM_AUDIT_STATUS"->"String",
    "NEW_SYSTEM_BANK_CARD_AUDIT_STATUS"->"String",
    "HAVE_SETTLE_BALANCE"->"String",
    "NEW_SYSTEM_DRIVER_TRUCK_AUDIT_STATUS"->"String",
    "is_access_internet_phone"->"String",
    "apply_status"->"String",
    "last_click_time"->"DateTime",
    "company_name"->"String",
    "is_vest"->"String"
  )

}
