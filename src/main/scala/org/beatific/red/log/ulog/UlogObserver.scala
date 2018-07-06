package org.beatific.red.log.ulog

import org.beatific.people.people.Observer
import org.beatific.people.utils.ImplicitUtils._
import org.beatific.people.event.Event
import java.nio.charset.Charset
import org.springframework.stereotype.Component

import java.util.concurrent.ExecutorService
import java.util.Date

@Component
class UlogObserver extends Observer[UlogEvent] {

  implicit val pool: ExecutorService = time.pool

  val file = """D:\source\scala\workspace\red\log\online_20180320.log""".af
//  var lastIndex: Long = file.size
  var lastIndex: Long = 0

  def observe() {
    lastIndex = file.readLines(strs => { (this >> "UlogAnalyst")(strs) }, "EUC-KR", lastIndex)
  }
}
