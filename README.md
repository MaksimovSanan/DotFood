# DotFood (edabro.ru)

# Stack:
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Thymeleaf
- Lombok

# TODO:

## Plan:

- Регистрация пользователя
### Зарегестрированные пользователи
  - Добавление рецепта
  - Поиск рецептов по ингридиентам

### Незарегестрированные пользователи
- Поиск рецептов по ингридиентам

### Страница рецепта
- Зарегестрированные пользователи
  - Оценка рецепта 
  - Оставление коментариев

## Тут инфа по БД

### addresses:
- address_id
- city
- street
- house
### roles:
- role_id
- role_name
### users:
- user_id
- role REFERENCES roles(role_id)
- username UNIQUE NOT NULL
- firstname
- lastname
- email UNIQUE NOT NULL
- address REFERENCES addresses(address_id)
- password NOT NULL
- recipes (One To Many) FOREIGN KEY recipes(id) 
- description
- rating
- registration_date NOT NULL
- status (enabled)
- photo UNIQUE
### recipes:
- recipes_id
- title NOT NULL
- description NOT NULL
- cooking_time NOT NULL
- portions NOT NULL
- rating
- author (Many To One) FOREIGN KEY users(id) NOT NULL
- post_date NOT NULL
- photo NOT NULL
- status (enabled)

 TODO: Comments
### ingridients:
- id
- title UNIQUE NOT NULL
- price 
- photo NOT NULL
### recipes-ingridients
- recipes (foreign key reciepes(id)) NOT NULL
- ingridients (foreign key ingridients(id)) NOT NULL


