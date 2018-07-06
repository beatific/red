package org.beatific.red.source.divide

object DlcallDivider {
  val dlcallPatten = """mpfm_dlcall\s*\(\s*"(\w+)"""".r
  val dlcallPattenRetry = """mpfm_dlcall\s*\(\s*(\w+)""".r

  def apply(s: String): List[String] = {
    val dlcallList1 = (dlcallPatten findAllMatchIn s) map (_ group 1) toList
    val dlcallList2 = (dlcallPattenRetry findAllMatchIn s) flatMap (
      m => VariableUtils.value(s, m group 1) match {
        case Some(data) => List(data)
        case None       => Nil
      }) toList

    dlcallList1 ::: dlcallList2
  }

}