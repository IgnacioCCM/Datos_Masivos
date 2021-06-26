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

/////////////////////////////////////////////////////////Decision Three////////////////////////////////////////////////////////////////
import  org.apache.spark.ml.Pipeline 
import  org.apache.spark.ml.classification.DecisionTreeClassificationModel 
import  org.apache.spark.ml.classification.DecisionTreeClassifier 
import  org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator 
import  org.apache .spark.ml.feature. { IndexToString ,  StringIndexer ,  VectorIndexer }

// Etiquetas de índice, agregando metadatos a la columna de etiquetas. 
// Encajar en el conjunto de datos completo para incluir todas las etiquetas en el índice. 
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(df2)

// Identifica automáticamente características categóricas e indexalas. 
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(df2)

// Divida los datos en conjuntos de prueba y entrenamiento (30% reservado para pruebas). 
val Array(trainingData, testData) = df2.randomSplit(Array(0.7, 0.3))

// Entrene un modelo DecisionTree. 
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

// Convertir etiquetas indexadas de nuevo a etiquetas originales. 
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// Cadena de indexadores y árbol en un Pipeline. 
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

// Modelo de train. Esto también ejecuta los indexadores. 
val model = pipeline.fit(trainingData)

// Hacer predicciones. 
val predictions = model.transform(testData)

// Seleccione filas de ejemplo para mostrar. En este caso solo seran 10
predictions.select("predictedLabel", "label", "features").show(10)

// Seleccione (predicción, etiqueta verdadera).
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

// calcule el error de prueba. 
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

// Mostrar por etapas la clasificación del modelo de árbol
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

/////////////////////////////////////////////////////////Logistic Regresion//////////////////////////////////////////////////////////////////
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.log4j._

Logger.getLogger("org").setLevel(Level.ERROR)

df.printSchema()

val label = new StringIndexer().setInputCol("y").setOutputCol("label")
val labeltransform = label.fit(df).transform(df)

val assembler = (new VectorAssembler(). setInputCols (Array ("balance", "duration", "campaign", "previous")).setOutputCol("features"))
val featurestransform = assembler.transform(labeltransform)

val df2 = featurestransform.select("features", "label")

val logregdata = df2.na.drop()


//val assembler = (new VectorAssembler(). setInputCols (Array ("balance", "duration", "campaign", "previous")).setOutputCol("features"))

val Array(training, test) = df2.randomSplit(Array(0.7, 0.3), seed = 12345)


val lr = new LogisticRegression()

// val pipeline = new Pipeline().setStages(Array(genderIndexer,embarkIndexer,embarkEncoder,assembler,lr))
val pipeline = new Pipeline().setStages(Array(genderIndexer,genderEncoder,assembler,lr))

val model = pipeline.fit(training)

val results = model.transform(test)


//Probar el modelo solo se puede con la libreria vieja
import org.apache.spark.mllib.evaluation.MulticlassMetrics

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

// Matriz de confusion
println("Confusion matrix:")
println(metrics.confusionMatrix)

metrics.accuracy
