package org.apple

import com.amazonaws.services.glue.GlueContext
import com.amazonaws.services.glue.util.Job
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import scala.collection.JavaConverters._

object code1 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext()
    val glueContext = new GlueContext(sc)
    val spark = glueContext.getSparkSession

    spark.range(1,1000).show()
  }
}