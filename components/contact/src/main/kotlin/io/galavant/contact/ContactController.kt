package io.galavant.contact

import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("contact")
class ContactController(
    private val contactProcessor: ContactProcessor
) {

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    fun contact(@RequestBody contact: Contact) {
        contactProcessor.process(contact)
    }
}
