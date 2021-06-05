
```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
```

// Load the data stored in LIBSVM format as a DataFrame.
```scala
val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
```

// Split the data into train and test
```scala
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)
```

// specify layers for the neural network:
// input layer of size 4 (features), two intermediate of size 5 and 4
// and output of size 3 (classes)
```scala
val layers = Array[Int](4, 5, 4, 3)
```

// create the trainer and set its parameters
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```

// train the model
```scala
val model = trainer.fit(train)
```

// compute accuracy on the test set
```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```
