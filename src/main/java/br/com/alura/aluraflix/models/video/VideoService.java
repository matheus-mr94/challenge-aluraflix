package br.com.alura.aluraflix.models.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    public VideoView findById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(VideoNotFoundException::new));
        return videoMapper.toView(video);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }
}
