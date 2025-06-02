# element-insurance-server

## Prerequisites

* JDK 11
* Apache Maven 3.6.0

## Dependencies

Before everything, you must install the SpringBloom.dev Project in your local environment:

```
$ git clone https://github.com/desiderati/springbloom.git

$ cd springbloom

$ mvn clean install
```

## A) Running the project from scratch

```
$ git clone https://github.com/desiderati/demos.git

$ cd demos/demo-element-insurance/demo-element-insurance-server

$ mvn clean install

$ java -Xms256m -Xmx512m -jar target/demo-element-insurance-server-0.0.1-SNAPSHOT-exec.war
```

## D) Using the application

```
http://localhost:9090/element-insurance/
```
