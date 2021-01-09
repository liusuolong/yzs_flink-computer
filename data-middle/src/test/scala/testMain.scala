package scala

import com.yzs.data.utils.DateUtil.toTimeStamp2Date

object testMain {
  def main(args: Array[String]): Unit = {
   val test= toTimeStamp2Date("2021-01-06 10:34:45","yyyy-MM-dd HH:mm:ss")
  println(test)
    println( toTimeStamp2Date("","yyyy-MM-dd"))
  }

}
