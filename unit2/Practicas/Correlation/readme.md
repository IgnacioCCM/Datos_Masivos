# Correlation
We started with this library to have access to local matrices and Factory Methods for Vector.
```scala
import org.apache.spark.ml.linalg.{Matrix, Vectors}
```
Library to use the correlation method
```scala
import org.apache.spark.ml.stat.Correlation
```
Allows access to a row value through generic ordinal access, as well as primitive access
```scala
import org.apache.spark.sql.Row
```

Create dense and sparse vectors from their values, within the matrix
```scala
val data = Seq(
   (4, Seq((0, 1.0), (3, -2.0))),
  Vectors.dense(4.0, 5.0, 0.0, 3.0),
  Vectors.dense(6.0, 7.0, 0.0, 8.0),
  Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
)
```

The data is extracted from our matrix and a dataframe is created regarding the characteristics
```scala
val df = data.map(Tuple1.apply).toDF("features")
```
The Pearson correlation matrix is created using the dataframe that we just created and we ask for the first values with head
```scala
val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
```
We print the result
```scala
println(s"Pearson correlation matrix:\n $coeff1")
```
The Spearman correlation matrix is created using the dataframe that we just created and we ask for the first values with head
```scala
val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
```
We print the result
```scala
println(s"Spearman correlation matrix:\n $coeff2")
```



Hypothesis testing


The following library is used to apply methods to vectors 
```scala
import org.apache.spark.ml.linalg.{Vector, Vectors}
```
The chiSquare library is also used to perform the necessary calculations
```scala
import org.apache.spark.ml.stat.ChiSquareTest
```

The following sequence of dense vectors is created
```scala
val data = Seq(
  (0.0, Vectors.dense(0.5, 10.0)),
  (0.0, Vectors.dense(1.5, 20.0)),
  (1.0, Vectors.dense(1.5, 30.0)),
  (0.0, Vectors.dense(3.5, 30.0)),
  (0.0, Vectors.dense(3.5, 40.0)),
  (1.0, Vectors.dense(3.5, 40.0))
)
```

Creation of the dataframe from the previous set of vectors
```scala
val df = data.toDF("label", "features")
```
The first values of the previously created dataframe are taken
```scala
val chi = ChiSquareTest.test(df, "features", "label").head
```
Starting with the parts of the test, the values of p will be searched
```scala
println(s"pValues = ${chi.getAs[Vector](0)}")
```
Then the degrees of freedom of the model will be searched
```scala
println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
```
Finally, certain values will be extracted from a final vector, all based on the chi-square function.
```scala
println(s"statistics ${chi.getAs[Vector](2)}")
```

Summarizer

Import of necessary libraries, in this use of vectors and the summarizer itself
```scala
import spark.implicits._    
import Summarizer._
```
A set of vectors or sequence is created
```scala
val data = Seq(
  (Vectors.dense(2.0, 3.0, 5.0), 1.0),
  (Vectors.dense(4.0, 6.0, 7.0), 2.0)
)
```

Creation of the dataframe from the vectors
```scala
val df = data.toDF("features", "weight")
```

The summarizer library is used to obtain the mean and variance of some data in the requested dataframe.
```scala
val (meanVal, varianceVal) = df.select(metrics("mean", "variance").summary($"features", $"weight").as("summary")).select("summary.mean", "summary.variance").as[(Vector, Vector)].first()
```

The variables previously worked on are printed
```scala
println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")
```

The process is repeated with 2 new variables
```scala
val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
  .as[(Vector, Vector)].first()
```
Variable printing
```scala
println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")
```
