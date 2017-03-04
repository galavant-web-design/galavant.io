import java.io.FileInputStream
import java.util.*
import org.springframework.boot.gradle.run.BootRunTask

group = "io.galavant"
version = "1.0"

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE")
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
    .configure(closureOf<BootRunTask> {
        addResources = true
        environment = localProperties
    })

dependencies {
    compile(project(":components/contact"))

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:${extra["jacksonVersion"]}")
}
