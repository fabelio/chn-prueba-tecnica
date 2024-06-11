# chn-prueba-tecnica
Prueba tecnica chn

#comando para ejecutar solucion 
docker-compose up --no-deps --build --force-recreate

#para iniciar a probar la solucion  se debe esperar a que el contenedor de base de datos 
#finalice de inicializar el motor y de crear las tablas del esquema
Completed: ALTER DATABASE OPEN
![alt text](end-db.png)

#para acceder a la aplicacion we se accede por el puerto 4200
http://localhost:4200

#Pantalla de gestion de clientes, con las siguientes opciones de izquierda a derecha
# Ver cuentas del cliente
# Editar cliente
# Eliminar cliente
![alt text](clientes.png)

#Pantalla de gestion de cuentas, con las siguientes opciones de izquierda a derecha
# Ver chequeras de la cuenta
# Bloquear Cuenta
# Activar cuenta
![alt text](cuentas.png)

#Pantalla de gestion de chequeras, con las siguientes opciones de izquierda a derecha
# Ver cheques de la cuenta
# Bloquear chequera
# Activar chequera
![alt text](chequeras.png)

#Pantalla de gestion de cheques, con las siguientes opciones de izquierda a derecha
# Bloquear chequera
# Cobrar cheque
![alt text](cheques.png)