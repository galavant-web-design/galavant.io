import java.io.FileInputStream
import java.util.*
import org.springframework.boot.gradle.tasks.run.BootRun

group = "io.galavant"
version = "1.0"

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.3.RELEASE")
    }
}


apply {
    from("$rootDir/kotlin.gradle.kts")
    plugin("org.springframework.boot")
}

@Suppress("UNCHECKED_CAST")
val localProperties = Properties().let {
    it.load(FileInputStream("$rootDir/local.properties"))
    it as Map<String, Any>
}

tasks.getByPath("bootRun")
    .configure(closureOf<BootRun> {
        environment = localProperties
    })

dependencies {
    "compile"(project(":components:contact"))

    "compile"("org.springframework.boot:spring-boot-starter-web:${extra["springBootVersion"]}")
    "compile"("org.springframework.boot:spring-boot-starter-security:${extra["springBootVersion"]}")
    "compile"("com.fasterxml.jackson.module:jackson-module-kotlin:${extra["jacksonVersion"]}")
}
