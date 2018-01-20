package io.galavant.app

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.galavant.contact.ContactProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate


@Configuration
open class AppConfig {

    @Value("\${contact.mailgun.url}")
    lateinit var mailgunUrl: String

    @Value("\${contact.mailgun.key}")
    lateinit var mailgunKey: String

    @Value("\${contact.recipient.address}")
    lateinit var recipientAddress: String

    @Value("\${contact.from.address}")
    lateinit var fromAddress: String

    @Bean
    open fun contactProcessor(restOperations: RestOperations) =
        ContactProcessor(
            restOperations = restOperations,
            mailgunUrl = mailgunUrl,
            mailgunKey = mailgunKey,
            recipientAddress = recipientAddress,
            fromAddress = fromAddress
        )

    @Bean
    open fun objectMapper() = jacksonObjectMapper()

    @Bean
    open fun restOperations(): RestOperations = RestTemplate()
}