/*
import java.sql.{Connection, DriverManager, PreparedStatement}

import com.yzs.data.common
import com.yzs.data.utils.{clickHouseUtil, kafkaDealUtil}

object test {

  def main(args: Array[String]): Unit = {
    val clickCon = clickHouseUtil.getClickHouseUtil("dev/clickhouse.properties")
    val Con = clickCon.getClickHouseConn()
    val sql =
      """      INSERT INTO yzs_src.src_job_status_trace_log  (id,job_name,original_task_id,task_id,slave_id,
                       source,execution_type,sharding_item,state,message,creation_time)
                       VALUES(?,?,?,?,?,?,?,?,?,?,?)
    """

    val preparedStatement = Con.prepareStatement(sql)
    preparedStatement.setString(1, "1")
    preparedStatement.setString(2, "1")
    preparedStatement.setString(3, "1")
    preparedStatement.setString(4, "1")
    preparedStatement.setString(5, "1")
    preparedStatement.setString(6, "1")
    preparedStatement.setString(7, "1")
    preparedStatement.setString(8, "1")
    preparedStatement.setString(9, "1")
    preparedStatement.setString(10, "1")
    preparedStatement.setString(11, "1")
    preparedStatement.executeUpdate //执行sql语句


  }
}
*/
