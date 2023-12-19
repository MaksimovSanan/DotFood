
insert into recipes(title, description, portions, rating, post_date, photo, status)
values ('zzapekanka', 'wikarnaya', 4, 4.2, '01-01-2019', 'photo_zapekanki', 1);

insert into ingredients(title, price, photo) values ('coffee', 200, 'coffee_photo');
insert into ingredients(title, price, photo) values ('apple', 60, 'apple_photo');
insert into ingredients(title, price, photo) values ('flour', 80, 'flour_photo');

insert into recipes_ingredients(recipe_id, ingredient_id) values (1, 1);
insert into recipes_ingredients(recipe_id, ingredient_id) values (1, 2);
insert into recipes_ingredients(recipe_id, ingredient_id) values (1, 3);
