plugins {
    id("java-library")
}

group = "ua.edu.ukma"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.squareup:javapoet:1.13.0")
    implementation(project(":annotations"))
}

tasks.test {
    useJUnitPlatform()
}