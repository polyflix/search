# Polyflix Search microservice

This repository contains the source code of the Polyflix Search microservice. The service is written in Kotlin by using the Spring Boot framework.

## Dependencies

To run this project locally, you must match the following requirements : 

- Have a Kafka instance on `localhost:9092`
- Have an Elasticsearch instance on `localhost:9200`

If you don't match the requirements, use the [Bootstrap](https://gitlab.polytech.umontpellier.fr/polyflix-do/bootstrap) project to bootstrap your local env.


## Development

### Kafka producer templates

#### Quiz

```json
{
  "trigger": "CREATE",
  "payload": {
    "id": "b091386e-2c44-4d4e-986f-8ca1d5442552",
    "name": "Quiz 1"
  }
}
```

#### Video

pas testé, c'est a peu pret ça

```json
{
    "trigger": "CREATE",
    "id": "b091386e-2c44-4d4e-986f-8ca1d5442552",
    "fields": {
        "id": "b091386e-2c44-4d4e-986f-8ca1d5442552",
        "email": "thomas.gouveia@etu.umontpellier.fr",
        "username": "tgouveia",
        "firstName": "Thomas",
        "lastName": "Gouveia",
        "avatar": "https://avatars.dicebear.com/api/identicon/dGhvbWFzLmdvdXZlaWFAZXR1LnVtb250cGVsbGllci5mcg==.svg",
        "roles": ["MEMBER"]
    }
}
```
