package uz.pdp.online.lesson_2_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_2_2.Entity.Monoblock;
import uz.pdp.online.lesson_2_2.Repository.MonoblockRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/monoblok")

public class MonoblockController {

    @Autowired
    MonoblockRepository monoblockRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getmonoblok() {
        return ResponseEntity.ok(monoblockRepository.findAll());
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addmonoblok(@RequestBody Monoblock monoblock) {

        return ResponseEntity.ok(monoblockRepository.save(monoblock));
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editMonoblok(@PathVariable Integer id, @RequestBody Monoblock monoblock) {
        Optional<Monoblock> monoblockOptional = monoblockRepository.findById(id);
        if (monoblockOptional.isPresent()) {
            Monoblock editCategory = monoblockOptional.get();
            editCategory.setProduct(monoblock.getProduct());
            return ResponseEntity.ok(editCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletemonoblok(@PathVariable Integer id) {
        monoblockRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
