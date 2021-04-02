plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application:common"))
    implementation(project(":application:core"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
}