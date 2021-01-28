package ru.job4j

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@SpringBootApplication
class KotlinSpringApplication

@EnableWebSecurity
class KotlinSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http {
            httpBasic {}
            authorizeRequests {
                authorize("/products/**", hasAuthority("ROLE_ADMIN"))
                authorize("/**", permitAll)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringApplication>(*args) {


        addInitializers(beans {
            bean {

                fun user(user: String, pw: String, vararg roles: String) =
                        User.withDefaultPasswordEncoder().username(user).password(pw).roles(*roles).build()

                InMemoryUserDetailsManager(
                        user("user", "user", "USER"),
                        user("admin", "admin", "USER", "ADMIN"))
            }
        })
    }
}