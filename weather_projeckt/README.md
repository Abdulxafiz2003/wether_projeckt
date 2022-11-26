# spring-jwt-realization
In this project I realize full mechanism of user login and registration. Also Implement Role Based Action Control.

To implement it, I used `Spring Boot`, `Spring Data JPA` and `Postgres` to store data, `Spring Security 5` to implement security issues, `JWT` to communicate between `Server` and `Client`.

# Running
To run the application enter in the command line: `mvn spring-boot:run`

Docker was used to create the database

After project running, schema in the Database created. First of all, you need some `Roles`:

```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

```
*\*\*\src\main\resources> docker compose up -d : in terminal
```

# Signup
`POST localhost:8080/api/auth/sign-up`
```
{
    "username": "admin",
    "email": "admin@gmail.com",
    "password": "12345678",
    "role": ["admin, moderator"]
}
```

# Get JWT Token
`POST localhost:8080/api/auth/sig-in`
```
{
    "username": "admin",
    "password": "1234"
}
```

# Admin controller
`localhost:8080/api/admin/***`

```Bearer {jwt}```

# User controller
`localhost:8080/api/user/***`
