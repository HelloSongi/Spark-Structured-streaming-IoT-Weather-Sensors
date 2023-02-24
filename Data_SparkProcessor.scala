import org.apache.spark.sql.types._
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._
import com.datastax.oss.driver.api.core.uuid.Uuids 
import com.datastax.spark.connector._

// Define the Cassandra configuration
val CASSANDRA_HOST = "localhost" // Change this if your Cassandra instance is running in a different server
val CASSANDRA_KEYSPACE_NAME = "weatherSensors"
val CASSANDRA_TABLE_NAME = "weather"
val CASSANDRA_CONFIG = Map(
  "spark.cassandra.connection.host" -> CASSANDRA_HOST,
  "spark.cassandra.output.consistency.level" -> "LOCAL_ONE",
  "spark.cassandra.auth.username" -> "Weather_metrics", // Change this to your Cassandra username
  "spark.cassandra.auth.password" -> "your_password" // Change this to your Cassandra password
)

// Define the schema for the incoming JSON data
val schema = new StructType()
  .add("main", new StructType()
    .add("temp", DoubleType)
    .add("humidity", DoubleType)
    .add("pressure", DoubleType)
  )
  .add("rain", new StructType()
    .add("1h", DoubleType)
  )
  .add("dt", LongType)

// Define the streaming query
val streamingQuery = spark
  .readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", "localhost:9092") // Change this if your Kafka Broker is running in a different server
  .option("subscribe", "weather") //kafka topic
  .option("startingOffsets", "latest")
  .load()
  .select(from_json(col("value").cast("string"), schema).alias("data"))
  .select(
    col("data.main.temp").alias("temperature"),
    col("data.main.humidity").alias("humidity"),
    col("data.main.pressure").alias("pressure"),
    col("data.rain.1h").alias("rain"),
    (col("data.dt")/1000).cast("timestamp").alias("date")
  )
  .writeStream
  .foreachBatch { (batchDF: DataFrame, batchId: Long) =>
    batchDF
      .selectExpr("uuid() as id", "temperature", "humidity", "pressure", "rain", "date")
      .write
      .cassandraFormat(CASSANDRA_TABLE_NAME, CASSANDRA_KEYSPACE_NAME, CASSANDRA_CONFIG)
      .mode("append")
      .save()
  }
  .start()

//This waits for the streaming query to terminate
streamingQuery.awaitTermination()
