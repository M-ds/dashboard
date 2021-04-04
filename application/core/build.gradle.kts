plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    implementation(project(":application:common"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
}


tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.isEnabled = true
    }
}