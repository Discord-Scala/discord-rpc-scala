name := "discord-rpc-scala"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.github.MinnDevelopment" % "Java-DiscordRPC" % "ac5097ee9c"
)

resolvers ++= Seq(
  "jcenter" at "https://jcenter.bintray.com",
  "jitpack" at "https://jitpack.io"
)