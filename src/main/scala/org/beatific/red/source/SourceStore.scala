package org.beatific.red.source

import scala.util.matching.Regex
import scala.io.Source
import org.beatific.people.utils.ImplicitUtils._
import org.springframework.stereotype.Component
import scala.collection.immutable.StringOps
import scalax.file.Path
import java.io.File

@Component
class SourceStore {

  var map: Map[String, List[String]] = Map[String, List[String]]()
  val _TARGET_FILE_ = "D:/source/scala/workspace/red/default/error"
  val _SOURCE_DIRECTORY_ = "D:/source/scala/workspace/red/source"

  def _saveErrorLogOnSource() {
    _saveErrorLogOnSourceInDirectory(_SOURCE_DIRECTORY_)
  }

  def _saveErrorLogOnSourceInDirectory(directory: String) {

    new File(directory).listFiles().foreach {
      case file if file.isFile() => {
        val filename = file.getAbsolutePath
        filename match {
          case filename if filename.endsWith(".c") || filename.endsWith(".pc") => {

            val key = filename.substring(filename.lastIndexOf(File.separator) + 1)
            val value = """PFM_ERRS\"([가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9_%\[\]]+)\"""".r
              .findAllMatchIn(Source.fromFile(filename)("UTF-8").mkString.refine())
              .map(str => """%[a-z]""".r.replaceAllIn(str.group(1), """[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9_%\[\]\"]*""")).toList

            map = map + (key -> value)
          }
        }
      }

      case directory if directory.isDirectory() => _saveErrorLogOnSourceInDirectory(directory.getAbsolutePath)
    }
  }

  def dump() = synchronized {
    _saveErrorLogOnSource()
    map.writeObject(_TARGET_FILE_)
  }

  def load() = synchronized {
    map = _TARGET_FILE_.loadObject()
  }

  def matching(source: String, error: String): Boolean = synchronized {
    map.getOrElse(source, List[String]()).map {
      case pattern if (pattern.r.findAllIn(error).count(str => !str.isEmpty()) > 0) => 1
    }.sum > 0
  }
}