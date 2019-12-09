## Indexer

### Prerequisites
* JDK 8

### Run the project as jar

`./gradlew clean build -x test && java -jar -Dspring.profiles.active=dev build/libs/indexer-0.0.1-SNAPSHOT.jar`


### Run the project as container

`docker build -t indexer . && docker run -p 127.0.0.1:8081:8081 indexer`

### index request

`curl -X POST http://localhost:8081/api/v1/index-chat`