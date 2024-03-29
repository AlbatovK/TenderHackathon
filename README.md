# Проект команды "Котята фон Неймана"
## Участники команды
* Албатов Константин
* Подворный Артем
* Суслов Илья
* Горлов Артем

## Стэк технологий
* `Spring` - основной веб фреймворк
* `Redis` - использование внешней базы данных для кэширования с интеграцией `Spring Cache`
* `Postgres` - база данных для хранения данных о площадках тендеров и возможных форматах входных данных
* `Elastic Search` - база данных для поиска по отдельным полям и свободного поиска по ключевым и фильтрации
* `Kibana` - возможность просмотра данных в базе в режиме реального времени с целью контроля потока данных и администрирования 
* `Vaadin` - удобный и легковесный Server-Side UI-фреймворк
* `Spring AI` - интеграция с новым модулем с перспективой возможного добавления нейронной сети для более умного и кастомизируемого поиска тендеров
* `Swagger` - создание автоматической [документации](https://tenderhackathon.onrender.com/swagger-ui/index.html) для упрощения тестирования и демонстрации веб-приложения
* `Docker + Docker Compose` - для удобного развертывания на локальных или удаленных хостингах [(ссылка)](https://tenderhackathon.onrender.com). В данный момент используется Render, так как исппользуется Free-tier, сервер засыпает после бездействия, необходимо подождать от 3 до 5 минут после первого запроса, после обновить страницу.
* `Spring Jib Plugin` - эффективное создание docker образов для локального запуска и тестирования 
* `Detekt` и `Ktlint` - статические анализаторы кода, которые ускоряли и упрощали процесс разработки и отлаживания сервиса
* `Github CI/CD` - автоматическая сбока, тестирование и создание образа после коммита

## Структура проекта
![](https://github.com/AlbatovK/TenderHackathon/blob/dev/layers.drawio.svg?raw=true)
### Структура веб-сервиса
![](https://github.com/AlbatovK/TenderHackathon/blob/dev/svg_diagram.drawio.svg?raw=true)

## Видео-демонстрация функционала
Ссылка ня [яндекс-диск](https://disk.yandex.ru/i/RycUOM7ntfPiJQ)
