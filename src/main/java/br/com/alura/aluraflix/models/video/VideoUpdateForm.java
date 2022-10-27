package br.com.alura.aluraflix.models.video;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class VideoUpdateForm implements Form {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;
}
