package ru.job4j.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product (
        @Id
        @JsonProperty("id")
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,
        @JsonProperty("name")
        @Column(name = "name", length = 200)
        val name: String = "",
@JsonProperty("description")
@Column(name = "description", length = 500)
val description: String = ""
)