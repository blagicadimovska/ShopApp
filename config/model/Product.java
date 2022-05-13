package SweetWorld.config.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Product {


    public Product() {
    }

    public Product(String name, String description, String image, Double price, Integer quantity, ProductCategory category) {
    }

    public Product(String name, String description, MultipartFile image, ProductCategory productCategory, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.productCategory = productCategory;
        this.dueDate = dueDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;

    private String name;

    private String description;
    @Column(name = "image")
    @Lob

    private MultipartFile image;

    private Double price;
    private boolean popular;

    private Integer quantity;

    @ManyToOne
    private ProductCategory productCategory;

    public Product(LocalDate dueDate, String name, String description, MultipartFile image, Double price, Integer quantity, ProductCategory productCategory) {
        this.dueDate = dueDate;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.productCategory = productCategory;
    }

    public Product(Long id, LocalDate dueDate, String name, String description, MultipartFile image, Double price, Integer quantity, ProductCategory productCategory, Boolean popular) {
        this.id = id;
        this.dueDate = dueDate;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.productCategory = productCategory;
        this.popular = popular;
    }

    public Product(Long id, LocalDate dueDate, String name, String description,  Double price, Integer quantity, ProductCategory productCategory) {
        this.id = id;
        this.dueDate = dueDate;
        this.name = name;
        this.description = description;

        this.price = price;
        this.quantity = quantity;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


}
