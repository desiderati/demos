# treasy-tree-editor-static

## A) Running the project for development

### Project setup

```
git clone https://github.com/desiderati/demos.git

cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-static

npm install
```

### Just compiles the project

```
npm run build
```

### Compiles and hot-reloads for development

```
npm run start
```

## B) Running the project using Docker Image

```
docker-compose up -d && docker-compose logs -f
```

or

```
docker run -it -p 8000:80 --rm -e API_URL=localhost:9090 desiderati/treasy-tree-editor-static:latest
```

## C) Building the project

```
docker build -t desiderati/treasy-tree-editor-static:latest .

docker push desiderati/treasy-tree-editor-static:latest
```

## D) Using the application

```
http://localhost:8000
```
