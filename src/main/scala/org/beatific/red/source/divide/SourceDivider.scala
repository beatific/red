package org.beatific.red.source.divide

sealed trait SourceDivision
case class Include(source: String) extends SourceDivision
case class Function(function: String) extends SourceDivision
case class Dlcall(function: String) extends SourceDivision
case class Tpcall(function: String) extends SourceDivision
case class StaticCall(function: String) extends SourceDivision
case class Link(source: String) extends SourceDivision
case object NotExist extends SourceDivision

object SourceDivider {
  val IncludePattern = """#include\s+<(\w+)>""".r

  val StaticFunctionPattern = """static\s+\w+\s+(\w+)\s*\([\w+\s\*\[\]\.,]+\)\s*{""".r
  val FunctionPattern = """\w+\s+(\w+)\s*\([\w+\s\*\[\]\.,]+\)\s*{""".r
  val DlcallPatten = """mpfm_dlcall\s*\(\s*\"(\w+)\"""".r
  val TplinkCallPattern = """mpfmlink\s*\(\s*&\s*([\w_]+)\s*\)""".r
  val TpcallPattern = """mpfmlink\s*\(\s*&\s*([\w_]+)\s*\)""".r
  val LinkPattern = """""".r
  val StaticCallPattern = """""".r

  def unapply(s: String): SourceDivision = {

    s match { 
      case IncludePattern(c)    => Include(c)
      case FunctionPattern(c)   => Function(c)
      case DlcallPatten(c)      => Dlcall(c)
      case TpcallPattern(c)     => Tpcall(c)
      case LinkPattern(c)       => StaticCall(c)
      case StaticCallPattern(c) => Link(c)
      case _                    => NotExist
    }
  }
}