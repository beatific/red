package org.beatific.red.source.divide

object FunctionDivider {
  val functionNamePattern = """\w+\s+(\w+)\s*\([\w+\s\*\[\]\.,]+\)\s*{""".r
  val functionPattern = """(\w*\s*\w+\s+\w+\s*\([\w+\s\*\[\]\.,]+\)\s*{)""".r

  def apply(s: String): Map[String, String] = {
    
    var functionMap: Map[String, String] = Map()
    (functionPattern findAllMatchIn s) map (_ group 1) foreach {
      something =>
        {
          val function = findfunction(s.substring(s.indexOf(something)))
          
          functionNamePattern.findFirstMatchIn(something) match {
            case Some(m) => functionMap += (m.group(1) -> function) 
            case None    => ""
          }

        }
    }
    
    functionMap

  }

  private def findfunction(source: String): String = {
    var str = ""
    var index = 0

    source foreach {
      case c if c == '{' => if (index >= 0) { index = index + 1; str += c }
      case c if c == '}' => if (index > 1) { index = index - 1; str += c } else if (index == 1) index = -1
      case c             => if (index > 0) str + c
    }

    str
  }
}