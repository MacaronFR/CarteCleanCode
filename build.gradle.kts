import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val ktormVersion: String by project
val logbackVersion: String by project
val DBCPVersion: String by project
val mariaDBConnectorVersion: String by project

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

group = "fr.imacaron"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    implementation("io.ktor:ktor-server-resources:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("org.ktorm:ktorm-support-mysql:${ktormVersion}")
    implementation("org.apache.commons:commons-dbcp2:${DBCPVersion}")
    implementation("org.mariadb.jdbc:mariadb-java-client:${mariaDBConnectorVersion}")
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