package org.beatific.red

import org.junit.Test
import scala.io.Source
import scala.util.matching.Regex

class ErrerReaderTests {

//  @Test
  def testRemoveCommentsForC() {
    
//    var lines = reader.removeCommentsForC(Source.fromFile("D:/source/scala/workspace/red/zngmbcom00020.pc")("UTF-8").getLines().toList).foreach(println)
    
    val test  = """abcdefghiklmn
                   asdfasfdasfdaasfafd
                   /**********************
                   ********"adasdfdasfPFM_ERRS("호호호호")asdfafasdf"**************
                   ****************/   
                   PFM_ERRS("하하하하")"""
    
    val pattern :Regex = "[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9\"_]".r
    val Pattern = "PFM_ERRS\"([가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9_]+)\"".r
    
    val text = pattern.findAllIn(test).mkString
    Pattern.findAllMatchIn(text).foreach(m => println(m.group(1)))
    
  }
}