#####Practica de evaluacion

####sesion
```scala
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().appName("Spark CSV Reader").getOrCreate()
```

///////////////Cargar archivo//////////////
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/Users/admin/Documents/Github/Datos_Masivos/Netflix_2011_2016.csv")
df.show()

/////////////////Columnas//////////////////
df.columns

//////////////////Esquema/////////////////
df.printSchema()

////////////////Primeras 5////////////////
df.head(5)

for(row <- df.head(5)){
    println(row)
}

/////////////////describe/////////////////
df.describe()

//////////////Nuevo dataframe/////////////
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))

//////////Maximo en columna close/////////
df.select(max("Close")).show()

/*
La columna close hace referencia a la cifra monetaria en la que cerro por dia 
*/


/////////Max y Min columna volume//////////
df.select(max("Volume")).show()
df.select(min("Volume")).show()