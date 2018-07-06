package org.beatific.red.source

class SourceInfo(name: String) {

  var includes: List[String] = List()
  var function: List[String] = List()
  var dlcall: List[String] = List()
  var staticCall: List[String] = List()
  var tpcall: List[String] = List()
  var links: List[String] = List()
}