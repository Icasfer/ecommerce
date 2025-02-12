# Prueba Java Inditex ecommerce
## Enunciado
El enunciado esta subido en el fichero Enunciado.txt.

## Diseño
He optado por una arquitectura hexagonal y ha sido implementada aplicando los principios SOLID en la medida de lo posible.

## Base de datos
Como se pide en el enunciado lo he hecho con h2, al inciar la aplicación se inicializa con los datos que se indican. Para el tipo de datos lo único en lo que dudaba un poco es en los numéricos sin decimales, solo he puesto como Long el id del producto que parece el número que puede llegar a ser el más grande, los demás numéricos los he puesto como Integer. Esto es lo que he deduje con los datos que se me dan en el enunciado, para un correcto diseño habría que tener en cuenta cuanto crecería esta base de datos y con que datos reales vamos a trabajar.

## Tests
Los 5 test que se mencionan están en la clase de PriceServiceTest.
