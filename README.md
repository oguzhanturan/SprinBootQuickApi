# Spring Boot Refresh Token with JWT example
Role Based Authentication with Rest API.

Swagger, Swagger UI, Lombok implementation
## Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.23.jre7</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>3.0.0</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```
## Run following SQL insert statements
```
te user jwtexampleuser with password 'password';
create database jwtexampledb with template=template0 owner=jwtexampleuser;

alter default privileges grant all on tables to jwtexampleuser;
alter default privileges grant all on sequences to jwtexampleuser;

```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwtexampledb
spring.datasource.username=jwtexampleuser
spring.datasource.password=password

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
```

## Run Spring Boot application
```
mvn spring-boot:run
```

## SignIn Endpoint
http://localhost:8080/api/auth/signin
# Requests 

```xml
{
  "username": "admin",
  "password": "123456"
}
{
  "username": "moderator",
  "password": "123456"
}
{
  "username": "user",
  "password": "123456"
}
```
# Response
```xml
{
    "refreshToken": "d4f7d9c8-bfb0-4ec0-85ba-a05b9f2c717c",
    "id": 3,
    "username": "admin",
    "email": "admin@admin.com",
    "roles": [
        "ROLE_ADMIN"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzMTMwODY1MCwiZXhwIjoxNjMxMzA4NzEwfQ.7vfxHHMGRy50ChCCSnpX0dRhIzfd0UlEiu6RBFO4aKGvARbIue_UwKVcXv9DcgOQpbYWi44LCJ2VxCCBF5n3vA",
    "tokenType": "Bearer"
}

```
