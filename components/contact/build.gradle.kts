apply {
    from("$rootDir/kotlin.gradle.kts")
}

dependencies {
    compile("org.springframework:spring-web:${extra["springVersion"]}")

    testCompile("org.springframework:spring-test:${extra["springVersion"]}")
}