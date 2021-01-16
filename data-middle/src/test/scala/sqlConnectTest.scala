//import com.yzs.data.main.driverPostingMain.dealParseObject
//import com.alibaba.fastjson.{JSON, JSONObject}
//import com.yzs.data.utils.clickHousePoolUtil
//
//import scala.collection.JavaConversions._
//import com.alibaba.fastjson.JSONObject
//import com.yzs.data.common.clickHouseInsert
//
//import scala.util.parsing.json
//import scala.util.parsing.json.JSONObject
//
////object sqlConnectTest {
//
//  //def main(args: Array[String]): Unit = {
////    val sqlUpdate = "alter table  yzs_src.src_job_status_trace_log update  "
////    val oldData = dealParseObject(
////      """{"is_success":"0","complete_time":null}""")
////
////    var sqlNameString: String = ""
////    for (entry <- oldData.entrySet()) {
////     val cloName=entry.getKey
////      if (sqlNameString.equals("")){
////        sqlNameString=cloName+"=? "
////      }else{
////        sqlNameString=sqlNameString+" AND "+cloName+"=? "
////
////      }
////    }
////    println(sqlNameString)
//
////
////    val jobStatusTraceLogKeyColumns=Array("id","name")
////        for (entry <- 0 to jobStatusTraceLogKeyColumns.length-1) {
////         val cloName=jobStatusTraceLogKeyColumns(entry)
////          if (sqlNameString.equals("")){
////            sqlNameString=cloName+"=? "
////          }else{
////            sqlNameString=sqlNameString+" AND "+cloName+"=? "
////          }
////        }
////        println(sqlNameString)
////    println(clickHousePoolUtil.getClass.getSimpleName.replace("$",""))
////   // c=Object.("clickHousePoolUtil")
////
////  val  tableTemp="com.yzs.data.sql.job_execution_log$"
////val value = tableListUtil.stringToClass(tableTemp)
////    println(value.getClass.getName)
///*val  tableTemp:String= "com.yzs.data.sql.job_status_trace_log"
//val classObj = Class.forName(tableTemp).newInstance()
//println(classObj.getClass.getDeclaredField("jobStatusTraceLogUpdate"))
//
//
//    import com.alibaba.fastjson.JSONObject
//    val str = "{'wu':'吴','yan':'彦','zu':'祖'}"
//    val jsonObject = JSONObject.parseObject(str)
//    val value = insertSqlValuesSplit(jsonObject)
//    println(value)
//    val insert = clickHouseInsert.getClickHouseInsertSingleton()
//    import com.alibaba.fastjson.JSONObject
//    val str = "{'wu':'吴','yan':'彦','zu':'祖'}"
//    val jsonObject = JSONObject.parseObject(str)
//    val value = insertSqlValuesSplit(jsonObject)
//    val Str="{\"CREATION_TIME\":\"2021-01-07 17:12:31\",\"STATUS\":\"COMPLETED\"}"
//    val newDataTemp =  JSON.parseObject(Str)
//    println(insert.insertSqlValuesSplit(newDataTemp))
//    val str = "{'wu':'吴','yan':'彦','zu':'祖'}"
//    val jsonObject = JSONObject.parseObject(str)
//  }
//}
//*/
