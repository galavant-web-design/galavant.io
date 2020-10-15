val springBootVersion: String by extra
val jacksonVersion: String by extra

apply {
    plugin("org.springframework.boot")
}

dependencies {
    "compile"(project(":components:contact"))

    "compile"("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    "compile"("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    "compile"("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
}
