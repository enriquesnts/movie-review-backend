Lista de peliculas

    curl http://localhost:8080/peliculas \
    --header 'Authorization: Bearer <token>' \

Crear usuario

    curl -X POST 'localhost:8080/registro' -H 'Content-Type: application/json' --data-raw '{
    "nombre": "Juan Perez",
    "correo": "juanperez@gmail.com",
    "contrasena": "1234"
    }'

Crear review

    curl -X POST 'localhost:8080/peliculas/2/review' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer <token>' \
    --data '{
        "userId": 1,
        "rating": 5,
        "review": "great"
    }'

Login

    curl -X POST 'localhost:8080/login' \
    -H 'Content-Type: application/json' \
    --data-raw '{
    "correo": "juanperez@gmail.com",
    "contrasena": "1234"
    }'