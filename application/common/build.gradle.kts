plugins {
    kotlin("jvm")
    // Uncomment jacoco when tests are present in this module
    jacoco
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("io.jsonwebtoken:jjwt:0.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
