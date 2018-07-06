package org.beatific.red

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.TaskScheduler
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@EnableScheduling
@SpringBootApplication
class SpringBootConfig {
//  @Bean 
//  def taskScheduler():TaskScheduler = new ConcurrentTaskScheduler()
  
  @Bean 
  def taskScheduler():TaskScheduler = { 
     val taskScheduler :ThreadPoolTaskScheduler = new ThreadPoolTaskScheduler()
     taskScheduler.setPoolSize(1)
     taskScheduler 
   }

}