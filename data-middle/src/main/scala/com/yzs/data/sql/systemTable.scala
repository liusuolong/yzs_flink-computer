package com.yzs.data.sql

object systemTable {
  /**
   * 数据库下对应表的列的类型
   */
  val ColumnType =
    """
      select  type  from system.columns
      where database=?
      and  table =?
      and  name=?
      ;
      """
  /**
   * 获取数据库对应的表下的列
   */
  val ColumnArray =
    """
      select  name  from system.columns
      where database=?
      and  table =?
      ;
      """
  /**
   * 获取数据库下表对应的主键
   */
  val KeyColumnArray =
    """
      select  name  from system.columns
      where database=?
      and  table =?
      and is_in_primary_key=1
      ;
      """

}
