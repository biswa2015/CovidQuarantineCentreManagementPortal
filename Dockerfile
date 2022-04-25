FROM openjdk:11
COPY target/CQCMP-0.0.1-SNAPSHOT.jar CQCMP.jar
CMD ["java","-jar","CQCMP.jar"]
EXPOSE 8095
