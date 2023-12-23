docker build -t mypostgresimage .
cd RecipeServiceDb
docker build -t recipe_service .
cd ..
cd recipeService
docker network create mynetwork
cd ..
docker run -d --network=mynetwork --name=mypostgrescontainer -p 54321:5432 mypostgresimage
docker run --network=mynetwork --name=spring-boot-container -p 8080:8080 -d recipe_service:latest