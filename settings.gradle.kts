// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.kotlin").version(extra["kotlin.version"] as String)
        id("com.squareup.sqldelight").version(extra["sqlDelight.version"] as String)
    }

}

rootProject.name = "test-sqldelight"

