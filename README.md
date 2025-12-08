жавашный спринг бут проверка типа в одно ухо влетает через хттпс из другого вылетает в бдшку 

запуск: \n
docker compose up -d


дальше можно курлить инсерты в бдшку джосонами

создали задачу: \n
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"First task\",\"completed\":false}"

вывели все задачи шо есть: \n
curl http://localhost:8080/api/tasks

вывод одной задачки: \n
curl http://localhost:8080/api/tasks/1

апдейт задачки например первой: \n
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated task\",\"completed\":true}"

удаление задачки: \n
curl -X DELETE http://localhost:8080/api/task s/

короче все что подходит под концепцию crud