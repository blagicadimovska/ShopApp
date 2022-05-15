package SweetWorld.config.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
public class ProductDto {

    private String name;
    private String description;
    @Column(name = "image")
    @Lob

    private String image;

    private Double price;

    private Integer quantity;

    private Long category;
    private LocalDate DueDate;

    public ProductDto(String name, String description, String image, Double price, Integer quantity, Long category, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        DueDate = dueDate;
    }
    public ProductDto(String name, String description, Double price, Integer quantity, Long category, LocalDate dueDate) {
        this.name = name;
        this.description = description;

        this.price = price;
        this.quantity = quantity;
        this.category = category;
        DueDate = dueDate;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return DueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        DueDate = dueDate;
    }

    public ProductDto() {
    }

    public ProductDto(String name, Double price, Integer quantity, Long category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;

    }

}
