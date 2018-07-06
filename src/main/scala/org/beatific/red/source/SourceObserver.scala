package org.beatific.red.source

import java.util.concurrent.ExecutorService

import org.beatific.people.event.Event
import org.beatific.people.people.Observer
import org.beatific.people.utils.FileUtils
import org.beatific.people.utils.ImplicitUtils._

case class SourceEvent() extends Event
class SourceObserver extends Observer[SourceEvent] {
  
  implicit val pool: ExecutorService = time.pool
  
  lazy val directory = FileUtils.filelist("""""")
  
  private def convertMakeFile() {
    
  }
  
  def observe() {
    directory.filter(filename => filename.endsWith(".c")) foreach (
      filename => {
        val file = filename.af
        val info :SourceInfo = new SourceInfo(filename)
      }
    )
  }
}