## Algoritmos de fibonacci

#### Primer algoritmo de fibonacci versión recursiva descendente

def fibonacci1(n: Double): Double = {
   if (n < 2){
       return n
   }else{
       return fibonacci2(n -1) + fibonacci2(n-2)
   }
}

fibonacci1(1)
fibonacci1(4)


#### Segundo algoritmo de fibonacci versión con fórmula explícita


def fibonacci2(n: Double): Double = {
   if (n < 2){
       return n
   }else{
       var i = ((1 + math.sqrt(5))/2)
       var j = ((math.pow(i, n) - math.pow((1 - i), n)) - (math.sqrt(5)))
       return j
   }
}

fibonacci2(1)
fibonacci2(4)


#### Tercer algoritmo de fibonacci versión iterativa


var k = 0
def fibonacci3(n: Int): Int = {
   var a = 0
   var b = 1
   var c = 0
   for (k <- 0 to n ){
       c = b + a
       a = b
       b = c
   }
   return a
}

fibonacci3(8)


#### Cuarto algoritmo de fibonacci versión iterativa 2 variables


var k = 0
def fibonacci4(n: Int): Int = {
   var a = 0
   var b = 1
   for (k <- 0 to n){
       b = b + a
       a = b - a
   }
   return b
}

fibonacci4(2)


#### Quinto algoritmo de fibonacci versión iterativa vector


var k = 0

def fibonacci5(n: Double): Double = {
   if (n < 2){
       return n
   }else{
       vector = (( k to n) + 1)
       vector(0) = 0
       vector(1) = 1
       for (k <- 2 to (n + 1)){
           vector(k) = vector(k - 1) + vector(k - 2)
       }
   }
   return vector(n)
}

