lazy val playping = project in file(".")

version := "0.1.0-SNAPSHOT"

enablePlugins(PlayScala)
disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.11.8"

      maxErrors := 15
triggeredMessage := Watched.clearWhenTriggered

scalacOptions ++= Seq("-encoding", "utf8")
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
scalacOptions  += "-language:higherKinds"
scalacOptions  += "-language:implicitConversions"
scalacOptions  += "-language:postfixOps"
scalacOptions  += "-Xfuture"
scalacOptions  += "-Yinline-warnings"
scalacOptions  += "-Yno-adapted-args"
scalacOptions  += "-Ywarn-dead-code"
scalacOptions  += "-Ywarn-numeric-widen"
scalacOptions  += "-Ywarn-unused"
scalacOptions  += "-Ywarn-unused-import"
scalacOptions  += "-Ywarn-value-discard"

routesGenerator := InjectedRoutesGenerator

initialCommands in console += "\nimport playping._"

             fork in Test := false
parallelExecution in Test := true

         fork in run := true
cancelable in Global := true

noDocs

TaskKey[Unit]("stop") := {
  val pidFile = (stagingDirectory in Universal).value / "RUNNING_PID"
  if (!pidFile.exists) sys error "App not started!"
  val pid = IO read pidFile
  s"kill $pid".!
  pidFile.delete
  println(s"Stopped application with process ID $pid")
}

val noDocs = Def.settings(sources in (Compile, doc) := Nil, publishArtifact in (Compile, packageDoc) := false)
val noPackage = Def.settings(Keys.`package` := file(""), packageBin := file(""), packagedArtifacts := Map())
val noPublish = Def.settings(
  publishArtifact := false,
  publish         := {},
  publishLocal    := {},
  publishM2       := {},
  publishTo       := Some(Resolver.file("devnull", file("/dev/null")))
)
val noArtifacts = Def.settings(noPackage, noPublish)

watchSources ++= (baseDirectory.value * "*.sbt").get
watchSources ++= (baseDirectory.value / "project" * "*.scala").get
