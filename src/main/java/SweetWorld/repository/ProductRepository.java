package SweetWorld.repository;

import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByNameLike(String name);

    void deleteByName(String name);
    List<Product> findByPriceLessThan(Float price);
    List<Product> findAllByNameLikeAndPriceLessThan(String name, Float price);
}
