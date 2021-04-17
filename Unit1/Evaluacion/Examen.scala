/* 1. Comienza una simple sesión Spark.
2. Cargue el archivo Netflix Stock CSV, haga que Spark infiera los tipos de datos.
3. ¿Cuáles son los nombres de las columnas?
4. ¿Cómo es el esquema?
5. Imprime las primeras 5 columnas.
6. Usa describe () para aprender sobre el DataFrame.
7. Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la
relación entre el precio de la columna “High” frente a la columna “Volume” de
acciones negociadas por un día. (Hint: Es una operación de columnas).
8. ¿Qué día tuvo el pico mas alto en la columna “Close”?
9. Escribe con tus propias palabras en un comentario de tu codigo. ¿Cuál es el
significado de la columna Cerrar “Close”?
10. ¿Cuál es el máximo y mínimo de la columna “Volume”?
*/

///////////////////sesion//////////////////
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().appName("Spark CSV Reader").getOrCreate()

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

