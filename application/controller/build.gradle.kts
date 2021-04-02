plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application:common"))
    implementation(project(":application:core"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
}