package uz.pdp.online.lesson_2_2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_2_2.Entity.Category;
import uz.pdp.online.lesson_2_2.Projection.CategoryCustom;

@RepositoryRestResource(path = "category", excerptProjection = CategoryCustom.class)
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
