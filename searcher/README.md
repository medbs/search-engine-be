## Searcher

### Prerequisites
* JDK 8

### Run the project as jar

`./gradlew clean build -x test && java -jar -Dspring.profiles.active=dev build/libs/searcher-0.0.1-SNAPSHOT.jar`


### Run the project as container

`docker build -t searcher . && docker run -p 127.0.0.1:8082:8082 searcher`

### Search request

`curl -X POST -G http://localhost:8082/api/v1/search/{word}+{word}+{word}+{word}`