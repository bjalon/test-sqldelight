
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
        classpath("com.squareup.sqldelight:gradle-plugin")
    }
}

plugins {
    kotlin("jvm")
    id("com.squareup.sqldelight")
}

dependencies {
    implementation("com.squareup.sqldelight:sqlite-driver:1.5.3")

    testImplementation("com.squareup.sqldelight:sqlite-driver:1.5.3")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.testfx:testfx-core:4.0.16-alpha")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.mockk:mockk:1.12.8")

}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

sqldelight {
    database("LocalDb") {
        packageName = "com.qastia"
        sourceFolders = listOf("kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}


