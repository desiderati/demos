# leads2b-employee-static

## A) Running the project for development

### Project setup
```
$ git clone https://github.com/desiderati/demos.git

$ cd demos/demo-leads2b-employee/demo-leads2b-employee-static

$ npm install
```

### Just compiles the project
```
$ npm run build
```

### Compiles and hot-reloads for development
```
$ npm run serve
```

### Lints and fixes files
```
$ npm run lint
```

## B) Running the project using Docker Image
```
$ docker-compose up -d && docker-compose logs -f
```
or
```
$ docker run -it -p 8080:80 --rm -e API_URL=<YOUR_IP_ADDRESS>:3000 desiderati/leads2b-employee-static:latest 
```

## C) Building the project
```
$ docker build -t desiderati/leads2b-employee-static:latest .

$ docker push desiderati/leads2b-employee-static:latest
```

## D) Using the application
```
http://localhost:8080

Username: admin
Password: 123456
```