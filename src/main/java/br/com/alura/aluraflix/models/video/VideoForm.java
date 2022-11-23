package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class VideoForm implements Form {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public Video toEntity(VideoForm videoForm) {
        return new Video(videoForm.getTitle(), videoForm.description, videoForm.getUrl(), videoForm.getCategory());
    }
}
