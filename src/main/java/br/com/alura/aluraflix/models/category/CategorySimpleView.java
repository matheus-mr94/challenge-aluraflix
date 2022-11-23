package br.com.alura.aluraflix.models.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategorySimpleView {

    private String title;
    private String color;

    public CategorySimpleView(Category category) {
        this.title = category.getTitle();
        this.color = category.getColor();
    }
}
