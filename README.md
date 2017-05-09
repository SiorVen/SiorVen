# SiorVen
![alt text](https://avatars0.githubusercontent.com/u/28288406?v=3&s=200 "Logo SiorVen")

*Sistema de optimización de reposición vending*

***

Este código organizado como un proyecto maven produce un archivo .war preparado para desplegar en servidores tomcat 7/8/9. 
Esta basado en el framework Spring (Versión 4) para Java (Versión 8). Tambien esta el codigo slice que usa el middleware ICE
 Con el que se puede generar el código para la interfaz de conexion de los recolectores de datos para un numero de lenguajes.
 Este código se encuentra ya generado para Java.

Para crear el war solo se debe lanzar el comando war de maven sobre el proyecto.

>mvn war

Ofrece las siguientes funcionalidades:

* Manejo de los productos y recursos disponibles en maquinas de vending
* Monitorización remota de la actividad de las maquinas vending con sistema de SiorVen con este fin
* Oferta de sugerencias de productos segun las ventas de los productos
