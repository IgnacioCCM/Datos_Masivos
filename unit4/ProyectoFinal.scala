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

/////////////////////////////////////////////////////////////////SVM////////////////////////////////////////////////////////////////////////
import org.apache.spark.ml.classification.LinearSVC

// Split data into training (60%) and test (40%).
val splits = df2.randomSplit(Array(0.7, 0.3), seed = 11L)
val training = splits(0)
val test = splits(1)

val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

// Realizamos un fit para ajustar el modelo.
val lsvcModel = lsvc.fit(training)

// Imprime los coeficientes e intercepta para el Linear SVC.
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")