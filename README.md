Lista de peliculas

    curl http://localhost:8080/peliculas

Crear usuario

    curl -X POST 'localhost:8080/registro' -H 'Content-Type: application/json' --data-raw '{
    "nombre": "Juan Perez",
    "correo": "juanperez@gmail.com",
    "contrasena": "1234"
    }'