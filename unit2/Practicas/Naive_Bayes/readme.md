# <p align="center"> Tecnológico Nacional de México </p>
```scala
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
```

Load data in LIBSVM storage format as a DataFrame.
```scala
val data = spark.read.format("libsvm").load("C:/Users/brise/Documents/GitHub/NaiveBayes/sample_libsvm_data.txt")

println ("Numero de lineas en el archivo de datos:" + data.count ())
```

Show 20 lines by default
```scala
data.show()
```

Randomly divide the data set into training set and test set according to the given weights. You can also specify a seed
```scala
val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), 100L) // El resultado es el tipo de matriz, y la matriz almacena los datos del tipo DataSet
```

Incorporate into training set (fit operation) to train a Bayesian model
```scala
val naiveBayesModel = new NaiveBayes().fit(trainingData)
```

The model calls transform () to make predictions and generate a new DataFrame
```scala
val predictions = naiveBayesModel.transform(testData)
```

Prediction results data output
```scala
predictions.show()
```
 Model accuracy assessment
```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

val precision = evaluator.evaluate (predictions) // Precisión

println ("tasa de error =" + (1-precision))
```
