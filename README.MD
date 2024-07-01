### Требуемая инфрастуктура
Запущенный доступный Selenium GRID: отдельный сервет, Docker и т. п. с Crome <br />
Рекомендуемый запуск в случае Docker: <br />
```shell
1. docker network create grid

2. docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:latest

3. docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
    --shm-size="2g" \
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
    selenium/node-chrome:latest
```

### Сборка, запуск
Адрес доступа к хосту GRID указывается в файле `application.conf`. В случае использования Docker по вышеописанному сценарию, менять его не нужно. 

Запуск `mvn clean test`.