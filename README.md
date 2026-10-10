# Proyecto integrador B1-2026-2 G2

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Vaadin](https://img.shields.io/badge/Vaadin-00B4F0?style=for-the-badge&logo=vaadin&logoColor=white)

## Resumen Ejecutivo
Esta es una plataforma web para realizar la gestion los usuarios, recursos y reservas de un coworking.
Sirve para llevar un control de los regristros desde una interfaz grafica sencilla que se conecta directamente a la base de datos.

## Arquitectura 
El proyecto se divide en varias capas para mantener un orden que son las siguientes:
* Frontend: la interfaz visual.
* Backend: Toda la funcion de logica y servicios.
* Base de datos: Alamcenamiento y orden de datos entrantes en la plataforma web.

## Stack Tecnologico
* Lenguaje: Java.
* Framework Principal: Spring boot.
* Interfaz: Vaadin y CSS (Lumo).
* Gestor de paquetes: Maven.
* Base de datos relacional (SQL).
* Control de versiones: Git y GitHub.

## Guia de Configuracion local
Para poder correr el proyecto en tu computador de manera local, haz lo siguiente:
1. Clona el repositorio en la terminal (CMD O TERMINAL DE VSC).
   git clone https://github.com/restreport04-pixel/Proyecto-integrador-b1-2026-2-g2.git
2. Entra a la carpeta del proyecto.
   cd Proyecto-integrador-b1-2026-2-g2
3. Abre el archivo src/main/resources/application.properties y pon el usuario y contraseña de tu base de datos local.
4. Para encender el servidor, escribe este comando en la terminal.
   ./mvnw spring-boot:run
5. Ve a tu navegador y ya entras a: http://localhost:8080
   
## Creditos del equipo de trabajo
* Integrante 1: Jacob Vanegas Martinez - Base de datos 
* Integrante 2: Thomas Restrepo Rodriguez - Frontend
* Integrante 3: Brenda Sofia Rivero Reyes - Documentacion
