package org.beatific.red.log.ulog

import org.beatific.people.people.Worker
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger
import org.springframework.beans.factory.annotation.Autowired
import org.beatific.people.utils.ImplicitUtils._

@Component
class UlogAnalyst extends Worker[List[String]] {
  
  @Autowired
  val filter : UlogFilter = null
  val file = """D:\source\scala\workspace\red\log\refine.log""".af

  def work(works: List[String]): Unit = {

    works.foreach(
      working => {
        filter(working) match {
          case Some(Ulog(time, id, service, caller, program, hostname, log)) => file.write(log)
          case None =>
        }
      })
  }
}