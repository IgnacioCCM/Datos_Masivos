////////////////////Importar sesion en spark///////////////////////
import org.apache.spark.sql.SparkSession 

/////////////////////////Minimizando errores///////////////////////
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

////////////////////Inicio de sesion///////////////////////////////
val Spark = SparkSession.builder().getOrCreate()

///////////////////////////Libreria KMeans/////////////////////////
import org.apache.spark.ml.clustering.KMeans

//////////////////////////Carga del dataset////////////////////////
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/Users/admin/Documents/Github/Datos_Masivos/BigData/Scala_Kmeans/Wholesale customers data.csv")

///////////////////Creacion de columna features_data////////////////
val feacture_data = df.select("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")

////////////////////Importacion de librerias///////////////////////
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.linalg.Vector

////////////Creacion un nuevo objeto Vector Assembler//////////////
val assembler = (new VectorAssembler(). setInputCols (Array ("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features"))

/////////////////////Transformacion a feature_data////////////////
val featurestransform = assembler.transform(feacture_data)

val df2 = featurestransform.select("features")

//////////////////////////Modelo Kmeans//////////////////////////
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(df2)

////////////////////Evaluacion con WSSE///////////////////////////
val WSSE = model.computeCost(df2)
println(s"Within set sum of Squared Errors = $WSSE")

println("Cluster Centers: ")
model.clusterCenters.foreach(println)
