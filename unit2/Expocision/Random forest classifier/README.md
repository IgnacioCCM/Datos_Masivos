# <p align="center" > Bosques aleatorios de clasificaion </p> 

## ¿Que son los bosques aleatorios?

<p> El bosque aleatorio tiende a combinar cientos de árboles de decisión y luego entrena cada árbol de decisión en una muestra diferente de las observaciones.
Las predicciones finales del bosque aleatorio se realizan promediando las predicciones de cada árbol individual.</p> 
<p> Los beneficios son numerosos. Los árboles de decisión individuales tienden a sobre ajustarse (overfit) a los datos de entrenamiento, pero el bosque aleatorio puede mitigar ese problema al promediar los resultados de predicción de diferentes árboles. Esto le da al algoritmo de bosques aleatorios una mayor precisión predictiva que un solo árbol de decisión.</p> 

## ¿Cómo funciona el algoritmo de bosques aleatorios?
El algoritmo funciona completando los siguientes pasos:

<p> Paso 1: El algoritmo selecciona muestras en forma aleatoria de la base de datos proporcionada.</p> 

<p> Paso 2: El algoritmo creará un árbol de decisión para cada muestra seleccionada. Luego obtendrá un resultado de predicción de cada árbol creado.</p> 

<p> Paso 3: A continuación, se realizará la votación para cada resultado previsto. Para un problema de clasificación, usará la moda, y para un problema de regresión, usará la media.</p> 

<p> Paso 4: Y finalmente, el algoritmo seleccionará el resultado de predicción más votado como predicción final.</p> 

<p align="center">
  <img src="https://www.iartificial.net/wp-content/uploads/2019/06/Random-Forest-Bagging.png" />
</p>

## Ventajas
* Puede resolver ambos tipos de problemas, es decir, clasificación y regresión, y realiza una estimación decente en ambos casos.
* Unos de los beneficios que más llama la atención es el poder de manejar grandes cantidades de datos con mayor dimensionalidad. Puede manejar miles de variables de entrada e identificar las variables más significativas, por lo que se considera uno de los métodos de reducción de dimensionalidad. Además el modelo muestra la importancia de la variable, que puede ser una característica muy útil.
* Tiene un método efectivo para estimar datos faltantes y mantiene la precisión cuando falta una gran proporción de los datos.

## Desventajas
* En ocasiones se puede parecer este algoritmo como una caja negra, ya que se tiene muy poco control sobre lo que hace el modelo. 
* Perdida de interpretacion.
* Se aumenta el período de entrenamiento, el Random Forest requiere mucho más tiempo
para entrenar en comparación con los árboles de decisión normales, ya que genera muchos árboles y toma la decisión sobre la mayoría de los votos.

## Aplicaciones
<p>Para proporcionar recomendaciones de diferentes productos a los clientes en el comercio electrónico.</p>
<p>En medicina, el algoritmo puede ser utilizado para identificar la enfermedad del paciente a través del análisis de su historial médico.</p>
<p>También en el sector bancario, puede ser utilizado para determinar fácilmente si el cliente es fraudulento o legítimo.</p>

## Video
https://www.youtube.com/watch?v=VH7eLWsLCks&t=212s

## Bibliografia

<p>Cardellino F. (2021). Tutorial para un clasificador basado en bosques aleatorios: cómo utilizar algoritmos basados en árboles para el aprendizaje automático. mayo 03, 2021, de freecodecamp Sitio web: https://www.freecodecamp.org/espanol/news/random-forest-classifier-tutorial-how-to-use-tree-based-algorithms-for-machine-learning/</p>

<p>Gonzales L. (2018). Aprendizaje Supervisado: Random Forest Classification. mayo 03, 2021, de aprendeIA Sitio web: https://aprendeia.com/aprendizaje-supervisado-random-forest-classification/</p>

