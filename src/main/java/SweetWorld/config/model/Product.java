package SweetWorld.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Float price;
    @Column(name = "image")
    @Lob

    private String imageBase64;

    private Integer quantity;

    @ManyToOne
    private ProductCategory category;



    public Product(Long id,
                   String name,
                   String description,
                   Float price,
                   ProductCategory category) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Product(Long id,
                   String name,
                   String description,
                   Float price,
                   Integer quantity,
                   ProductCategory category,
                   String imageBase64) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imageBase64=imageBase64;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}