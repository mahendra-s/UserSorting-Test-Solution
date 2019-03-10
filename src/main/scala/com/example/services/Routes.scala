package com.example.services

import akka.NotUsed
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, HttpResponse }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source
import com.example.poco.User
import com.example.util.Constants._

import scala.concurrent.{ ExecutionContext, Future }

trait Routes {
  implicit val executionContext: ExecutionContext
  val routes: Route =
    path("alive") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h3> Hello from User Sort System</h3>"))
      }
    } ~
      pathPrefix("api" / "1.0") {
        path(OUTFILE) {
          get {
            import UserService._
            val response: Future[HttpResponse] =
              getSortedUsers.map {
                case Some(resp: List[User]) =>
                  val lines: List[String] = outFormat(resp).map(_.mkString(COLUMN_SEPARATOR).+(LINE_SEPARATOR))
                  val chunked: Source[ChunkStreamPart, NotUsed] = Source.fromIterator(() => lines.iterator).map(ChunkStreamPart.apply)
                  HttpResponse(entity = HttpEntity.Chunked(ContentTypes.`text/csv(UTF-8)`, chunked))
                case None => HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, "<b>No Users to Write</b>"))
              }
            completeOrRecoverWith(response) { extraction =>
              failWith(extraction)
            }
          }
        }
      }
}
