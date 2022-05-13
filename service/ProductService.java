package SweetWorld.service;



import SweetWorld.config.model.Product;
import SweetWorld.config.model.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(LocalDate dueDate, String name, String description, MultipartFile image, Double price, Integer quantity, Long category) throws IOException;

    Product save(Product product, MultipartFile image) throws IOException;

    Optional<Product> edit(Long id,LocalDate dueDate, String name, String description,MultipartFile image, Double price, Integer quantity, Long category);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);
}