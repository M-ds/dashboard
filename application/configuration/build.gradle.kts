plugins {
    id("org.springframework.boot")

    kotlin("jvm")
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

    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    {
//        exclude group : 'junit', module: 'junit'
//    }

    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}