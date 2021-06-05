![](docs/portadatcnm.png)

# <p align="center"> Tecnológico Nacional de México </p>

//Importamos la librería "LinearSVC", este clasificador binario optimiza la pérdida de bisagra utilizando el optimizador OWLQN.  
```scala
import org.apache.spark.ml.classification.LinearSVC
```

// Importamos y creamos la sesión en spark.
```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder.appName("LinearSVCExample").getOrCreate()
```

// Cargamos los datos de entrenamiento.
```scala
val training = spark.read.format("libsvm").load("/Archivos/sample_libsvm_data.txt")
```

// Establecemos el número máximo de iteraciones y el parámetro de regularización.
```scala
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```

// Realizamos un fit para ajustar el modelo.
```scala
val lsvcModel = lsvc.fit(training)
```

// Imprime los coeficientes e intercepta para el Linear SVC.
```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```
