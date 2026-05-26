
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "scala_awsglue5_project",
    idePackagePrefix := Some("org.apple"),
    // Point to AWS's specific S3 bucket for the Glue ETL library
    resolvers += "AWS Glue ETL" at "https://aws-glue-etl-artifacts.s3.amazonaws.com/release/"
  )


val sparkVersion = "3.5.4" // Glue 5.0 uses Spark 3.5.4

libraryDependencies ++= Seq(
  // Production Dependencies
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"  % sparkVersion % "provided",
  "com.amazonaws"    % "AWSGlueETL"  % "5.0.0" % "provided" ,

  // Testing Dependencies
  "org.scalatest"          %% "scalatest"  % "3.2.18"   % Test
)