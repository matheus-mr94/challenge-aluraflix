package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.utils.validations.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryFullView findFullViewById(Long id) throws NotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        return categoryMapper.toFullView(category);
    }

    public CategorySimpleView findSimpleViewById(Long id) throws NotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        return categoryMapper.toView(category);
    }
}
