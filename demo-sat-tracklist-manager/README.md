# Pré-Requisitos

* JDK 21
* Apache Maven 3.9.0

# Dependências

Antes de tudo, você deve instalar o projeto SpringBloom.dev em seu ambiente local:

```
$ git clone https://github.com/desiderati/springbloom.git

$ cd springbloom

$ mvn clean install
```

# Fazendo o Build da aplicação

```
$ mvn clean package

```

# Executando a aplicação (na ordem abaixo definida)

- [Executando o ArtemisMQ](demo-sat-tracklist-manager-artemis-server/README.md)
- [Executando o HSQLDB](demo-sat-tracklist-manager-db/README.md)
- [Executando o Worker](demo-sat-tracklist-manager-worker/README.md)
- [Executando o Server](demo-sat-tracklist-manager-server/README.md)
- [Executando o Conteúdo Estático](demo-sat-tracklist-manager-static/README.md)
