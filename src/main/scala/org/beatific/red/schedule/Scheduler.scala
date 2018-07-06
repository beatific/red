package org.beatific.red.schedule

import org.springframework.scheduling.annotation.Scheduled
import org.beatific.people.people.Publisher
import org.springframework.stereotype.Component
import org.beatific.red.log.ulog.UlogEvent

@Component
class Scheduler {
  
  @Scheduled(cron="*/10 * * * * *")
  def readULog() {
    Publisher >> UlogEvent()
  }
}