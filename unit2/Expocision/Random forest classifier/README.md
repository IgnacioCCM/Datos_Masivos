# <p align="center" > Bosques aleatorios </p> 

## ¿Que son los bosques aleatorios?

<p> El bosque aleatorio tiende a combinar cientos de árboles de decisión y luego entrena cada árbol de decisión en una muestra diferente de las observaciones.
Las predicciones finales del bosque aleatorio se realizan promediando las predicciones de cada árbol individual.</p> 
<p> Los beneficios son numerosos. Los árboles de decisión individuales tienden a sobre ajustarse (overfit) a los datos de entrenamiento, pero el bosque aleatorio puede mitigar ese problema al promediar los resultados de predicción de diferentes árboles. Esto le da al algoritmo de bosques aleatorios una mayor precisión predictiva que un solo árbol de decisión.</p> 

## Ventajas
* Puede resolver ambos tipos de problemas, es decir, clasificación y regresión, y realiza una estimación decente en ambos casos.
* Unos de los beneficios que más llama la atención es el poder de manejar grandes cantidades de datos con mayor dimensionalidad. Puede manejar miles de variables de entrada e identificar las variables más significativas, por lo que se considera uno de los métodos de reducción de dimensionalidad. Además el modelo muestra la importancia de la variable, que puede ser una característica muy útil.
* Tiene un método efectivo para estimar datos faltantes y mantiene la precisión cuando falta una gran proporción de los datos.
