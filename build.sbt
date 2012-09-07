import de.johoop.jacoco4sbt._
import JacocoPlugin._


organization := "nielinjie"

name := "alltags"


scalaVersion := "2.9.1"

resolvers += ScalaToolsSnapshots

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.6.4",
  "net.databinder" %% "unfiltered-jetty" % "0.6.4",
  "net.databinder" %% "unfiltered-json" % "0.6.4",
  "org.clapper" %% "avsl" % "0.3.6",
  "net.databinder" %% "dispatch-lift-json" % "0.8.5",
  	"nielinjie" %%  "util.io" % "1.0", 
  	"nielinjie" %% "util.data" % "1.0",
  "net.databinder" %% "unfiltered-spec" % "0.6.4" % "test",
  "org.specs2" %% "specs2" % "1.7.1" % "test"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2"
)

seq(jacoco.settings : _*)
