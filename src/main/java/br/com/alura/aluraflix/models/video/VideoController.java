package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.utils.validations.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class VideoController {

      private VideoService videoService;

      public VideoController(VideoService videoService) {
            this.videoService = videoService;
      }

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

      @Transactional
      @PutMapping("/video/{id}")
      public ResponseEntity<VideoView> update(@PathVariable Long id, @RequestBody @Valid VideoUpdateForm updateForm) {
            try {
                  videoService.updateVideo(id, updateForm);
                  return ResponseEntity.ok(new VideoView(updateForm.getTitle(), updateForm.getDescription(), updateForm.getUrl()));
            } catch (NotFoundException ex) {
                  throw new ResponseStatusException(BAD_REQUEST);
            }
      }

      @PostMapping("/video")
      public ResponseEntity<VideoView> newVideo(@RequestBody @Valid VideoForm videoForm) {
            videoService.saveVideo(videoForm);
            URI location = URI.create("/videos");
            return ResponseEntity.created(location).body(new VideoView(videoForm.getTitle(), videoForm.getDescription(), videoForm.getUrl()));
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
