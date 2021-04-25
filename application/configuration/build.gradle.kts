plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    // Uncomment jacoco when tests are present in this module
//    jacoco
}

dependencies {
    implementation(project(":application:common"))
    implementation(project(":application:controller"))
    implementation(project(":application:core"))
    implementation(project(":application:infra-api"))
    implementation(project(":application:infra-jdbc"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    implementation("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}
