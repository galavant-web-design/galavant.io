package io.galavant.contact

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.POST
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestOperations
import java.util.Base64.getEncoder


class ContactProcessor(
        val restOperations: RestOperations,
        val mailgunUrl: String,
        mailgunKey: String,
        val recipientAddress: String,
        val fromAddress: String
) {
    val credentials = "api:$mailgunKey"

    fun process(contact: Contact) {
        val request = HttpEntity(
                buildBody(contact),
                buildHeaders()
        )

        restOperations.exchange(mailgunUrl, POST, request, String::class.java)
    }

    private fun buildHeaders() = HttpHeaders().apply {
        contentType = MULTIPART_FORM_DATA
        add("Authorization", "Basic " + credentials.base64Encode())
    }

    private fun buildBody(contact: Contact) =  LinkedMultiValueMap<String, String>().apply {
            add("from", fromAddress)
            add("to", recipientAddress)
            add("subject", "Interest from ${contact.email}")
            add("html", buildMessage(contact))
    }

    private fun buildMessage(contact: Contact) = """
        <p><strong>Message from ${contact.email}:</strong></p>
        <p>${contact.message}</p>
        """
}

fun String.base64Encode(): String {
    val plainCredentials = this.toByteArray()
    val base64CredsBytes = getEncoder().encode(plainCredentials)
    val base64Creds = String(base64CredsBytes)
    return base64Creds
}
