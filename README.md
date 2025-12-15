жавашный спринг бут проба пера типа в одно ухо влетает через хттпс из другого вылетает в бдшку и наоборот типа можно гетать из бдшки

запуск:<br />
`docker compose up -d`


дальше можно например курлить инсерты в апишку джсонами откуда они идут в коре где главная логика лежит и из коре оно идет в бдшку дальше че то делает 

че оно может делать:<br />

создать задачу:<br />
`curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"title\":\"First task\",\"completed\":false}"`

вывод всех задачи шо есть:<br />
`curl http://localhost:8080/api/tasks`

вывод одной задачки:<br />
`curl http://localhost:8080/api/tasks/1`

апдейт задачки например первой:<br />
`curl -X PUT http://localhost:8080/api/tasks/1-H "Content-Type: application/json" -d "{\"title\":\"Updated task\",\"completed\":true}"`

удаление задачки:<br />
`curl -X DELETE http://localhost:8080/api/tasks/1`

короче все что подходит под концепцию crud



КАК ЗАПУСКАТЬ ТЕСТЫ, А ОЧЕНЬ ПРОСТО ДЛЯ ЭТОГО НАМ ПОНАДОВИТСЯ COCOJAMPA, А ТОЧНЕЕ JACOCO

Сначала переходим в дерикторию core-service команда:
`cd .\core-service\`

Что бы запустить обычный тест, он не даст нам покрывания подойдет команда: (не надо писать)
`.\mvnw test`

Что бы запустить трушный тест нам нужна команда: (надо писать)
`.\mvnw clean test `

Далее переходим в дерикторию api-gateway команда:
`cd .\api-gateway\ `

Что бы запустить трушный тест нам нужна команда:
`.\mvnw clean test`

НО ГДЕ ЖЕ ПОСМОТРЕТЬ РЕЗУЛЬТАТЫ ПОКРЫВАНИЯ??????

Тупо заходим в папку проекта в ПРОВОДНИКЕ (РАБОТАЕТ БЕЗ СМС И РЕГИСТРАЦИИ)
`C:\Users\..\hhhummxk\api-gateway\target\site\jacoco\ и открываем файл index.html`
`C:\Users\..\hhhummxk\core-service\target\site\jacoco\ аналогично!`

cd C:\Users\evgra\HHH\hhhummxk\api-gateway
./mvnw package -DskipTests

cd ..\core-service
./mvnw package -DskipTests

cd ..
docker compose down -v
docker compose up --build