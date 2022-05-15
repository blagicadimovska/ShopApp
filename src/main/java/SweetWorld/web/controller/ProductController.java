package SweetWorld.web.controller;

import SweetWorld.config.model.Product;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.service.CategoryService;
import SweetWorld.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;


    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;

    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false)String name,
                                 @RequestParam(required = false)Float price,
                                 Model model) {
        List<Product> products;
        if (name == null && price == null) {
            products = this.productService.findAll();
        } else {
            products = this.productService.findByNameAndPrice(name, price);
        }
//        if (name == null) {
//            products = this.productService.findAll();
//        } else {
//            products = this.productService.findByName(name);
//        }

        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "products";
    }


    @GetMapping("/add-product")
    public String addNewProductPage(Model model) {
        List<ProductCategory> categoryList = this.categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("shop", new Product());
        model.addAttribute("bodyContent", "add-product");

        return "add-product";
    }

    @PostMapping("/add-new-product")
    public String saveProduct(
            @Validated Product product,
            BindingResult bindingResult,
            @RequestParam MultipartFile image,
            @RequestParam String name,
            Model model) {


        try {
            this.productService.saveProduct(product, image, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable Long id,
                                  Model model) {
        try {
            Product product = this.productService.findById(id);
            List<ProductCategory> categories = this.categoryService.findAll();

            model.addAttribute("shop", product);
            model.addAttribute("categoryList", categories);
            model.addAttribute("bodyContent", "add-product");
            return  "add-product";
        } catch (RuntimeException ex) {
            return "redirect:/products?error=" + ex.getMessage();
        }
    }
    @PostMapping("/{id}")
    public String edit (@PathVariable Long id,
                        @RequestParam String name,
                        @RequestParam String description,
                        @RequestParam Float price,
                        @RequestParam ProductCategory category,
                        @RequestParam MultipartFile image) throws IOException {
        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));

        }

        this.productService.edit(id, name, description, price, category, image);
        return "redirect/products";

    }



    @PostMapping("/{id}/delete")
    public String deleteProductWithPost(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }
}