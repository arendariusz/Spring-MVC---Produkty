package com.example.springmvcprodukty;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/list")
    public String home(Model model, @RequestParam(required = false, name = "kategoria") ProductCategory category) {
        List<Product> products;
        double priceSum = productRepository.getSum(category);
        if (category != null) {
            products = productRepository.findByCategory(category);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("priceSum", priceSum);
        return "list";
    }

    @GetMapping("/")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "index";
    }

    @PostMapping("/")
    public String add(Product product) {
        productRepository.add(product);
        return "redirect:/";
    }
}
