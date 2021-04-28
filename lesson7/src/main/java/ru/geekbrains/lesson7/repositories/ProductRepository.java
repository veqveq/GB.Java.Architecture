package ru.geekbrains.lesson7.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson7.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
