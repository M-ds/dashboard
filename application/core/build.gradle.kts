plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application:common"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}