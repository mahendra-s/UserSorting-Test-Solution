package com.example.services

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.example.util.Logging

import scala.concurrent.{ ExecutionContext, Future }

object WebService extends Logging {

  implicit val system = ActorSystem("Client_WebService")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  def get(url: String): Future[String] = {
    val request = HttpRequest(uri = url)
    info(s"getting data from web service$request")
    Http()
      .singleRequest(request)
      .flatMap { res => Unmarshal(res.entity).to[String]
      }
  }
}
