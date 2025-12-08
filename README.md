жавашный спринг бут проба пера типа в одно ухо влетает через хттпс из другого вылетает в бдшку и наоборот типа можно гетать из бдшки

запуск на линухе (виндовс мрази спок):<br />
docker compose up -d


дальше можно курлить инсерты в апишку джсонами откуда они идут в коре где главная логика лежит и из коре оно идет в бдшку дальше че то делает 

че оно может делать:<br />

создать задачу:<br />
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"First task\",\"completed\":false}"

вывод всех задачи шо есть:<br />
curl http://localhost:8080/api/tasks

вывод одной задачки:<br />
curl http://localhost:8080/api/tasks/1

апдейт задачки например первой:<br />
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated task\",\"completed\":true}"

удаление задачки:<br />
curl -X DELETE http://localhost:8080/api/tasks/1

короче все что подходит под концепцию crud