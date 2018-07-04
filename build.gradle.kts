import org.gradle.api.tasks.Exec

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.31")
    }
}

plugins {
    base
}

task<Exec>("deploy") {
    mustRunAfter("build")
    commandLine("cf", "push")
}
