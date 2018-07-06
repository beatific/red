package org.beatific.red.utils.asyncfile

import org.junit.Test
import java.nio.charset.Charset
import java.util.concurrent.ExecutorService
import org.beatific.people.utils.ImplicitUtils._

class SeekableFileTests {
  
//  @Test
  def testRead() {
    
    val file = """D:\source\scala\workspace\red\log\online_20180320.log""".af
//    file.read(printtool("read", _))
    val start = System.currentTimeMillis()
//    file.readEach(printtool("each", _))
//    file.readEach(s => {})
//    file.readLine(printtool("line", _))
    file.readLine(s => {})
    Thread.sleep(10000000)
  }
  
  def printtool(prefix: String, str :String) {
    println(prefix + ":" + str)
  }
}