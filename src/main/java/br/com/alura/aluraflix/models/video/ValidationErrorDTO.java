package br.com.alura.aluraflix.models.video;

public class ValidationErrorDTO {

    String field;
    String message;

    public ValidationErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
