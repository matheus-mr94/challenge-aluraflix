package br.com.alura.aluraflix.models.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryForm {

    private String title;
    private String color;

    public Category toEntity(CategoryForm form) {
        return new Category(form.getTitle(), form.getColor());
    }
}
