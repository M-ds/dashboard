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

    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}
