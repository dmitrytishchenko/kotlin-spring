package ru.job4j

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.context.WebApplicationContext
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@SpringBootTest
@RunWith(SpringRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //запуск тестов в алфавитном порядке
class KotlinSpringApplicationTests {
    private val url = "http://localhost:8080/products/"
    private val contentType = "application/json;charset=utf8"
    private lateinit var mock: MockMvc
    @Autowired
    private lateinit var webAppContext: WebApplicationContext

    @Before
    fun before() {
        mock = webAppContextSetup(webAppContext).build()
    }

    @Test
//    @WithMockUser
    fun `1 - Get empty list of product`() {
        val request = get(url)
                .contentType(contentType)
        mock.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().json("[]", true))
    }

    @Test
//    @WithMockUser
    fun `2 - Add product`() {
        val json = """
			{
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai"
			}
		""".trimIndent()
        val request = post(url)
                .contentType(contentType)
                .content(json)
        val resultJson = """
			{
			"id":1,
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai"
			}
		""".trimIndent()
        mock.perform(request)
                .andExpect(status().isCreated)
                .andExpect(content().json(resultJson, true))
    }

    @Test
//    @WithMockUser
    fun `3 - Update product`() {
        val json = """
			{
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai and others cars"
			}
		""".trimIndent()
        val request = put(url + "1")
                .contentType(contentType)
                .content(json)
        val resultJson = """
			{
			"id":1,
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai and others cars"
			}
		""".trimIndent()
        mock.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().json(resultJson, true))
    }

    @Test
//    @WithMockUser
    fun `4 - Get first product`() {
        val json = """
			{
			"id":1,
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai and others cars"
			}
		""".trimIndent()
        val request = get(url + "1")
                .contentType(contentType)
                .content(json)
        mock.perform(request)
                .andExpect(status().isFound)
                .andExpect(content().json(json, true))
    }

    @Test
//    @WithMockUser
    fun `5 - Get list with one product`() {
        val json = """[
			{
			"id":1,
			"name":"Engine G4KD",
			"description":"This engine destined for KIA and Hyundai and others cars"
			}
			]
		""".trimIndent()
        val request = get(url)
                .contentType(contentType)
                .content(json)
        mock.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().json(json, true))
    }

    @Test
//    @WithMockUser
    fun `6 - Delete one product and get empty list`() {
        val request = delete(url + "1")
                .contentType(contentType)
        mock.perform(request)
                .andExpect(status().isOk)
    }
}

