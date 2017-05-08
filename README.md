# SiorVen
![alt text](https://avatars0.githubusercontent.com/u/28288406?v=3&s=200 "Logo SiorVen")

*Sistema de optimización de reposición vending*

***

Este código organizado como un proyecto maven produce un archivo .war preparado para desplegar en servodores tomcat 7/8/9. 
Esta basado en el frameowrk Spring (Versión 4) para Java (Versión 8).

Para crear el war solo se debe lanzar el comando war de maven sobre el proyecto.

>mvn war

Ofrece las siguientes funcionalidades:

* Manejo de los productos y recursos disponibles en maquinas de vending
* Monitorización remota de la actividad de las maquinas vending con sistema de SiorVen con este fin
* Oferta de sugerencias de productos segun las ventas de los productos
