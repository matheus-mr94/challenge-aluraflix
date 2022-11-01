package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;

public interface Form {

    String getTitle();
    String getDescription();
    String getUrl();
    Category getCategory();
}
