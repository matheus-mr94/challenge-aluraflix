package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
public class VideoController {

      private VideoService videoService;

      @GetMapping("/videos")
      public ResponseEntity<List<Video>> findAll() {
            return ResponseEntity.ok(videoService.findAll());
      }

      @GetMapping("/videos/{id}")
      public ResponseEntity<VideoView> findById(@PathVariable Long id){
            try{
                 return ResponseEntity.ok(videoService.findById(id));
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @GetMapping("/videos/category/{categoryId}")
      public ResponseEntity<List<VideoView>> findByCategoryId(@PathVariable Long categoryId){
            try{
                 return ResponseEntity.ok(videoService.findByCategoryId(categoryId));
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @GetMapping("/videos/")
      public ResponseEntity<List<VideoView>> findByTitle(@RequestParam String title) {
            return ResponseEntity.ok(videoService.findByTitle(title));
      }

      @Transactional
      @PutMapping("/video/{id}")
      public ResponseEntity<VideoView> update(@PathVariable Long id, @RequestBody @Valid VideoUpdateForm updateForm) {
            try {
                  Video video = videoService.updateVideo(id, updateForm);
                  VideoView body = new VideoView(video.getTitle(), video.getDescription(),
                          video.getUrl(), video.getCategoryId());
                  return ResponseEntity.ok(body);
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(NOT_FOUND);
            }
      }

      @PostMapping("/video")
      public ResponseEntity<Video> newVideo(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
            Video video = videoService.saveVideo(videoForm);
            URI location = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(location).body(video);
      }

      @DeleteMapping("/video/{id}")
      public ResponseEntity<Void> removeVideo(@PathVariable Long id) {
            try {
                  videoService.removeVideo(id);
                  return ResponseEntity.ok().build();
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(BAD_REQUEST);
            }
      }
}
