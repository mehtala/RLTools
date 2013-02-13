import sbt._
import Keys._

case class ScalaVersionStr(major : Int, minor : Int, patchlevel : Int) {
  val featureVersionStr = major.toString() + "." + minor.toString()
  val fullVersionStr = featureVersionStr + "." + patchlevel
}

object ExampleBuild extends Build {
  val appScalaVersion = ScalaVersionStr(2, 10, 0)

  val localScalacOptions = Seq(
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  val localSettings = Seq(
    scalaVersion := appScalaVersion.fullVersionStr,
    scalacOptions := localScalacOptions
  )

  lazy val root = Project(id = "RLTools",
    base = file("."),
    settings = Project.defaultSettings ++ localSettings)
}