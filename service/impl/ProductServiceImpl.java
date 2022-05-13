package SweetWorld.service.impl;
import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.config.model.dto.ProductDto;
import SweetWorld.config.model.exceptions.CategoryNotFoundException;
import SweetWorld.config.model.exceptions.ProductNotFoundException;
import SweetWorld.repository.ProductRepository;
import SweetWorld.repository.CategoryRepository;
import SweetWorld.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(LocalDate dueDate, String name, String description, MultipartFile image, Double price, Integer quantity, Long categoryId) throws IOException {
        ProductCategory category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));


        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(dueDate, name, description, image, price, quantity, category)));
    }



    public Product save(Product product, MultipartFile image) throws IOException {



        return this.productRepository.save(product);
    }



    @Override
    @Transactional
    public Optional<Product> edit(Long id,LocalDate dueDate, String name, String description,MultipartFile image, Double price, Integer quantity, Long categoryId) {



        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDueDate(dueDate);

        ProductCategory category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setProductCategory(category);



        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        ProductCategory category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setProductCategory(category);



        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
