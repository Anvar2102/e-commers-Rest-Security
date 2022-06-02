package uz.pdp.online.lesson_2_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_2_2.Entity.Category;
import uz.pdp.online.lesson_2_2.Repository.CategoryRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getCategory() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody Category category) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);
        if (categoryRepositoryById.isPresent()) {
            Category editCategory = categoryRepositoryById.get();
            editCategory.setCategory(category.getCategory());
            return ResponseEntity.ok(editCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }


}
