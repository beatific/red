package org.beatific.red.log.ulog

import org.beatific.people.utils.FileUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException
import java.io.File
import java.io.DefaultFileSystem
import java.io.FileSystem
import org.springframework.stereotype.Component

@Component
class UlogFilter {

//    def file(child: String, parent: File) = {
//      new File(parent.getAbsolutePath + File.separator + child)
//    }
//  
//    def filelist(dir: String): Array[String] = {
//      filelist(new File(dir))
//    }
//  
//    def filelist(dir: File): Array[String] = {
//  
//      val files = dir.list
//      files ++ files.filter(file(_, dir).isDirectory).flatMap(directory => filelist(file(directory, dir)))
//    }
//
//    val logpattern = """\(E\) (\d+):(\d+):(\d+[A-Z]+\d+_*.+):\d+:(\w+)\s?:(\w+)\s?:(\w+\.c):""".r
//    val sourceList: Array[String] = filelist("""D:\source\scala\workspace\red\files""").filter(path => path.endsWith(".c"))
  val hostname: String = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream)).readLine()
  
  def apply(text: String): Option[Ulog] = {

      text match {
        case txt if (text contains "pfm") && (text startsWith "(E)") => {
          text.split(":") match {
            case splited if (splited.length > 7) && (splited(6) contains "pfm") => Some(Ulog(splited(0).substring(4) + splited(1), splited(2), splited(4).trim, splited(5).trim, splited(6), hostname, text))
            case _ => None
          }
        }
        case _ => None
      }

//        logpattern.findFirstMatchIn(text) match {
//          case Some(m) => {
//    
//            Ulog(m.group(1) + m.group(2), m.group(3), m.group(4), m.group(5), m.group(6), hostname, text) match {
//              case Ulog(time, id, service, caller, program, hostname, log) if sourceList.contains(program) => None
//              case Ulog(time, id, service, caller, program, hostname, log)                                 => Some(Ulog(time, id, service, caller, program, hostname, log))
//            }
//          }
//          case None => None
//        }
  }
}