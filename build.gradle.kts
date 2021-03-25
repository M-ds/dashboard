import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "2.4.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
}

group = "polar.bear"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

// Used for all the modules (also the root)
allprojects {
    repositories {
        mavenCentral()
    }
}

// Used for all the sub modules (thus starting from :application)
subprojects {
    apply(plugin = "io.spring.dependency-management")

    dependencies {

        /**
         * Dependencies without Bill-Of-Materials
         */
        constraints {
        }
    }

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
