package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import br.com.alura.aluraflix.models.category.CategoryRepository;
import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
public class VideoController {

      private final VideoRepository videoRepository;
      private final CategoryRepository categoryRepository;

      @GetMapping("/videos")
      public ResponseEntity<List<Video>> findAll() {
            return ResponseEntity.ok(videoRepository.findAll());
      }

      @GetMapping("/videos/{id}")
      public ResponseEntity<VideoView> findById(@PathVariable Long id){
            try{
                  Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
                  VideoView videoView = new VideoView(video);
                  return ResponseEntity.ok(videoView);
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @GetMapping("/videos/category/{categoryId}")
      public ResponseEntity<List<VideoView>> findByCategoryId(@PathVariable Long categoryId){
            try{
                  Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
                  return ResponseEntity.ok(videoRepository.findByCategory(category));
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @GetMapping("/videos/")
      public ResponseEntity<List<VideoView>> findByTitle(@RequestParam String title) {
            List<Video> videosByTitle = videoRepository.findByTitleContainingIgnoreCase(title);
            List<VideoView> videos = new ArrayList<>();
            videosByTitle.forEach(video -> {
                  VideoView videoView = new VideoView(video);
                  videos.add(videoView);
            });
            return ResponseEntity.ok(videos);
      }

      @Transactional
      @PutMapping("/video/{id}")
      public ResponseEntity<VideoView> update(@PathVariable Long id, @RequestBody @Valid VideoUpdateForm updateForm) {
            try {
                  Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
                  video.update(updateForm);
                  VideoView videoView = new VideoView(video);
                  return ResponseEntity.ok(videoView);
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @PostMapping("/video")
      public ResponseEntity<Video> newVideo(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
            if (videoForm.getCategory() == null) {
                  Category category = categoryRepository.findById(1L).get();
                  videoForm.setCategory(category);
            }
            Video video = videoForm.toEntity(videoForm);
            videoRepository.save(video);

            URI location = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(location).body(video);
      }

      @DeleteMapping("/video/{id}")
      public ResponseEntity<Void> removeVideo(@PathVariable Long id) {
            try {
                  Video video = videoRepository.findById(id).orElseThrow(NotFoundException::new);
                  videoRepository.delete(video);
                  return ResponseEntity.ok().build();
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(BAD_REQUEST);
            }
      }
}
