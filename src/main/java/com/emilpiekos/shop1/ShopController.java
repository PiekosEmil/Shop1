package com.emilpiekos.shop1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShopController {
    private ProductsRepo productsRepo;

    public ShopController(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    @GetMapping("/")
    String index(Model model) {
        return "index";
    }

    @GetMapping("/add")
    String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("home", Category.HOME);
        model.addAttribute("food", Category.FOOD);
        model.addAttribute("other", Category.OTHER);
        return "add";
    }

    @PostMapping("/add")
    String add(@ModelAttribute("product") Product product) {
        productsRepo.add(product);
        return "redirect:/list";
    }

    @GetMapping("/list")
    String list(@RequestParam(required = false) String category, Model model) {
        if (category != null) {
            List<Product> productsByCategory = productsRepo.getProductsByCategory(category);
            model.addAttribute("products", productsByCategory);
            model.addAttribute("sum", totalSumOfProductsPrices(productsByCategory));
        } else {
            List<Product> products = productsRepo.allProducts();
            model.addAttribute("products", products);
            model.addAttribute("sum", totalSumOfProductsPrices(products));
        }
        return "list";
    }

    private BigDecimal totalSumOfProductsPrices(List<Product> products) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal price = BigDecimal.valueOf(product.getPrice());
            sum = sum.add(price);
        }
        return sum;
    }
}
