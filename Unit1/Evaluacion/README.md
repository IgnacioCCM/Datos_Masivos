### Practica de evaluacion

#### Sesion
```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().appName("Spark CSV Reader").getOrCreate()
```

####  Cargar archivo
```scala
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/Users/admin/Documents/Github/Datos_Masivos/Netflix_2011_2016.csv")
df.show()
```

#### Columnas
```scala
df.columns
```

####  Esquema
```scala
df.printSchema()
```

#### Primeras 5
```scala
df.head(5)

for(row <- df.head(5)){
    println(row)
}
```

#### describe
```scala
df.describe()
```

#### Nuevo dataframe
```scala
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
```

#### Maximo en columna close
```scala
df.select(max("Close")).show()
```

```scala
//La columna close hace referencia a la cifra monetaria en la que cerro por dia 
```


#### Max y Min columna volume
```scala
df.select(max("Volume")).show()
df.select(min("Volume")).show()
```