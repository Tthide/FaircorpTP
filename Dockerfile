FROM openjdk:18

ARG JAR_FILE=build/libs/faircorp-0.0.1-SNAPSHOT.jar
ARG APPLICATION_PROPERTIES=src/main/resources/applicationDocker.properties

ARG APP_PORT_TEMP
ARG H2_USER_TEMP="sa"
ARG H2_PASS_TEMP=""
ARG H2_PATH_TEMP="jdbc:h2:file:./data/demo"

ENV APP_PORT=${APP_PORT_TEMP}  \
    H2_USER=${H2_USER_TEMP}  \
    H2_PASS=${H2_PASS_TEMP}  \
    H2_PATH=${H2_PATH_TEMP}





#Copying the app from the host
COPY ${JAR_FILE} ./app.jar
#Copying the new application properties from the host
COPY ${APPLICATION_PROPERTIES} .
#Copying database file
#COPY src/main/resources/data/demo.mv.d ./data/demo.mv.d

#Executing the app on docker run
ENTRYPOINT ["java","-jar","./app.jar","--spring.config.location=applicationDocker.properties"]
