package br.com.alura.aluraflix.models.category;

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

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/categories/{id}/full")
    public ResponseEntity<CategoryFullView> findFullViewById(@PathVariable Long id) {
        try{
            Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
            return ResponseEntity.ok(new CategoryFullView(category));
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @GetMapping("/categories/{id}/simple")
    public ResponseEntity<CategorySimpleView> findSimpleViewById(@PathVariable Long id) {
        try{
            Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
            return ResponseEntity.ok(new CategorySimpleView(category));
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> newCategory(@RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder) {
        Category category = form.toEntity(form);
        categoryRepository.save(category);
        URI location = uriBuilder.path("/videos/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).body(category);
    }

    @Transactional
    @PutMapping("/category/{id}")
    public ResponseEntity<CategorySimpleView> update(@PathVariable Long id, @RequestBody CategoryForm form) {
        try {
            if(id != 1) {
                Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
                category.update(form);
                return ResponseEntity.ok(new CategorySimpleView(category.getTitle(), category.getColor()));
            }
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        throw new ResponseStatusException(BAD_REQUEST, "categoria 1 não pode ser editada.");
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> removeCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
}
