# My Spring Boot Application

## Запуск приложения

1. Убедитесь, что у вас установлен Java и Maven.
2. Клонируйте репозиторий: `git clone https://github.com/TALKER2k/TestStringFilter.git`
3. Перейдите в каталог проекта: `cd TestStringFilter`
4. Соберите проект: `mvn clean install`
5. Запустите приложение: `java -jar target/TestStringFilter.jar`

Приложение будет доступно по адресу http://localhost:8089.

## Формат входящих параметров

API-метод `/frequency` принимает параметр `str`, который представляет собой строку для анализа.

Пример запроса:

POST /frequency?str=aaaaabcccc

bash


## Формат исходящих параметров

API-метод `/frequency` возвращает карту с частотой символов во входной строке, в отсортированном порядке по значению.

Пример ответа:

{
"a": 5,
"c": 4,
"b": 1
}