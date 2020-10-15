import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0" apply false
    id("org.springframework.boot") version "2.0.3.RELEASE" apply false
}

subprojects kotlinConfig@{
    if (isNotKotlinProject()) return@kotlinConfig

    apply(plugin = "kotlin")

    extra.apply {
        set("springVersion", "5.0.7.RELEASE")
        set("springBootVersion", "2.0.3.RELEASE")
        set("jacksonVersion", "2.9.6")
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    group = "io.galavant"

    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
        "implementation"(kotlin("reflect"))

        "testCompile"("io.damo.aspen:aspen:2.1.0")
        "testCompile"("org.assertj:assertj-core:3.6.2")
        "testCompile"("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

fun Project.isNotKotlinProject() = name == "applications" || name == "components"
