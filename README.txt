Para poder ejecutar la api , es necesario configurar mysql , se debe tener
instalado mysql , un usuario llamado mercado_valores_dev con una contraseña 
1234 , tambien es necesario crear el schema de la base de datos en mysql ,
para ello es necesario las siguientes lineas de codigo en mysql:

create database MercadoValores;
use accionesMercadoValores;

Link para crear usuarios en MYSQL
https://www.youtube.com/live/xo1Y4h6t-yM?si=3YKXC4Y248bUR_4F

Para ir a la documentacion con swagger, primero es necesario ejecutar la api y despues
ir al siguiente link:

http://localhost:8080/swagger-ui/index.html