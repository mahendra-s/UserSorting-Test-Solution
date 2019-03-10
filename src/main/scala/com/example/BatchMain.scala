package com.example

import java.io.{ BufferedWriter, File, FileWriter }

import com.example.poco.User
import com.example.services.UserService
import com.example.util.Constants._
import com.example.util.Logging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object BatchMain extends App with Logging {

  import UserService._

  UserService.getSortedUsers
    .map {
      case Some(sorted: List[User]) => write(OUTFILE, sorted)
      case None => warn("Nothing to write in file")
    }
    .onComplete {
      case Success(_) => info(s"Process Completed.")
      case Failure(ex) => error(s"Process Ended with error.", ex)
    }

  def write(path: String, users: List[User]): Unit = {
    val file = new File(path)
    info(s"Writing file at: ${file.getPath}")
    val writer = new BufferedWriter(new FileWriter(file))
    outFormat(users).foreach { line =>
      writer.write(s"${line.mkString(COLUMN_SEPARATOR)}$LINE_SEPARATOR")
    }
    writer.flush()
    writer.close()
  }
}
