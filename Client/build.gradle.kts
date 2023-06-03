import org.intellij.markdown.html.urlEncode
import org.jetbrains.kotlin.codegen.intrinsics.ArrayOf

plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.5.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("org.jetbrains.dokka") version "1.7.10"
    id ("org.openjfx.javafxplugin") version "0.0.8"
    application
}

group = ""
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("org.openjfx:javafx-base:17-ea+11")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.0")
    implementation("com.charleskorn.kaml:kaml:0.52.0")
    implementation("org.yaml:snakeyaml:2.0")
    implementation ("no.tornado:tornadofx:1.7.20")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
javafx {
    version = "11.0.2"
    modules("javafx.graphics", "javafx.controls")
}
tasks.named<JavaExec>("run"){
    standardInput=System.`in`
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
tasks.withType<JavaCompile> {
    options.encoding="UTF-8"
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("/home/roman/Client2.jar")
}
tasks.withType<Test> {
    useJUnitPlatform()
}








