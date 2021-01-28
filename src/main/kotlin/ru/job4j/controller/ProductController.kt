package ru.job4j.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.job4j.model.Product
import ru.job4j.service.ServiceProduct

@RestController
@RequestMapping("products")
class ProductController(private val service: ServiceProduct) {
    @GetMapping("/")
    fun getAllProducts() = service.All()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody product: Product) = service.add(product)

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) = service.getById(id)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product) = service.update(id, product)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}