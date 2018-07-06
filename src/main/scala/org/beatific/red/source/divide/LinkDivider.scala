package org.beatific.red.source.divide

object LinkDivider {
   val linkPattern = """MY_LDLIBS\s*=([\s\-[a-z][0-9]\\]+)""".r
  
  def apply(s : String) :List[String] = {
    (linkPattern findAllMatchIn s) flatMap (_.group(1).replaceAll("""\""", """""").replaceAll("""\s""", """""").split("-l").toList) toList
  }
}
