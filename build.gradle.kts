import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val ktormVersion: String by project
val logbackVersion: String by project
val ktormMySQLVersion: String by project

plugins {
    kotlin("jvm") version "1.7.20"
}

group = "fr.imacaron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty:${ktorVersion}")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("org.ktorm:ktorm-support-mysql:${ktormVersion}")
    testImplementation(kotlin("test"))
    testImplementation("org.ktorm:ktorm-support-sqlite:${ktormVersion}")
    testImplementation("io.ktor:ktor-server-test-host:${ktorVersion}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}