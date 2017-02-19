package io.galavant.contact

data class Contact(
        val name: String,
        val email: String,
        val organization: String,
        val message: String,
        val website: String?
)