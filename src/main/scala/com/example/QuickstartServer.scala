package com.example

//#quick-start-server
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.example.services.Routes
import com.example.util.Logging

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object QuickstartServer extends App with Routes with Logging {

  //#server-bootstrapping
  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  //#server-bootstrapping

  //#http-server
  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "localhost", 8080)

  serverBinding.onComplete {
    case Success(bound) =>
      info(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
      info(s"Press Enter to stop server.")
    case Failure(e) =>
      error(s"Server could not start!", e)
      system.terminate()
  }
}
