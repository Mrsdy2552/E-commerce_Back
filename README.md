## API CRUD Completo para Órdenes, Clientes, Productos y Items de Órdenes

Cada uno de los siguientes elementos cuenta con un CRUD completo que incluye las operaciones de POST, GET, PUT y DELETE.

## 1. Órdenes

Para crear, actualizar o eliminar una orden, usa la siguiente URL:

http://localhost:8080/api/Order

Objeto de creación de una orden:

json
Copiar código
{
"total": "El campo total no debe ser nulo",
"customer_id": "El campo customer_id no debe ser nulo",
"status": "El campo status no debe estar vacío"
}

## 2. Clientes

Para crear, actualizar o eliminar un cliente, usa la siguiente URL:

http://localhost:8080/api/Customers

Objeto de creación de un cliente:

json
Copiar código
{
"first_name": "Diego",
"last_name": "Camelo",
"email": "camelodiego1@hotmail.com",
"address": "Calle 123",
"city": "Colombia",
"postal_code": "124654",
"country": "Colombia",
"phone": "3002837056"
}

## 3. Productos

Para crear, actualizar o eliminar un producto, usa la siguiente URL:

http://localhost:8080/api/products

Objeto de creación de un producto:

json
Copiar código
{
"price": "El campo price no debe ser nulo",
"name": "El campo name no debe estar vacío",
"description": "El campo description no debe estar vacío",
"stock": "El campo stock no debe ser nulo"
}

## 4. Items de Órdenes

Los items de una orden se deben crear después de que la orden ha sido realizada. Para crear, actualizar o eliminar un item de orden, usa la siguiente URL:

http://localhost:8080/api/OrderItem

Objeto de creación de un item de orden:

json
Copiar código
{
"quantity": "El campo quantity no debe ser nulo",
"price": "El campo price no debe ser nulo",
"product_id": "El campo product_id no debe ser nulo",
"order_id": "El campo order_id no debe ser nulo"
}

## 5. Obtener Items de una Orden

Para buscar todos los items de una orden específica basada en su ID, usa la siguiente URL:

http://localhost:8080/api/OrderItem/orderfull/{ID}

Donde {ID} es el ID de la orden que deseas consultar.

## importaciones SQL

En el archivo import.sql se encuentran los scripts para la creación de las tablas y la inserción de los productos básicos. Estos deben ejecutarse en la base de datos local.

Para garantizar el correcto funcionamiento y despliegue del proyecto, es necesario realizar la configuración correspondiente en el archivo application.properties. Allí se encuentran las siguientes variables clave para ajustar el entorno de la base de datos:

Configuración de la URL de la base de datos y el puerto:
spring.datasource.url=jdbc:mysql://localhost:3306/db_e_comers_crud

Configuración del usuario de la base de datos:
spring.datasource.username=root

Configuración de la contraseña de la base de datos:
spring.datasource.password=0000

Asegúrate de adaptar estos valores según tu entorno local para una correcta conexión con la base de datos.
