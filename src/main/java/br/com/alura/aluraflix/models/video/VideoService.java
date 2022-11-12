package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import br.com.alura.aluraflix.models.category.CategoryRepository;
import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoryRepository categoryRepository;
    private final VideoMapper videoMapper;

    public Video saveVideo(VideoForm form) {
        if (form.getCategory() == null) {
            Category category = categoryRepository.findById(1L).get();
            form.setCategory(category);
        }

        Video video = videoMapper.toEntity(form);
        return videoRepository.save(video);
    }

    public VideoView findById(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        return videoMapper.toView(video);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video updateVideo(Long id, VideoUpdateForm form) {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        video.update(form);
        return video;
    }

    public void removeVideo(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        videoRepository.delete(video);
    }

    public List<VideoView> findByTitle(String title) {
        List<Video> videosByTitle = videoRepository.findByTitleContainingIgnoreCase(title);
        List<VideoView> videos = new ArrayList<>();
        videosByTitle.forEach(video -> {
            VideoView videoView = videoMapper.toView(video);
            videos.add(videoView);
        });
        return  videos;
    }

    public List<VideoView> findByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        return videoRepository.findByCategory(category);
    }
}
