# treasy-tree-editor-server

## Prerequisites

* JDK 11
* Apache Maven 3.6.0

## Dependencies 

Before everything, you must install the Commons Project in your local environment:
```
$ git clone https://github.com/desiderati/commons.git

$ cd commons

$ mvn clean install
```

## A) Running the project from the scratch
```
$ git clone https://github.com/desiderati/demos.git

$ cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-server

$ mvn clean install

$ java -Xms256m -Xmx512m -jar target/demo-treasy-tree-editor-server-1.0.0-SNAPSHOT-exec.war
```

## B) Running the project using Docker Image
```
$ id -u ${USER}

$ docker run -it -p 9090:9090 --rm -e LOCAL_USER_ID={id} -e SPRING_APPLICATION_JSON='{"spring.datasource.url":"jdbc:mysql://<YOUR_IP_ADDRESS>:3306/treasy"}' desiderati/treasy-tree-editor-server:latest 
```

### Example
```
docker-compose up -d && docker-compose logs -f
```
or
```
$ id -u ${USER}
1000
$ docker run -it -p 9090:9090 --rm -e LOCAL_USER_ID=1000 -e SPRING_APPLICATION_JSON='{"spring.datasource.url":"jdbc:mysql://<YOUR_IP_ADDRESS>:3306/treasy"}' desiderati/treasy-tree-editor-server:latest 
```

## C) Building the project 
```
$ docker build -t desiderati/treasy-tree-editor-server:latest .

$ docker push desiderati/treasy-tree-editor-server:latest
```

## D) Using the application
```
http://localhost:9090/treasy-tree-editor/swagger-ui.html
```
