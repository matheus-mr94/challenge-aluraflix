package br.com.alura.aluraflix.models.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    VideoMapper videoMapper;

    public void saveVideo(VideoForm form) {
        Video video = videoMapper.toEntity(form);
        videoRepository.save(video);
    }

    public VideoView findById(Long id) throws VideoNotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(VideoNotFoundException::new);
        return videoMapper.toView(video);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public void updateVideo(Long id, VideoUpdateForm form) throws VideoNotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(VideoNotFoundException::new);
        video.update(form);
    }

    public void removeVideo(Long id) throws VideoNotFoundException {
        Video video = videoRepository.findById(id).orElseThrow(VideoNotFoundException::new);
        videoRepository.delete(video);
    }
}
