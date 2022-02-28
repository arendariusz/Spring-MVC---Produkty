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
        if (category != null) {
            products = productRepository.findByCategory(category);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        getSum(model, products);
        return "list";
    }

    private void getSum(Model model, List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        model.addAttribute("priceSum", sum);
    }

    @GetMapping("/")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "index";
    }

    @PostMapping("/dodaj")
    public String add(Product product) {
        productRepository.add(product);
        return "index";
    }
}
