import org.gradle.script.lang.kotlin.gradleScriptKotlin
import org.gradle.script.lang.kotlin.kotlinModule

buildscript {
    repositories {
        gradleScriptKotlin()
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", "1.1.0-rc-91"))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE")
    }
}

plugins {
    base
}
