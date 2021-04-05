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

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

// task to gather code coverage from multiple subprojects
// NOTE: the `JacocoReport` tasks do *not* depend on the `test` task by default. Meaning you have to ensure
// that `test` (or other tasks generating code coverage) run before generating the report.
// You can achieve this by calling the `test` lifecycle task manually
// $ ./gradlew test codeCoverageReport
tasks.register<JacocoReport>("codeCoverageReport") {
    // If a subproject applies the 'jacoco' plugin, add the result it to the report
    subprojects {
        val subproject = this
        subproject.plugins.withType<JacocoPlugin>().configureEach {
            subproject.tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.configureEach {
                val testTask = this
                sourceSets(subproject.sourceSets.main.get())
                executionData(testTask)
            }

            // To automatically run `test` every time `./gradlew codeCoverageReport` is called,
            // you may want to set up a task dependency between them as shown below.
            // Note that this requires the `test` tasks to be resolved eagerly (see `forEach`) which
            // may have a negative effect on the configuration time of your build.
            subproject.tasks.matching { it.extensions.findByType<JacocoTaskExtension>() != null }.forEach {
                rootProject.tasks["codeCoverageReport"].dependsOn(it)
            }
        }
    }

    // enable the different report types (html, xml, csv)
    reports {
        // xml is usually used to integrate code coverage with
        // other tools like SonarQube, Coveralls or Codecov
        xml.isEnabled = true

        // HTML reports can be used to see code coverage
        // without any external tools
        html.isEnabled = true
    }
}
