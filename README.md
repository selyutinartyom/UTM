# UTM

## Заглушка УТМ

Программа предназначена для тестирования TillypadEGAIS в специфических условиях, в изолированной среде
(без связи с тестовым или продуктовым УТМ).
Например, необходимо проверить логику того или иного документа, ожидая получить от УТМ предварительно созданный XML файл.

Программа поднимает Undertow веб-сервер на хосте *localhost:8080* и предоставляет ресурсы */opt/in/* и */opt/out/*.
Поддерживаются HTTP метоты GET, POST, DELETE.
На эти ресурсы маппятся локальные XML-файлы для возврата ожидаемого ответа на запрос от клиентского ПО (TillypadEGAIS).
Таким образом, можно проверить некоторые простые действия для разных типов документов ЕГАИС, например, принятие
документов WayBill в TillypadEGAIS или отправку акта подтверждения WayBillAct на ТТН.

Поддерживаются все типы документов УТМ 2.0.5.

В корневую директорию, откуда запускается программа, можно подсунуть любой xml файл,
но его имя должно соответствовать шаблону:

	ТипДокумента_НомерДокумента.xml

Например:
- .\opt_out\Ticket4.xml -> */opt/out/Ticket/4*
- .\opt_in\WayBillAct123.xml -> */opt/in/WayBillAct/123*

Файлы **opt_in.xml**, **opt_out.xml** и **in_response.xml** – это маппинг списка документов в */opt/in*, */opt/out*
и ответ на POST запрос в */opt/in/*, соответственно.

Второй особенностью ПО является **конвертер XML-файла ReplyRests в WayBill расхода** (отгрузки) для дальнейшей отправки
в УТМ клиента при **перемещении остатков на обособленное подразделение** (пока нет реализации в TillypadEGAIS).

## Сборка из исходного кода

Для сборки и запуска артифакта проекта, вы можете вызвать:

	$ gradle build
	$ java -jar build/libs/tillypad-spring-rest-0.0.1-SNAPSHOT.jar

## Запуск проекта in-place

Чтобы запустить проект in place без сборки jar-ника вы можете использовать задачу “bootRun”:

	$ gradle bootRun

## Запуск из jar-файла

	$ java -jar -Dserver.address=localhost -Dserver.port=8080 build/libs/utm-0.0.x-SNAPSHOT.jar

или

    $ run.bat

Для остановки **CTRL+C** в консоли сервера или POST-запрос на ресурс *localhost:8080/shutdown*,
который можно выполнить с помощью CURL (**shutdown.bat**) или кнопкой «**Shutdown**» в браузере.

Допустимые параметры для конфигурации приложения:
- server.address=localhost
- server.port=8080
- utm.path=./
- utm.outFileName=out.xml
- utm.inFileName=in.xml
- utm.inResponseFileName=in_response.xml
- utm.opt.out.path=./opt_out/
- utm.opt.in.path=./opt_out/
- endpoints.shutdown.enabled=true
- server.error.whitelabel.enabled=true
- logging.level.io.secundus: INFO
- logging.level.org.springframework.web=INFO
- logging.level.org.springframework.security=INFO

## Версии программы

### 0.0.4-SNAPSHOT

1. Изменена модель XML схем на пакет **ru.fsrar.wegais.documenttypes**, который сгенерирован автоматически
(скрипт *src/main/resources/xsd/run_xjc.bat*).
2. В новой модели XML к схеме *v1* добавлена схема *v2*.
3. В скрипте генерации опущен параметр *-p* - "пакет" для того, чтобы одноименные классы были сгенерированы
в своих собственных пакетах. Также добавлен параметр *-encoding* - кодировка для POJO классов со значением UTF-8.
4. Класс **ObjectFactory** в пакете **io.secundus.model.xml** переименован в **ObjectFactoryV1** - фабрика создания
классов по модели *v1*.
5. Добавлено меню в шапке страниц.
6. Добавленны тесты для слоя сервиса и контроллера.

### 0.0.3-SNAPSHOT

1. Добавлена страница */converter* с формой преобразования XML файла ReplyRests в WayBill расхода для перемещения
остатков на обособленное подразделение.
2. Добавлена валидация формы на стороне front end.
3. Добавлена модель POJO классов, сгенерированная из XSD схем УТМ. Модель документов версии 1 из УТМ.
4. Добавлены слой контроллера и сервиса для обработки AJAX POST запроса на ресурс */convert*.
В ответе содержится XML файл документа WayBill отгрузки с нулевой ценой позиций.
5. Добавлена валидация формы на стороне back end.
6. Добавлены парамтры конфигурации типов документов для ресурсов */opt/in* и */opt/out*.

### 0.0.2-SNAPSHOT

1. Добавлены */opt/in*, */opt/in/{doc}* и */opt/in/{doc}/{id}*.
2. Исправлена ошибка "Could not parse multipart servlet request".
3. Добавлен README.md

### 0.0.1-SNAPSHOT

Первая экспериментальная версия.
Обработка */opt/out*, */opt/out/{doc}* и */opt/out/{doc}/{id}*.