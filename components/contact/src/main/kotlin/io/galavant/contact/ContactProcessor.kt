package io.galavant.contact

import org.intellij.lang.annotations.Language
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.POST
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestOperations
import java.util.Base64.getEncoder


class ContactProcessor(
    private val restOperations: RestOperations,
    private val mailgunUrl: String,
    private val fromAddress: String,
    private val recipientAddress: String,
    mailgunKey: String
) {
    private val credentials = "api:$mailgunKey"

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

    private fun buildBody(contact: Contact) = LinkedMultiValueMap<String, String>().apply {
        add("from", fromAddress)
        add("to", recipientAddress)
        add("subject", "Interest from ${contact.name}")
        add("html", buildMessage(contact))
    }

    @Language("HTML")
    private fun buildMessage(contact: Contact)
        = """<h2>Contact</h2>
<p>
<strong>Name:</strong> ${contact.name}<br>
<strong>Organization:</strong> ${contact.organization}<br>
<strong>Email:</strong> <a href="mailto:${contact.email}">${contact.email}</a><br>
<strong>Website:</strong> <a href="${contact.website}">${contact.website}</a>
</p>
<h2>Message</h2>
<p>${contact.message}</p>"""
}

fun String.base64Encode() = let {
    String(getEncoder().encode(it.toByteArray()))
}
