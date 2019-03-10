package com.example.services

import com.example.poco.User
import com.example.util.Constants._
import com.example.util.Logging
import net.liftweb.json.{ DefaultFormats, parse }

import scala.concurrent.Future

object UserService extends Logging {

  def getSortedUsers: Future[Option[List[User]]] = {
    import com.example.services.WebService._

    // GET JSON FROM REST SERVICE
    val response: Future[String] = get(URL)

    response.map { json =>

      // EXTRACT USERS
      implicit val formats = DefaultFormats
      val usersOpt: Option[List[User]] = parse(json).extractOpt[List[User]]

      // PROCESS IF USERS EXIST
      usersOpt match {
        case Some(users) =>
          info(s"Total Users ${users.length}")
          // SORTING USERS
          Some(users.sortBy(_.address.geo.distance(geoPoint)))
        case _ =>
          warn("No User in response")
          None
      }
    }
  }

  def outFormat(users: List[User]): List[List[String]] = {
    val header = List("id", "name", "email", "company name", "latitude", "longitude")
    header ::
      users.map { user =>
        List(user.id, user.name, user.email, user.company.name, user.address.geo.lat, user.address.geo.lng).map(_.toString)
      }
  }
}

