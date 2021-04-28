package ru.geekbrains.lesson7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson7.models.Product;
import ru.geekbrains.lesson7.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public void add(long id, String title, int cost) {
        productRepository.save(new Product(id,title,cost));
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
