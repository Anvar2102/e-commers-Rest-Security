package uz.pdp.online.lesson_2_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_2_2.Entity.Category;
import uz.pdp.online.lesson_2_2.Entity.Laptop;
import uz.pdp.online.lesson_2_2.Repository.LaptopRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/laptop")
public class LaptopController {
    @Autowired
    LaptopRepository laptopRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getlaptop() {
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addlaptop(@RequestBody Laptop laptop) {

        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editLaptopname(@PathVariable Integer id, @RequestBody Laptop laptop) {
        Optional<Laptop> laptopOptional = laptopRepository.findById(id);
        if (laptopOptional.isPresent()) {
            Laptop editCategory = laptopOptional.get();
            editCategory.setProduct(laptop.getProduct());
            return ResponseEntity.ok(editCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletelaptop(@PathVariable Integer id) {
        laptopRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
