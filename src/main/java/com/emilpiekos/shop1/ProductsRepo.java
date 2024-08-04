package com.emilpiekos.shop1;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsRepo {
    private List<Product> products;

    private ProductsRepo() {
        products = new ArrayList<>();
        products.add(new Product("Mleko", 3.99, Category.FOOD));
        products.add(new Product("Chleb", 4.99, Category.FOOD));
        products.add(new Product("Ryż", 5.29, Category.FOOD));
        products.add(new Product("Ręczik", 29.99, Category.HOME));
        products.add(new Product("Płyn do naczyń", 7.99, Category.HOME));
        products.add(new Product("Worki na śmieci", 6.99, Category.HOME));
        products.add(new Product("Zestaw kluczy", 99.99, Category.OTHER));
        products.add(new Product("Wentylator", 149.99, Category.OTHER));
        products.add(new Product("Papier do drukarki", 19.99, Category.OTHER));
    }

    public List<Product> allProducts() {
        return products;
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream().filter(product -> product.getCategory().name().equals(category.toUpperCase())).toList();
    }

    public void add(Product product) {
        products.add(product);
    }
}
