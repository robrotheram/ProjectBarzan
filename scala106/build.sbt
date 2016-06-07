

name := "scala106"

version := "1.0"

scalaVersion := "2.10.6"

libraryDependencies += "org.mongodb" %% "casbah" % "3.1.1"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.1" % "provided"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.1" % "provided"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.1"  exclude("org.spark-project.spark", "unused")
