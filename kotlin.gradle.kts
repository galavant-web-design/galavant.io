repositories {
    mavenCentral()
    jcenter()
}

apply {
    plugin("kotlin")
}

dependencies {
    "compile"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.51")

    "testCompile"("io.damo.aspen:aspen:2.0.0")
    "testCompile"("org.assertj:assertj-core:3.6.2")
    "testCompile"("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
}

extra["springVersion"] = "5.0.7.RELEASE"
extra["springBootVersion"] = "2.0.3.RELEASE"
extra["jacksonVersion"] = "2.9.6"
