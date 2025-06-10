# treasy-tree-editor-mysql

## A) Running the project

```bash
git clone https://github.com/desiderati/demos.git

cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-mysql

docker-compose up -d && docker-compose logs -f
```

## B) Database configuration

```
URL Connection = jdbc:mysql://localhost:3306/treasy?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
User = treasy
Password = treasy
```

## B) Validates fi user was created successfully

```bash
docker exec -it mysql_db mysql -u treasy -p
```
