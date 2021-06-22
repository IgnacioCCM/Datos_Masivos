# <p align="center"> Linear Support Vector Machine </p>

We import the "LinearSVC" library, this binary classifier optimizes the hinge loss using the OWLQN optimizer.
```scala
import org.apache.spark.ml.classification.LinearSVC
```

We import and create the session in spark.
```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder.appName("LinearSVCExample").getOrCreate()
```

We load the training data.
```scala
val training = spark.read.format("libsvm").load("/Archivos/sample_libsvm_data.txt")
```

We set the maximum number of iterations and the regularization parameter.
```scala
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```

We perform a fit to adjust the model.
```scala
val lsvcModel = lsvc.fit(training)
```

Print the coefficients and intercepts for the Linear SVC.
```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```
