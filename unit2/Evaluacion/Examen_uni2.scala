cd /usr/local/
cd spark
bin/spark-shell
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature. {VectorAssembler, StringIndexer}
import org.apache.spark.sql.SparkSession

////////////////////Inicio de sesion///////////////////////////////
val Spark = SparkSession.builder().getOrCreate()

////////////////////////Cargar archivo csv/////////////////////////
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/Users/admin/Documents/Github/Datos_Masivos/Iris.csv")

/////////////////////limpieza de los datos////////////////////////
val data = df.na.drop()

val label = new StringIndexer().setInputCol("species").setOutputCol("label")
val labeltransform = label.fit(data).transform(data)

val Features = (new VectorAssembler(). setInputCols (Array ("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features"))
val featurestransform = Features.transform(labeltransform)

val df2 = featurestransform.select("features", "label")

labeltransform.show()
featurestransform.show()
df2.show()

////////////////Nombre de las columnas////////////////
df.columns

//////////////////////Esquema////////////////////////
df.printSchema()

//////////////////Primeras 5 lineas///////////////////
df.show(5)

//////////////////////Metodo describe/////////////////////////
df.describe()

//////////////////////Modelo de clasificacion/////////////////////
val splits = df2.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val model = trainer.fit(train)

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")