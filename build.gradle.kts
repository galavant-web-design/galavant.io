import org.gradle.api.tasks.Exec
import org.gradle.script.lang.kotlin.gradleScriptKotlin
import org.gradle.script.lang.kotlin.kotlinModule

buildscript {
    repositories {
        gradleScriptKotlin()
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", "1.1.0"))
    }
}

plugins {
    base
}

task<Exec>("deploy") {
    mustRunAfter("build")
    commandLine("cf", "push")
}
