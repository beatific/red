package org.beatific.red.source.divide

object TpcallDivider {

  val tpcallPatten = """tpcall\s*\(\s*"(\w+)"""".r
  val tpcallPattenRetry = """tpcall\s*\(\s*([^"\s]+)\s*,*,""".r

  val pfmlinkPatten = """mpfmlink\s*\(\s*&\s*([\w_]+)\s*\)""".r
  def pfmlinkappPattern(s: String) = ("""strncpy\s*\(\s*""" + s + """.link_app\s*,\s*"(\w+)"\s*,""").r
  def pfmlinkappPatternRetry(s: String) = ("""strncpy\s*\(\s*""" + s + """.link_app\s*,\s*(\w+)\s*,""").r

  def apply(s: String): List[String] = {
    val tpcallList1 = (tpcallPatten findAllMatchIn s) map (_ group 1) toList
    val tpcallList2 = (tpcallPattenRetry findAllMatchIn s) flatMap (
      m => VariableUtils.value(s, m group 1) match {
        case Some(data) => List(data)
        case None       => Nil
      }) toList

    val tpcallList3 = (pfmlinkPatten findAllMatchIn s) flatMap (
      m => {
        val pfmlinkList1 = pfmlinkappPattern(m group 1) findAllMatchIn s map (_ group 1) toList

        val pfmlinkList2 = pfmlinkappPatternRetry(m group 1) findAllMatchIn s flatMap (
          m => VariableUtils.value(s, m group 1) match {
            case Some(data) => List(data)
            case None       => Nil
          }) toList

        pfmlinkList1 ::: pfmlinkList2
      }) toList

    tpcallList1 ::: tpcallList2 ::: tpcallList3
  }
}