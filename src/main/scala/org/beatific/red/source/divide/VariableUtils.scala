package org.beatific.red.source.divide

import scala.annotation.tailrec

object VariableUtils {

  def definePattern(s :String) = (s + """#define\s*""" + s + """\s*"(\w+)"""").r
  def equalPattern(s :String) = (s + """\s*=\s*(\d+)""").r
  def copyPattern(s :String) = ("""strn?cpy\(\s*&?""" + s + """\s*,\s*"([\w|\-\.\>]+)"\s*,\s*\w+\s*\)\s*;""").r
  def copyPatternRetry(s :String) = ("""strn?cpy\(\s*&?""" + s + """\s*,\s*([\w|\-\.\>]+)\s*,\s*\w+\s*\)\s*;""").r
  def equalPatternRetry(s :String) = (s + """\s*=\s*"(\w+)"""").r
  
  def value(source: String, variable: String):Option[String] = {
    
    @tailrec
    def valueOf(source: String, variable: String):Option[String] = {
      
      definePattern(variable) findFirstMatchIn source match {
        case Some(m) => Some(m.group(1))
        case None => equalPattern(variable) findFirstMatchIn source match {
          case Some(m) => Some(m.group(1))
          case None => copyPattern(variable) findFirstMatchIn source match {
            case Some(m) => Some(m.group(1))
            case None => copyPatternRetry(variable) findFirstMatchIn source match {
              case Some(m) => valueOf(source, m.group(1))
              case None => equalPatternRetry(variable) findFirstMatchIn source match {
                case Some(m) => valueOf(source, m.group(1))
                case None => None
              }
            }
          }
        }
      }
    }
    
    valueOf(source, variable)
  }
}