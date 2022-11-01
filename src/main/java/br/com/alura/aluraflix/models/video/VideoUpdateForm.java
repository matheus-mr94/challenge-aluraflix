package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class VideoUpdateForm implements Form {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
    private Category category;

    public Long getCategoryId() {
        return category.getId();
    }
}
