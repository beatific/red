package org.beatific.red.sms

import org.beatific.people.people.Worker

case class SmsData(phonenumber : String, body: String, title: String)
class SmsSender extends Worker[SmsData] {

  def work(working: SmsData): Unit = {

    working match {
      case SmsData(phone, body, title) => 
    }
  }
}