package uz.pdp.online.lesson_2_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_2_2.Entity.Display;
import uz.pdp.online.lesson_2_2.Entity.GamingChairrr;
import uz.pdp.online.lesson_2_2.Repository.GamingChairRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/gaming")
public class GamingChair {
    @Autowired
    GamingChairRepository gamingChairRepository;

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR','OPERATOR')")
    @GetMapping
    public HttpEntity<?> getGameComyter() {
        return ResponseEntity.ok(gamingChairRepository.findAll());
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PostMapping
    public HttpEntity<?> addgamechair(@RequestBody GamingChairrr gamingChairrr) {
        return ResponseEntity.ok(gamingChairRepository.save(gamingChairrr));

    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public HttpEntity<?> editgameComyter(@PathVariable Integer id, @RequestBody GamingChairrr gamingChairrr) {
        Optional<GamingChairrr> gamingChairrrOptional = gamingChairRepository.findById(id);
        if (gamingChairrrOptional.isPresent()) {
            GamingChairrr editCategory = gamingChairrrOptional.get();
            editCategory.setProduct(gamingChairrr.getProduct());
            return ResponseEntity.ok(editgameComyter(1, gamingChairrr));
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize(value = "hasAnyRole('SUPER ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComyter(@PathVariable Integer id) {
        gamingChairRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
