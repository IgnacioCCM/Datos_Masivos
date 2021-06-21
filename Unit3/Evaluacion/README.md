# <p align="center" > Evaluacion </p> 

Importar sesion en spark
```scala
import org.apache.spark.sql.SparkSession 
```

Minimizando errores
```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

Inicio de sesion
```scala
val Spark = SparkSession.builder().getOrCreate()
```

Libreria KMeans
```scala
import org.apache.spark.ml.clustering.KMeans
```

Carga del dataset
```scala
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/Users/admin/Documents/Github/Datos_Masivos/BigData/Scala_Kmeans/Wholesale customers data.csv")
```

Creacion de columna features_data
```scala
val feacture_data = df.select("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")
```

Importacion de librerias
```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.mllib.linalg.Vector
```

Creacion un nuevo objeto Vector Assembler
```scala
val assembler = (new VectorAssembler(). setInputCols (Array ("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features"))
```

Transformacion a feature_data
```scala
val featurestransform = assembler.transform(feacture_data)

val df2 = featurestransform.select("features")
```

Modelo Kmeans
```scala
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(df2)
```

Evaluacion con WSSE
```scala
val WSSE = model.computeCost(df2)
println(s"Within set sum of Squared Errors = $WSSE")

println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```
