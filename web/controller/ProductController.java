package SweetWorld.web.controller;
import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.config.model.dto.ProductDto;
import SweetWorld.service.CategoryService;
import SweetWorld.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;


    public ProductController(ProductService productService,
                             CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;

    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<ProductCategory> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<ProductCategory> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

//    @PostMapping("/add")
//    public String saveProduct(
//            @Validated Product product,
//            BindingResult bindingResult,
//            @RequestParam MultipartFile image,
//            Model model) {
//
//
//        try {
//            this.productService.save(product, image);
//        } catch ( IOException e ) {
//            e.printStackTrace();
//        }
//        return "redirect:/products";
//    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String dueDate,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category
            ) throws IOException {
        if (id != null) {
            String base64Image = null;
            if (image != null && !image.getName().isEmpty()) {
                byte[] bytes = image.getBytes();
                base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));


            }


            this.productService.edit(id, LocalDate.parse(dueDate), name, description, image, price, quantity, category);
        } else {
            this.productService.save(LocalDate.parse(dueDate),name,description,image,price,quantity,category);
        }
        return "redirect:/products";
    }



}