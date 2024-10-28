plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.register("jacocoTestReport", JacocoReport::class) {
    dependsOn("testDevDebugUnitTest") // Đảm bảo unit test chạy trước khi đo coverage

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    // Đường dẫn đến file output
    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")

    val debugTree = fileTree("${layout.buildDirectory}/intermediates/javac/debug") {
        exclude(fileFilter)
    }

    val kotlinDebugTree = fileTree("${layout.buildDirectory}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    sourceDirectories.setFrom(files("src/main/java"))
    classDirectories.setFrom(files(debugTree, kotlinDebugTree))
    executionData.setFrom(fileTree(buildDir).include("**/jacoco/testDebugUnitTest.exec"))
}

android {
    namespace = "com.git.admin"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.git.admin"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions += listOf("environment")
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
        }
        create("staging") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
            )
        }
    }

    tasks.register<Javadoc>("generatePackageJavadoc") {
        description = "Generates Javadoc for specific package"

        // Cách 1: Sử dụng sourceTree để chỉ định package
        source(android.sourceSets.getByName("main").java.srcDirs.map { srcDir ->
            fileTree(srcDir) {
                include("com/git/admin/domain/usecase/user/*.kt")  // Đường dẫn package cụ thể
                // Có thể thêm nhiều package
                // include("com/example/myapp/feature1/**/*.java")
                // include("com/example/myapp/feature2/**/*.java")
            }
        })

        // Cách 2: Sử dụng setIncludes để chỉ định package
        // source(android.sourceSets.getByName("main").java.srcDirs)
        // setIncludes(setOf("**/model/**/*.java"))

        classpath = files(android.bootClasspath.joinToString(File.pathSeparator))

        // Cấu hình options
        (options as StandardJavadocDocletOptions).apply {
            encoding = "UTF-8"
            charSet = "UTF-8"
            author(true)
            version(true)
            title = "Package Documentation"

            // Đặt mức độ hiển thị
            setMemberLevel(JavadocMemberLevel.PROTECTED)

            // Thêm links đến Android API
            links("https://developer.android.com/reference/")

            // Bỏ qua lỗi Javadoc
            addStringOption("Xdoclint:none", "-quiet")

            // Sử dụng HTML5
            addBooleanOption("html5", true)
        }

        // Không fail khi có lỗi
        isFailOnError = false

        // Thư mục đầu ra
        setDestinationDir(file("$buildDir/docs/javadoc-package"))
    }
}

dependencies {
    // DEFAULT DEPENDENCIES
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // COMPOSE
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.navigation.compose)

    // REMOTE
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.adapter.rxjava2)
    implementation(libs.okhttp3)
    implementation(libs.io.coil.kt)

    // HILT
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    implementation(libs.hilt.compose.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compose.compiler)

    // ROOM
    implementation(libs.room)
    implementation(libs.room.ktx)
    kapt(libs.room.kapt)

    // PAGER
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicator)
    implementation(libs.accompanist.systemuicontroller)

    // UNIT TEST
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.core.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
