package SweetWorld.service;


import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<ProductCategory> findAll();
    Optional<ProductCategory> findById(Long id);
    ProductCategory create(String name, String description);

    ProductCategory update(String name, String description);

    void delete(String name);

    List<ProductCategory> listCategories();

    List<ProductCategory> searchCategories(String searchText);

}