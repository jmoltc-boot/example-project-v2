
## SonarQube

Ejecutar en una consola el comando docker 

Chip: M1
```shell
docker run -d -p 9000:9000 mwizner/sonarqube:8.7.1-community
```

Chip: Intel
```shell
docker run -d -p 9000:9000 sonarqube:8.9-community
```

abrir el browser en http://localhost:9000 y usar las credenciales por defecto

```shell
user: admin
pass: admin
```

entrar a la sección `Administration > Security > Users` y crear un nuevo usuario

```shell
user: onboarding
pass: onboarding
```

ejecutar en el IDE la task `sonarqube`

---

## Postgres

```shell
docker run -d --rm --name postgres -e POSTGRES_PASSWORD=onboarding -p 5432:5432 postgres
```
