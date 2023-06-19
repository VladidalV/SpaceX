buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        classpath("com.karumi:shot:5.13.0")
    }
}

plugins {
    id("nl.neotech.plugin.rootcoverage") version "1.6.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

allprojects {

    repositories {
        google()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        mavenCentral()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}

rootCoverage {
    // The default build variant for every module
    buildVariant = "debug"

    // Class & package exclude patterns
    excludes = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Module.*", // Modules for Hilt.
        "**/*Hilt*.*,", // Hilt auto-generated code.
        "**/Hilt_*.*,", // Hilt auto-generated code.
        "**/_HiltComponents_*.*,", // Hilt auto-generated code.
        "**/_HiltComponents.*.*,", // Hilt auto-generated code.
        "**/Hilt_*.*,", // Hilt auto-generated code.
        "**/*_GeneratedInjector.*,", // Hilt auto-generated code.
        "**/*InjectInterface.*,", // Hilt auto-generated code.
        "**/hilt_aggregated_deps/*,", // Hilt auto-generated code.
        "**/*hilt_aggregated_deps*",
        "**/*MembersInjector*.*", // Hilt auto-generated code.
        "**/*_Provide*Factory*.*",
        "**/*_Factory.*", //Hilt auto-generated code
        "**/*_Factory.InstanceHolder.*", //Hilt auto-generated code
        "**/codegen/*.*", //Hilt auto-generated code
        "**/*$*$*.*", // Anonymous classes generated by kotlin
        "**/*Directions.*", // Nav args generated code
        //add libraries
        "android/**/*.*",
        "androidx/**/*.*",
        "io/**/*.*",
        //remove what we don't test
        "androidTest/**/*.*",
        "test/**/*.*",
        "**/prieto/fernando/spacex/theme/**",
        "**/prieto/fernando/spacex/presentation/screens/**",
        "**/prieto/fernando/spacex/",
        "**/prieto/fernando/spacex/presentation/navigation/"
    )

    // Since 1.1 generateHtml is by default true
    generateCsv = false
    generateHtml = true
    generateXml = true

    // Since 1.2: When false the plugin does not execute any tests, useful when you run the tests manually or remote (Firebase Test Lab)
    executeTests = true

    // Since 1.2: Same as executeTests except that this only affects the instrumented Android tests
    executeAndroidTests = false

    // Since 1.2: Same as executeTests except that this only affects the unit tests
    executeUnitTests = true

    // Since 1.2: When true include results from instrumented Android tests into the coverage report
    includeAndroidTestResults = false

    // Since 1.2: When true include results from unit tests into the coverage report
    includeUnitTestResults = true

    // Since 1.4: Sets jacoco.includeNoLocationClasses, so you don't have to. Helpful when using Robolectric
    // which usually requires this attribute to be true
    includeNoLocationClasses = true
}