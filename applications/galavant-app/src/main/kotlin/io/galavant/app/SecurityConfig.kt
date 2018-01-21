package io.galavant.app

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${security.force.https:false}")
    lateinit var forceHttps: String

    override fun configure(http: HttpSecurity) {
        if (forceHttps == "true") {
            http.requiresChannel().anyRequest().requiresSecure()
        }

        http.csrf().disable()
    }
}
