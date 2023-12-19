package ru.maksimov.recipeService.util;

public class RecipeErrorResponse {
    private String message;
    private long datetime;

    public RecipeErrorResponse(String message, long datetime) {
        this.message = message;
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
