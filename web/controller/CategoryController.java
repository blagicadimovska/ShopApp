package SweetWorld.web.controller;
import SweetWorld.config.model.ProductCategory;
import SweetWorld.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/categories")
public class CategoryController {


    private final CategoryService categoryService;




    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String getCategoryPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<ProductCategory> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent", "add-category");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProductCategory(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description
    ) {
        if (id != null) {
            this.categoryService.update(name,description);
        } else {
            this.categoryService.create(name,description);
        }
        return "redirect:/categories";
    }
}