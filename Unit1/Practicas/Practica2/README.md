## Prctica 2

#### Creacion de una lista
var lista = collection.mutable.MutableList("red","white","black")

#### Añadir elementos a la lista
lista += ("verde" ,"amarillo", "azul", "naranja", "perla")

#### Obteniendo elementos de una lista
lista(3)
lista(4)
lista(5)

#### Crear un arreglo de rango
val arr = Array.range(0, 1000, 5)

#### Elemtos unicos en una lista
val num = List(1,3,3,4,6,7,3,7)
num.toSet

#### Creacion de un mapa mutable
val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))

#### Impresion de llave de un mapa
nombres.keys

#### Agreagar valores a un mapa
nombres += ("Miguel" -> 23)
