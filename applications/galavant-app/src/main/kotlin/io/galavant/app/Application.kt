package io.galavant.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = arrayOf(
    "io.galavant.app",
    "io.galavant.contact"
))
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
