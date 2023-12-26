package ru.nova.client.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nova.client.model.Ingredient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class RestIngredientService implements IngredientService {

    private RestTemplate restTemplate;

  /*
  public RestIngredientService() {
   */

    public RestIngredientService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<Ingredient> findAll() {
        Iterable<Ingredient> ingredients = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                "http://127.0.0.1:8082/ingredients",
                Ingredient[].class)));
        log.info("{}", ingredients);
        return ingredients;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        log.info("RestIngredientService - addIngredient");
        return null;
    }

    private ClientHttpRequestInterceptor
    getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor =
                new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(
                            HttpRequest request, byte[] bytes,
                            ClientHttpRequestExecution execution) throws IOException {
                        request.getHeaders().add("Authorization", "Bearer " + accessToken);
                        System.out.println(request.getHeaders());
                        System.out.println(request.getMethod());
                        System.out.println(request.getMethodValue());
                        System.out.println(request.getURI());
                        return execution.execute(request, bytes);
                    }
                };

        return interceptor;
    }
}
