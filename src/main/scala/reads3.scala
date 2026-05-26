package org.apple

import com.amazonaws.services.glue.GlueContext
import com.amazonaws.services.glue.util.Job
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import scala.collection.JavaConverters._

// reads files from s3 and writes to s3

object reads3 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext()
    val glueContext = new GlueContext(sc)
    val spark = glueContext.getSparkSession

    val df = spark.read
      .format("csv")
      .option("path","s3://orders123456sree/sourceA/")
      .option("inferSchema",true)
      .load()

    df.show()

    df.write
      .format("parquet")
      .mode("overwrite")
      .option("path","s3://orders123456sree/sourcelocal/")
      .save()

  }
}
