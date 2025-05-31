# Pré-requisitos

* Docker v27
* Docker Compose v2.28.1

# Executar o container Docker

```shell
docker compose up -d && docker compose logs -f
```

# Abrir o console do Artemis

```shell
docker compose exec -it artemis-server /var/lib/artemis-instance/bin/artemis shell --user artemis --password artemis
```

# Criar a fila de processamento

```shell
queue create --name=track-queue --address=track-queue --anycast --durable --preserve-on-no-consumers --auto-create-address
```
