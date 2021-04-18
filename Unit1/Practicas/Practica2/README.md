## Practica 2

#### Creacion de una lista
```scala
var lista = collection.mutable.MutableList("red","white","black")
```

#### Añadir elementos a la lista
```scala
lista += ("verde" ,"amarillo", "azul", "naranja", "perla")
```

#### Obteniendo elementos de una lista
```scala
lista(3)
lista(4)
lista(5)
```

#### Crear un arreglo de rango
```scala
val arr = Array.range(0, 1000, 5)
```

#### Elemtos unicos en una lista
```scala
val num = List(1,3,3,4,6,7,3,7)
num.toSet
```

#### Creacion de un mapa mutable
```scala
val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))
```

#### Impresion de llave de un mapa
```scala
nombres.keys
```

#### Agreagar valores a un mapa
```scala
nombres += ("Miguel" -> 23)
```
