import org.apache.spark.ml.feature. {VectorAssembler, StringIndexer}
import org.apache.spark.sql.SparkSession
import org.apache.log4j._

////////////////////Inicio de sesion///////////////////////////////
val Spark = SparkSession.builder().getOrCreate()

////////////////////////Cargar archivo csv/////////////////////////
val df = spark.read.option("header", "true").option("inferSchema","true").option("delimiter", ";")csv("/Users/admin/Documents/Github/Datos_Masivos/bank-full.csv")

df.printSchema()

////////////////////////Tranformacion de los datos///////////////////////

val label = new StringIndexer().setInputCol("y").setOutputCol("label")
val labeltransform = label.fit(df).transform(df)

val Features = (new VectorAssembler(). setInputCols (Array ("balance", "duration", "campaign", "previous")).setOutputCol("features"))
val featurestransform = Features.transform(labeltransform)

val df2 = featurestransform.select("features", "label")