package ru.job4j.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.job4j.model.Product
import ru.job4j.repository.ProductRepository
import java.util.*

@Service
class ServiceProduct(private val productRepository: ProductRepository) {
    fun add(product: Product): Product = productRepository.save(product)
    fun update(id: Long, product: Product): Product = productRepository.save(product.copy(id = id))
    fun delete(id: Long) = productRepository.deleteById(id)
    fun All(): MutableIterable<Product> = productRepository.findAll()
    fun getById(id: Long): Product? = productRepository.findByIdOrNull(id)

}