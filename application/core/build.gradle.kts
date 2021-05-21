plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    implementation(project(":application:common"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.mockito:mockito-core:3.10.0")
}