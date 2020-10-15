val springVersion: String by extra

dependencies {
    "compile"("org.springframework:spring-web:$springVersion")

    "testCompile"("org.springframework:spring-test:$springVersion")
}
