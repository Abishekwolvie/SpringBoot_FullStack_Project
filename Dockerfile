FROM openjdk:22-jdk
WORKDIR /usr/src/myapp
COPY ecomapp-v1.jar .
CMD ["java","-jar","ecomapp-v1.jar"]