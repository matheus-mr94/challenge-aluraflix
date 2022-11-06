package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.models.video.VideoView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryFullView toFullView(Category category) {
        List<VideoView> videoViews = category.getVideos().stream().map(VideoView::new).toList();
        return new CategoryFullView(category.getTitle(), category.getColor(), videoViews);
    }

    public CategorySimpleView toView(Category category) {
        return new CategorySimpleView(category.getTitle(), category.getColor());
    }

    public Category toEntity(CategoryForm form) {
        return new Category(form.getTitle(), form.getColor());
    }
}
