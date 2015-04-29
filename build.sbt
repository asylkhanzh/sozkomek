name := "y"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)


libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "2.4.0"

//libraryDependencies "com.typesafe" %% "play-plugins-mailer" % "2.1.0"

play.Project.playJavaSettings
