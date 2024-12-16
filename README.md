Lista de peliculas

    curl http://localhost:8080/peliculas

Crear usuario

    curl -X POST 'localhost:8080/registro' -H 'Content-Type: application/json' --data-raw '{
    "nombre": "Juan Perez",
    "correo": "juanperez@gmail.com",
    "contrasena": "1234"
    }'

Crear review

    curl -X POST 'localhost:8080/peliculas/2/review' \
    --header 'Content-Type: application/json' \
    --data '{
        "userId": 1,
        "rating": 5,
        "review": "great"
    }'