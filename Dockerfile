FROM openjdk:17

COPY target/OnlineShop-0.0.1-SNAPSHOT.jar OnlineShop.jar

ENTRYPOINT ["java", "-jar", "/OnlineShop.jar"]