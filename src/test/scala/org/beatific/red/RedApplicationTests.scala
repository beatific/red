package org.beatific.red

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired
import org.beatific.red.source.SourceStore


@RunWith(classOf[SpringRunner])
//@SpringBootTest
class RedApplicationTests {
  
//  @Autowired
  val store :SourceStore = null
  
  @Test
	def contextLoads() {
    
//    store.dump()
//    store.load()
	}
}