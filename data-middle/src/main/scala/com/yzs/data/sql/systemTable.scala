package com.yzs.data.sql

object systemTable {
  val ColumnType =
    """
      select  type  from system.columns
      where database=?
      and  table =?
      and  name=?
      ;
      """
  val ColumnArray =
    """
      select  name  from system.columns
      where database=?
      and  table =?
      ;
      """
  val KeyColumnArray =
    """
      select  name  from system.columns
      where database=?
      and  table =?
      and is_in_primary_key=1
      ;
      """

}
