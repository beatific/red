package org.beatific.red.source.divide

object IncludeDivider {
  val includePattern = """#include\s*<\s*(\w+).h\s*>""".r
  
  def apply(s : String) :List[String] = {
    (includePattern findAllMatchIn s) map (_ group 1) toList
  }
}