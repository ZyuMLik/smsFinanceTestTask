### Задача 1: 
Создание простого API для управления библиотекой книг.
Создайте RESTful API на Spring Boot для управления библиотекой книг. API должен поддерживать следующие операции:
- Получение списка всех книг.
- Получение книги по идентификатору.
- Добавление новой книги.
- Обновление информации о книге.
- Удаление книги по идентификатору.
Каждая книга должна иметь следующие поля: id, title, author, publishedDate.


### ***Решение:*** 
Создал RESTcontroller с необходимыми методами, которые получают/сохраняют/удаляют книгу в базе данных. 

---

### Задача 2*: 
Добавьте в RESTful API функционал отправки и получения уведомлений при изменении данных о книгах с использованием RabbitMQ.

RabbitMQ должен быть настроен для отправки сообщений в очередь при создании, обновлении или удалении книги.
- Создание новой книги 
- Обновление информации о книге
- Удаление книги


  
Сервис-подписчик внутри приложения должен слушать очередь RabbitMQ и обрабатывать полученные сообщения, выполняя соответствующие операции:
- Сохранение новой книги в базу данных.
- Обновление существующей книги по ID.
- Удаление книги из базы данных по ID.

### ***Решение:*** 
Создал конфигуратор RabbitMQ.

Создал сервисы продюсеров для отправки сообщений и JSON в очередь RabbitMQ, также они выводят об этом информацию в консоль

Создал слушателей, которые принимают из очереди RabbitMQ сообщения(выводит их в консоль) и JSON(выводит информацию об этом в консоль и сохраняют/обновляют/удаляют в базе данных)

В контроллере закомментировал методы, которые сохраняли/удаляли книгу в базе данных, теперь они отправляют сообщение и JSON в соответствующую очередь RabbitMQ
