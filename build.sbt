
lazy val akkaHttpVersion = "10.1.7"
lazy val akkaVersion = "2.5.21"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.12"
    )),
    name := "UserSorting-Test-Solution",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "net.liftweb" %% "lift-json" % "2.6.3"
    )
  )
