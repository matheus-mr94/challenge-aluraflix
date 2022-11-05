package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/categories/{id}/full")
    public ResponseEntity<CategoryFullView> findFullViewById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoryService.findFullViewById(id));
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @GetMapping("/categories/{id}/simple")
    public ResponseEntity<CategorySimpleView> findSimpleViewById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoryService.findSimpleViewById(id));
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
