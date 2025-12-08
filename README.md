жавашный спринг бут проверка типа в одно ухо влетает через хттпс из другого вылетает в бдшку 

запуск:
docker compose up -d


дальше можно курлить инсерты в бдшку джосонами

создали задачу:
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"First task\",\"completed\":false}"

вывели все задачи шо есть
curl http://localhost:8080/api/tasks

вывод одной задачки
curl http://localhost:8080/api/tasks/1

апдейт задачки например первой
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated task\",\"completed\":true}"

удаление задачки
curl -X DELETE http://localhost:8080/api/tasks/

короче все что подходит под концепцию crud