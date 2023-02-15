FROM openjdk:17
COPY target/vlachatapp-1-jar-with-dependencies.jar vlachatapp.jar
ENTRYPOINT [ "java","-jar", "/vlachatapp.jar"]