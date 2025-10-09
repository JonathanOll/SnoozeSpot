plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    id("org.openapi.generator") version "7.15.0"
}

android {
    namespace = "iut.fauryollivier.snoozespot"
    compileSdk = 36

    defaultConfig {
        applicationId = "iut.fauryollivier.snoozespot"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.okhttp)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlintest.runner.junit5)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.logging.interceptor)

    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.androidx.ui.text.google.fonts)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.material.v160)
    implementation(libs.androidx.runtime.livedata.v160)
    implementation(libs.androidx.lifecycle.runtime.ktx.v294)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v294)
}

openApiGenerate {
    generatorName = "kotlin"
    library = "jvm-retrofit2"
    generateModelTests = false
    generateApiTests = false
    inputSpec = file("${rootDir.absolutePath}/../api/src/main/resources/openapi/documentation.yaml").toURI().toString()
    outputDir = file("${rootDir.absolutePath}/app/src/main/java/iut/fauryollivier/snoozespot/api/generated").absolutePath
    apiPackage = "iut.fauryollivier.snoozespot.generated.api"
    modelPackage = "iut.fauryollivier.snoozespot.generated.api.model"

    configOptions.set(
        mapOf(
            "dateLibrary" to "string",
            "useCoroutines" to "true",
            //"generateRoomModels" to "true",
            "serializableModel" to "true",
            "sortModelPropertiesByRequiredFlag" to "true",
            "sortParamsByRequiredFlag" to "true",
        )
    )
}