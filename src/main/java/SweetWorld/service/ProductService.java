package SweetWorld.service;

import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    void deleteById(Long id);
    Product findById(Long id);
    Product saveProduct(Product product, MultipartFile image, String name) throws IOException;
    List<Product> findByName(String name);
    List<Product> findByPrice(Float price);
    List<Product> findByNameAndPrice(String name, Float price);
    Product edit(Long id, String name, String description,Float price, ProductCategory category, MultipartFile image ) throws IOException;

}