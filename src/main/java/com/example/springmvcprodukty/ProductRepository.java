package com.example.springmvcprodukty;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Chleb", 4.52, ProductCategory.FOOD));
        products.add(new Product("Masło", 8.27, ProductCategory.FOOD));
        products.add(new Product("Woda", 1.61, ProductCategory.FOOD));
        products.add(new Product("Miotła", 30.00, ProductCategory.HOME));
        products.add(new Product("Miska", 17.52, ProductCategory.HOME));
        products.add(new Product("Płyn do szyb", 12.29, ProductCategory.OTHER));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByCategory(ProductCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public double getSum(ProductCategory category) {
        double sum;
        if (category != null) {
            sum = products.stream().filter(product -> product.getCategory().equals(category))
                    .mapToDouble(Product::getPrice).sum();
        } else {
            sum = products.stream().mapToDouble(Product::getPrice).sum();
        }
        return sum;
    }

    public void add(Product product) {
        products.add(product);
    }
}
