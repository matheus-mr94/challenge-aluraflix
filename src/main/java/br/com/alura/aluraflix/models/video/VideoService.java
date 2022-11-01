package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import br.com.alura.aluraflix.models.category.CategoryRepository;
import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {

    private VideoRepository videoRepository;
    private CategoryRepository categoryRepository;
    private VideoMapper videoMapper;

    public Video saveVideo(VideoForm form) {
        if (form.getCategory() == null) {
            Category category = categoryRepository.findById(1L).get();
            form.setCategory(category);
        }

        Video video = videoMapper.toEntity(form);
        return videoRepository.save(video);
    }

    public VideoView findById(Long id) throws NotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        return videoMapper.toView(video);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public void updateVideo(Long id, VideoUpdateForm form) throws NotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        video.update(form);
    }

    public void removeVideo(Long id) throws NotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
        videoRepository.delete(video);
    }
}
