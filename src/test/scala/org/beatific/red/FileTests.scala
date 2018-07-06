package org.beatific.red

import scalax.io._
import scalax.io.Resource
import org.junit.Test

class FileTests {

//  @Test
  def test() {
    val file: Seekable = Resource.fromFile("c:/test.txt")
    implicit val codec = scalax.io.Codec.UTF8

    // delete all data from the file
    // or more specifically only keep the data from the beginning of the
    // file up to (but not including) the first byte
    file truncate 0

    val seedData = "Entering some seed data into the file"
    file write seedData

    // verify seed data was correctly written (in REPL)
    println(file.string)

    // write "first" after character 9
    // if the file is < 9 characters an underflow exception is thrown
    // if the patch extends past the end of the file then the file is extended
    // Note: The offset is always dependent on type of data being written
    // for example if the data written is a string it will be 9 characters
    // if the data is bytes it will be 9 bytes
    file patch (9, "first", OverwriteAll)

    // dumping to REPL will show: Entering firstseed data into the file
    println(file.string)

    // patch at position 0 and replace 100 bytes with new data
    file.patch(0, seedData, OverwriteSome(100))

    // dumping to REPL will show the unchanged seedData once again
    println(file.string)

    // Overwrite only 4 bytes starting at bytes 9.
    // the extra bytes will be inserted
    // In other words the "some" word of seed data will
    // be replaced with second
    // Warning: This is an overwrite and an insert
    // inserts are expensive since it requires copying the data from
    // the index to end of file.  If small enough it is done in
    // memory but a temporary file is required for big files.
    file.patch(9, "second".getBytes(), OverwriteSome(4))

    // dumping to REPL will show: Entering second seed data into the file
    println(file.string)

    // reset file
    file.patch(0, seedData, OverwriteSome(100))

    // Replace 9 bytes with the 5 bytes that are provided
    // In other words: replace "some seed" with "third"
    file.patch(9, "third".getBytes(), OverwriteSome(9))

    // dumping to REPL will show: Entering third data into the file
    println(file.string)

    // reset file
    file.patch(0, seedData, OverwriteSome(100))

    // Insert a string at start of file
    file.insert(0, "newInsertedData ")

    // dumping to REPL will show: newInsertedData Entering some seed data into the file
    println(file.string)

    // reset file
    file.patch(0, seedData, OverwriteSome(100))

    //add !! to end of file
    file.append("!!")

    // dumping to REPL will show: Entering some seed data into the file!!
    println(file.string)
  }
}