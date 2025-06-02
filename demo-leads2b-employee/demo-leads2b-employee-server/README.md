# leads2b-employee-server

Before everything, this project needs a MongoDB running on localhost and port: **27017**.
See the project: [leads2b-employee-mongodb](../demo-leads2b-employee-mongodb/README.md).

## A) Running the project for development

### Project setup

```
$ npm install
```

### Compiles and start the server

```
$ npm run start
```

## B) Running the project

```
$ docker-compose up -d && docker-compose logs -f
```
or

```
$ docker run -it -p 3000:3000 --rm -e DATABASE_URL=<YOUR_IP_ADDRESS>:27017 desiderati/leads2b-employee-server:latest
```

## C) Building the project

```
$ docker build -t desiderati/leads2b-employee-server:latest .

$ docker push desiderati/leads2b-employee-server:latest
```

## D) Using the application

```
http://localhost:3000
```
