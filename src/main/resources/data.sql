insert into addresses (city, street, house) values('Moscow', 'Prospect Mira', 171);

insert into roles(role_name) values ('ADMIN');
insert into roles(role_name) values ('USER');

insert into users(role, username, firstname, lastname, email, address_id, password, description, registration_date, status)
values (1, 'Nova', 'Tolya', 'Novikov', 'Nova@mail.ru', 1, '123', 'YA TOLYA', '01-01-2019', 1);

insert into recipes(title, description, portions, author, post_date, photo, status)
values ('zzapekanka', 'wikarnaya', 4, 1, '01-01-2019', 'photo_zapekanki', 1);

insert into ingridients(title, price, photo) values ('coffee', 200, 'coffee_photo');
insert into ingridients(title, price, photo) values ('apple', 60, 'apple_photo');
insert into ingridients(title, price, photo) values ('flour', 80, 'flour_photo');

insert into recipes_ingridients(recipe_id, ingridient_id) values (1, 1);
insert into recipes_ingridients(recipe_id, ingridient_id) values (1, 2);
insert into recipes_ingridients(recipe_id, ingridient_id) values (1, 3);
