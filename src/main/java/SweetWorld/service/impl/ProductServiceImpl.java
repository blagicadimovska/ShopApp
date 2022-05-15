package SweetWorld.service.impl;

import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.config.model.exceptions.ProductNotFoundException;
import SweetWorld.repository.ProductRepository;
import SweetWorld.service.CategoryService;
import SweetWorld.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }




    @Override
    public Product saveProduct(Product product, MultipartFile image, String name) throws IOException {
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));

            product.setImageBase64(base64Image);
        }
        product.setName(name);
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        String nameLike = "%"+name+"%";
        return this.productRepository.findAllByNameLike(nameLike);
    }

    @Override
    public List<Product> findByPrice(Float price) {
        return this.productRepository.findByPriceLessThan(price);
    }
    public List<Product> findByNameAndPrice(String name, Float price) {
        String nameLike = "%"+name+"%";
        if(name == null && price == null)
        {
            return productRepository.findAll();
        }
        else if(name == null){
            return productRepository.findByPriceLessThan(price);
        }
        else if(price == null){

            return productRepository.findAllByNameLike(nameLike);
        }
        else {
              return productRepository.findAllByNameLikeAndPriceLessThan(nameLike,price);
        }
    }

    @Override
    public Product edit(Long id, String name, String description, Float price, ProductCategory category, MultipartFile image) throws IOException {
        Product product = findById(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));

            product.setImageBase64(base64Image);
        }
        return productRepository.save(product);
    }


}