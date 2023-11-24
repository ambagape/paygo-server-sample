# paygo-server
To install:
1) mvn clean install -s settings.xml

To run
1) cd spring-service-gateway
2) cp src/main/resources/application.sample.properties src/main/resources/application.properties
3) Update the settings in application.properties to reflect your environment
4) mvn flyway:clean flyway:migrate
5) mvn spring-boot:run -s ../settings.xml

Access server on localhost:port
Swagger documentation is avaliable at localhost:port/swagger-ui/index.html
