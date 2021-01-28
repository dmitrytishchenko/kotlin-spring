package ru.job4j.repository

import org.springframework.data.repository.CrudRepository
import ru.job4j.model.Product

interface ProductRepository: CrudRepository<Product, Long> {
}