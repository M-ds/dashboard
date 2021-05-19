import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "2.4.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    jacoco

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

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

tasks.register<JacocoReport>("codeCoverageReport") {
    subprojects {
        val subproject = this
        subproject.plugins.withType<JacocoPlugin>().configureEach {
            subproject.tasks.matching {
                it.extensions.findByType<JacocoTaskExtension>() != null
            }.configureEach {
                val testTask = this
                sourceSets(subproject.sourceSets.main.get())
                executionData(testTask)
            }

            subproject.tasks.matching {
                it.extensions.findByType<JacocoTaskExtension>() != null
            }.forEach {
                rootProject.tasks["codeCoverageReport"].dependsOn(it)
            }
        }
    }

    reports {
        xml.isEnabled = true
    }
}

tasks.register<Copy>("copyCoverageReport") {
    from(file("$buildDir/reports/jacoco/codeCoverageReport/codeCoverageReport.xml"))
    into(file("coverageReports"))
}
