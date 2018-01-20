repositories {
    mavenCentral()
    jcenter()
}

apply {
    plugin("kotlin")
}

dependencies {
    "compile"("org.jetbrains.kotlin:kotlin-stdlib-jre8:1.2.10")

    "testCompile"("io.damo.aspen:aspen:2.0.0")
    "testCompile"("org.assertj:assertj-core:3.6.2")
    "testCompile"("com.nhaarman:mockito-kotlin:1.3.0")
}

extra["springVersion"] = "4.3.13.RELEASE"
extra["jacksonVersion"] = "2.8.6"
