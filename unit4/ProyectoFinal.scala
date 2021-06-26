import org.apache.spark.ml.feature. {VectorAssembler, StringIndexer}
import org.apache.spark.sql.SparkSession
import org.apache.log4j._

////////////////////Inicio de sesion///////////////////////////////
val Spark = SparkSession.builder().getOrCreate()

////////////////////////Cargar archivo csv/////////////////////////
val df = spark.read.option("header", "true").option("inferSchema","true").option("delimiter", ";")csv("/Users/admin/Documents/Github/Datos_Masivos/bank-full.csv")

df.printSchema()