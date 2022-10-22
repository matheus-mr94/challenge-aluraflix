package br.com.alura.aluraflix.models.video;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class VideoForm {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String url;

}
