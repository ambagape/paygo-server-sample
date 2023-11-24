FROM maven:3.8.7-openjdk-18

RUN mkdir /opt/paygo

COPY ./ /opt/paygo

RUN mv /opt/paygo/settings.xml /usr/share/maven/ref/

WORKDIR /opt/paygo

RUN ls -l

COPY spring-service-gateway/src/main/resources/application.sample.properties \
     spring-service-gateway/src/main/resources/application.properties

WORKDIR /opt/paygo

RUN mvn -DskipTests -s /usr/share/maven/ref/settings.xml clean install 

WORKDIR /opt/paygo/spring-service-gateway

RUN mvn flyway:clean flyway:migrate

EXPOSE 9000:8080

CMD [ "sh", "-c", "mvn -s /usr/share/maven/ref/settings.xml spring-boot:run" ]



