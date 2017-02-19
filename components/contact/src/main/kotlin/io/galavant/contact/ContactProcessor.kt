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

fun String.base64Encode(): String {
    val plainCredentials = this.toByteArray()
    val base64CredsBytes = getEncoder().encode(plainCredentials)
    val base64Creds = String(base64CredsBytes)
    return base64Creds
}
