plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.liquibase:liquibase-core")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
