package com.yzs.data.sql

object truck_order {
  val tableName = "src_truck_order"
  val Columns = Array("ID"
    , "CREATED_BY"
    , "CREATION_TIME"
    , "LAST_UPDATE_TIME"
    , "UPDATED_BY"
    , "VERSION"
    , "BL_NO"
    , "BUSINESS_NO"
    , "BUYER_CONTACT"
    , "BUYER_PHONE"
    , "CARRIER"
    , "CLOSING_TIME"
    , "CLASS_NO"
    , "DOCUMENTARY_OFF_TIME"
    , "ETA"
    , "ETD"
    , "FROM_PORT_NAME"
    , "GOODS_TYPE"
    , "IS_DANGEROUS_CARGO"
    , "IS_QUARANTINED"
    , "IS_REFER"
    , "IS_TANK_WASHER"
    , "OPENING_TIME"
    , "ORDER_NO"
    , "PLACE_OF_DELIVERY"
    , "PORT_DISTRICT"
    , "QUARANTINE_ADDRESS"
    , "QUARANTINE_NO"
    , "TANK_WASHER_ADDRESS"
    , "TEMPRATURE"
    , "TO_PORT_NAME"
    , "UN_NO"
    , "VESSEL"
    , "VOYAGE"
    , "BOUND_TYPE_ID"
    , "BUYER_ID"
    , "PORT_DISTRICT_ID"
    , "LOAD_TYPE_ID"
    , "ORDER_DOCUMENT_ID"
    , "SELLER_ID"
    , "TRADE_TYPE_ID"
    , "PORT_ID"
    , "SALES"
    , "SALES_DEPARTMENT"
    , "CUSTOMER_SERVICE"
    , "CARRIER_ID"
    , "eirAddress"
    , "hasCustomerAppliedEir"
    , "WAITTING_FOR_NOTIFY_TO_RELEASE_ORDER"
    , "IS_SOC"
    , "OUTPUT_DOCUMENTARY_OFF_DATA"
    , "DOCUMENT_CODE"
    , "REQUIRED_GATE_IN_TIME"
    , "OPENING_TIME_FIRST_UPDATE_TIME"
    , "ORDER_ACCEPT_TIME"
    , "FORWARDER_COMPANY_ID"
    , "BUYER_EMAIL"
    , "VENTILATION"
    , "IMDG_DESCRIPTION"
    , "IS_VGM_PROVIDED_BY_FACTORY"
    , "BL_NO_PRIMARY"
    , "OWNER"
    , "NEED_CUSTOMER_DOUBLE_CONFIRM"
    , "SETTLEMENT_CODE"
    , "CTN_OPERATOR_CODE"
    , "FORWARDER_ORDER_ID"
  )
  val KeyColumns = Array("ID")
  var InsertSql =
    """
             INSERT INTO yzs_src.src_truck_order  (
             ID
,CREATED_BY
,CREATION_TIME
,LAST_UPDATE_TIME
,UPDATED_BY
,VERSION
,BL_NO
,BUSINESS_NO
,BUYER_CONTACT
,BUYER_PHONE
,CARRIER
,CLOSING_TIME
,CLASS_NO
,DOCUMENTARY_OFF_TIME
,ETA
,ETD
,FROM_PORT_NAME
,GOODS_TYPE
,IS_DANGEROUS_CARGO
,IS_QUARANTINED
,IS_REFER
,IS_TANK_WASHER
,OPENING_TIME
,ORDER_NO
,PLACE_OF_DELIVERY
,PORT_DISTRICT
,QUARANTINE_ADDRESS
,QUARANTINE_NO
,TANK_WASHER_ADDRESS
,TEMPRATURE
,TO_PORT_NAME
,UN_NO
,VESSEL
,VOYAGE
,BOUND_TYPE_ID
,BUYER_ID
,PORT_DISTRICT_ID
,LOAD_TYPE_ID
,ORDER_DOCUMENT_ID
,SELLER_ID
,TRADE_TYPE_ID
,PORT_ID
,SALES
,SALES_DEPARTMENT
,CUSTOMER_SERVICE
,CARRIER_ID
,eirAddress
,hasCustomerAppliedEir
,WAITTING_FOR_NOTIFY_TO_RELEASE_ORDER
,IS_SOC
,OUTPUT_DOCUMENTARY_OFF_DATA
,DOCUMENT_CODE
,REQUIRED_GATE_IN_TIME
,OPENING_TIME_FIRST_UPDATE_TIME
,ORDER_ACCEPT_TIME
,FORWARDER_COMPANY_ID
,BUYER_EMAIL
,VENTILATION
,IMDG_DESCRIPTION
,IS_VGM_PROVIDED_BY_FACTORY
,BL_NO_PRIMARY
,OWNER
,NEED_CUSTOMER_DOUBLE_CONFIRM
,SETTLEMENT_CODE
,CTN_OPERATOR_CODE
,FORWARDER_ORDER_ID
)
             VALUES(,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?
,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?
,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            """

  //alter table  yzs_src.src_truck_order update job_name='10' where id ='12' ;
  var UpdateSql = "alter table  yzs_src.src_truck_order update  "
  val columnTypeMap = Map(
    "ID"->"Int32",
    "CREATED_BY"->"String",
    "CREATION_TIME"->"DateTime",
    "LAST_UPDATE_TIME"->"DateTime",
    "UPDATED_BY"->"String",
    "VERSION"->"Int32",
    "BL_NO"->"String",
    "BUSINESS_NO"->"String",
    "BUYER_CONTACT"->"String",
    "BUYER_PHONE"->"String",
    "CARRIER"->"String",
    "CLOSING_TIME"->"String",
    "CLASS_NO"->"String",
    "DOCUMENTARY_OFF_TIME"->"String",
    "ETA"->"DateTime",
    "ETD"->"DateTime",
    "FROM_PORT_NAME"->"String",
    "GOODS_TYPE"->"String",
    "IS_DANGEROUS_CARGO"->"String",
    "IS_QUARANTINED"->"String",
    "IS_REFER"->"String",
    "IS_TANK_WASHER"->"String",
    "OPENING_TIME"->"String",
    "ORDER_NO"->"String",
    "PLACE_OF_DELIVERY"->"String",
    "PORT_DISTRICT"->"String",
    "QUARANTINE_ADDRESS"->"String",
    "QUARANTINE_NO"->"String",
    "TANK_WASHER_ADDRESS"->"String",
    "TEMPRATURE"->"String",
    "TO_PORT_NAME"->"String",
    "UN_NO"->"String",
    "VESSEL"->"String",
    "VOYAGE"->"String",
    "BOUND_TYPE_ID"->"String",
    "BUYER_ID"->"Int32",
    "PORT_DISTRICT_ID"->"Int32",
    "LOAD_TYPE_ID"->"String",
    "ORDER_DOCUMENT_ID"->"Int32",
    "SELLER_ID"->"Int32",
    "TRADE_TYPE_ID"->"String",
    "PORT_ID"->"String",
    "SALES"->"String",
    "SALES_DEPARTMENT"->"String",
    "CUSTOMER_SERVICE"->"String",
    "CARRIER_ID"->"String",
    "eirAddress"->"String",
    "hasCustomerAppliedEir"->"String",
    "WAITTING_FOR_NOTIFY_TO_RELEASE_ORDER"->"String",
    "IS_SOC"->"String",
    "OUTPUT_DOCUMENTARY_OFF_DATA"->"String",
    "DOCUMENT_CODE"->"String",
    "REQUIRED_GATE_IN_TIME"->"String",
    "OPENING_TIME_FIRST_UPDATE_TIME"->"DateTime",
    "ORDER_ACCEPT_TIME"->"String",
    "FORWARDER_COMPANY_ID"->"Int32",
    "BUYER_EMAIL"->"String",
    "VENTILATION"->"String",
    "IMDG_DESCRIPTION"->"String",
    "IS_VGM_PROVIDED_BY_FACTORY"->"String",
    "BL_NO_PRIMARY"->"String",
    "OWNER"->"String",
    "NEED_CUSTOMER_DOUBLE_CONFIRM"->"String",
    "SETTLEMENT_CODE"->"String",
    "CTN_OPERATOR_CODE"->"String",
    "FORWARDER_ORDER_ID"->"String"
  )

}
