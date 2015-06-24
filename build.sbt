import SbtKitPre._

lazy val playping = project in file(".")

   name := "playping"
version := "0.1.0-SNAPSHOT"

enablePlugins(PlayScala)
disablePlugins(PlayLayoutPlugin)

      scalaVersion := "2.11.6"
crossScalaVersions := Seq(scalaVersion.value)

scalacOptions ++= Seq("-encoding", "utf8")
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
scalacOptions  += "-language:higherKinds"
scalacOptions  += "-language:implicitConversions"
scalacOptions  += "-language:postfixOps"
scalacOptions  += "-Yinline-warnings"
scalacOptions  += "-Xfuture"
scalacOptions  += "-Yinline-warnings"
scalacOptions  += "-Yno-adapted-args"
scalacOptions  += "-Ywarn-dead-code"
scalacOptions  += "-Ywarn-numeric-widen"
scalacOptions  += "-Ywarn-value-discard"

scalacOptions in (Compile, console) -= "-Ywarn-unused-import"

maxErrors := 5
triggeredMessage := Watched.clearWhenTriggered

routesGenerator := InjectedRoutesGenerator

initialCommands in console += "\nimport playping._"

parallelExecution in Test := true
fork in Test := false

fork in run := true
cancelable in Global := true

sources in (Compile, doc) := Nil
publishArtifact in (Compile, packageDoc) := false

TaskKey[Unit]("stop") := {
  val pidFile = (stagingDirectory in Universal).value / "RUNNING_PID"
  if (!pidFile.exists) sys error "App not started!"
  val pid = IO read pidFile
  s"kill $pid".!
  println(s"Stopped application with process ID $pid")
}

watchSources ++= (baseDirectory.value * "*.sbt").get
watchSources ++= (baseDirectory.value / "project" * "*.scala").get
