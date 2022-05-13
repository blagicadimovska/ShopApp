package SweetWorld.repository;
import SweetWorld.config.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findAllByNameLike(String text);
    void deleteByName(String name);
    Optional<ProductCategory> findById(Long id);
}
