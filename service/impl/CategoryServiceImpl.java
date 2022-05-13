package SweetWorld.service.impl;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.repository.CategoryRepository;
import SweetWorld.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductCategory> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<ProductCategory> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public ProductCategory create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        ProductCategory c = new ProductCategory(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public ProductCategory update(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        ProductCategory c= new ProductCategory(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<ProductCategory> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}