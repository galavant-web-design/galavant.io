import org.gradle.api.tasks.JavaExec
import java.io.FileInputStream
import java.util.*

group = "io.galavant"
version = "1.0"

apply {
    from("$rootDir/kotlin.gradle.kts")
    plugin("org.springframework.boot")
}

@Suppress("UNCHECKED_CAST")
val localProperties = Properties().let {
    it.load(FileInputStream("$rootDir/local.properties"))
    it as Map<String, Any>
}

project.tasks.findByPath("bootRun")?.configure(closureOf<JavaExec> {
    environment = localProperties
})

dependencies {
    compile(project(":components/contact"))

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:${extra["jacksonVersion"]}")
}
