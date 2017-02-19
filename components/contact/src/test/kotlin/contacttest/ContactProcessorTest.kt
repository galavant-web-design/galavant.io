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
                .andExpect(content().string(containsString("Interest from interested@example.com")))
                .andExpect(content().string(containsString("""
        <p><strong>Message from interested@example.com:</strong></p>
        <p>Hi there</p>
        """)))
                .andRespond(withSuccess())

        processor.process(Contact(
                email = "interested@example.com",
                message = "Hi there"
        ))

        mockServer.verify()
    }
})