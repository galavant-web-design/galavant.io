package contacttest

import io.damo.aspen.Test
import io.galavant.contact.Contact
import io.galavant.contact.ContactProcessor
import org.hamcrest.CoreMatchers.containsString
import org.springframework.http.HttpMethod.POST
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.*
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate


class ContactProcessorTest : Test({
    val restTemplate = RestTemplate()
    val mockServer = MockRestServiceServer.createServer(restTemplate)
    val processor = ContactProcessor(
            restOperations = restTemplate,
            mailgunUrl = "http://mail.example.com/",
            mailgunKey = "1234-secret",
            recipientAddress = "recipient@example.com",
            fromAddress = "sender@example.com"
    )

    before {
        mockServer.reset()
    }

    test {
        mockServer.expect(requestTo("http://mail.example.com/"))
                .andExpect(method(POST))
                .andExpect(header("Authorization", "Basic YXBpOjEyMzQtc2VjcmV0"))
                .andExpect(content().contentTypeCompatibleWith(MULTIPART_FORM_DATA))
                .andExpect(content().string(containsString("sender@example.com")))
                .andExpect(content().string(containsString("recipient@example.com")))
                .andExpect(content().string(containsString("Interest from Fred Derf")))
                .andExpect(content().string(containsString(
                        """<h2>Contact</h2>
<p>
<strong>Name:</strong> Fred Derf<br>
<strong>Organization:</strong> Derfco<br>
<strong>Email:</strong> <a href="mailto:interested@example.com">interested@example.com</a><br>
<strong>Website:</strong> <a href="http://derf.example.com">http://derf.example.com</a>
</p>
<h2>Message</h2>
<p>Hi there</p>""")))
                .andRespond(withSuccess())

        processor.process(Contact(
                name = "Fred Derf",
                organization = "Derfco",
                email = "interested@example.com",
                message = "Hi there",
                website = "http://derf.example.com"
        ))

        mockServer.verify()
    }
})