plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    implementation(project(":application:common"))
    implementation(project(":application:core"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
