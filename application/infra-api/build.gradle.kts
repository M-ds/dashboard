plugins {
    kotlin("jvm")
    // Uncomment jacoco when tests are present in this module
//    jacoco
}

dependencies {
    implementation(project(":application:common"))
    implementation(project(":application:core"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-mail:2.2.0.RELEASE")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
