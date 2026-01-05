import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import java.io.File

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    id("org.openapi.generator") version "7.17.0"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
}

android {
    secrets {
        // Optionally specify a different file name containing your secrets.
        // The plugin defaults to "local.properties"
        propertiesFileName = "secrets.properties"

        // A properties file containing default secret values. This file can be
        // checked in version control.
        defaultPropertiesFileName = "local.properties"

        // Configure which keys should be ignored by the plugin by providing regular expressions.
        // "sdk.dir" is ignored by default.
        ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
        ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
    }


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

    flavorDimensions += "env"
    productFlavors {
        create("development") {
            dimension = "env"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "clefDev", "\"API-KEY-DEV\"")
        }

        create("beta") {
            dimension = "env"
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
            buildConfigField("String", "clefBeta", "\"API-KEY-DEV\"")
        }

        create("production") {
            dimension = "env"
            buildConfigField("String", "clefProd", "\"API-KEY-BETA\"")
        }
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
        buildConfig = true
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/*",
                "draft*/**"
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

    implementation(libs.openapi.generator)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.maps.compose)

    implementation("com.airbnb.android:lottie-compose:6.6.10")

    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serialization.json)
    implementation (libs.androidx.datastore.preferences.rxjava2)
    implementation(libs.androidx.datastore.preferences.rxjava3)
}

configurations.all {
    exclude(group = "javax.validation", module = "validation-api")
}

val openApiSpec = "${rootDir.absolutePath}/../api/src/main/resources/openapi/documentation.yaml"
val srcDirPath = "${rootDir.absolutePath}/app/src/main/java/iut/fauryollivier/snoozespot/api/generated"
val generatedPackageName = "iut.fauryollivier.snoozespot.api.generated"


tasks.register<GenerateTask>("openApiGenerateRetrofit") {
    val packageName = generatedPackageName

    generatorName.set("kotlin")
    library.set("jvm-retrofit2")

    generateModelTests.set(false)
    generateApiTests.set(false)

    inputSpec.set(file(openApiSpec).toURI().toString())
    outputDir.set(srcDirPath)

    modelPackage.set("$packageName.model")
    apiPackage.set("$packageName.api")

    configOptions.set(
        mapOf(
            "dateLibrary" to "string",
            "useCoroutines" to "true",
            "serializableModel" to "true",
            "sortModelPropertiesByRequiredFlag" to "true",
            "sortParamsByRequiredFlag" to "true",
        )
    )
}

tasks.register<GenerateTask>("openApiGenerateRoom") {
    mustRunAfter("openApiGenerateRetrofit")
    val packageName = generatedPackageName

    generatorName.set("kotlin")
    library.set("jvm-volley")

    generateModelTests.set(false)
    generateApiTests.set(false)

    inputSpec.set(file(openApiSpec).toURI().toString())
    outputDir.set("$srcDirPath/room")

    modelPackage.set("$packageName.model")

    configOptions.set(
        mapOf(
            "dateLibrary" to "string",
            "serializationLibrary" to "gson",
            "generateRoomModels" to "true",
            "sortModelPropertiesByRequiredFlag" to "true",
            "sortParamsByRequiredFlag" to "true"
        )
    )
}

tasks.register("updateRetrofitModel") {
    mustRunAfter("openApiGenerateRoom")
    group = "openapi"
    doLast {
        val retrofitDir = file("$srcDirPath/src/main/kotlin/iut/fauryollivier/snoozespot/api/generated/model")
        val roomDir = file("$srcDirPath/src/main/kotlin/org/openapitools/client/models/room")

        retrofitDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { retrofitFile ->
            val className = retrofitFile.nameWithoutExtension
            val classNameRoomModel = retrofitFile.nameWithoutExtension + "RoomModel"
            val roomFile = File(roomDir, "$classNameRoomModel.kt")

            if(!roomFile.exists()) {
                return@forEach
            }

            val retrofitText = retrofitFile.readText()

            if (!retrofitText.contains("ITransformForStorage<$className>")) {

                // on parse le deuxieme file

                val packageLine = retrofitText.lines().firstOrNull { it.startsWith("package") } ?: ""
                val importInjection = """

                import org.openapitools.client.infrastructure.ITransformForStorage
                import org.openapitools.client.models.room.${className}RoomModel""".trimIndent()

                var textWithImport = retrofitText.replaceFirst(packageLine, "$packageLine\n$importInjection")

                val pattern = Regex("\\) : Serializable \\{")
                var textWithInterface = textWithImport.replace(pattern, ") : Serializable, ITransformForStorage<$classNameRoomModel> {")

                val toRoomModelRegex = Regex("override fun toRoomModel\\(\\): [^)]*\\)")
                val companionObjectRegex = Regex("companion object \\{[^\\}]*\\}")

                val toRoomModel = toRoomModelRegex.find(roomFile.readText())?.groupValues?.get(0)
                if(toRoomModel == null) {
                    println("no toRoomModel in $classNameRoomModel, skipping")
                    return@forEach
                }

                val finalText = textWithInterface.replace(companionObjectRegex) { it->
                    it.value.replace("}", "}\n$toRoomModel")
                }

                retrofitFile.writeText(finalText)
                println("updated class $className")
            }
        }
    }
}

tasks.register("updateRoomModel") {
    mustRunAfter("updateRetrofitModel")
    group = "openapi"
    doLast {
        val roomModelDir = file("$srcDirPath/room/src/main/java/org/openapitools/client/models/room")

        roomModelDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { roomModelFile ->

            val roomModelText = roomModelFile.readText()
            val line = roomModelText.lines().firstOrNull { it.startsWith("import iut.fauryollivier") }
            if(line == null) {
                println("no import found in ${roomModelFile.nameWithoutExtension}, skipping")
                return@forEach
            }

            val finalText = roomModelText.replace(line, line.replace("room", "retrofit"))

            roomModelFile.writeText(finalText)
            println("room model ${roomModelFile.nameWithoutExtension} updated")

        }

    }
}

tasks.register("initRoomLists") {
    mustRunAfter("cleanupAndMergeGenerated")
    group = "openapi"
    doLast {
        val roomModelsDir = file("$srcDirPath/src/main/kotlin/org/openapitools/client/models/room")

        roomModelsDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { roomModelFile ->
            var text = roomModelFile.readText()

            // Regex qui détecte : @Ignore lateinit var nom: List<Something>
            val listLateinitRegex = Regex("@Ignore\\s+lateinit var (\\w+): kotlin\\.collections\\.List<[^>]+>")

            // Remplace par : var nom: List<Type> = emptyList()
            text = listLateinitRegex.replace(text) { match ->
                val propName = match.groupValues[1]
                "@Ignore var $propName: ${match.value.substringAfter(": ")} = emptyList()"
            }

            roomModelFile.writeText(text)
            println("Initialisé les listes dans ${roomModelFile.name}")
        }
    }
}

tasks.register("cleanupAndMergeGenerated") {
    mustRunAfter("updateRoomModel")
    group = "openapi"
    doLast {

        val sourceBaseDir = "$srcDirPath/room/src/main/java/org/openapitools/client/"
        val sourceITransformFile = file("$sourceBaseDir/infrastructure/ITransformForStorage.kt")
        val sourceModelsDir = file("$sourceBaseDir/models/room")

        val targetBaseDir = "$srcDirPath/src/main/kotlin/org/openapitools/client/"
        val targetITransformFile = file("$targetBaseDir/infrastructure/ITransformForStorage.kt")
        val targetModelsDir = file("$targetBaseDir/models/room")
        if(true) {
            val success = sourceITransformFile.renameTo(targetITransformFile)
            println(if (success) "Fichier ITransform déplacé" else "erreur du déplacement de ITransform")
        }
        targetModelsDir.mkdirs()

        val files = sourceModelsDir.listFiles { file -> file.isFile && file.extension == "kt" } ?: arrayOf()

        files.forEach {  f->
            val targetFile = File(targetModelsDir, f.name)
            val success = f.renameTo(targetFile)
            println(if (success) "Fichier ${f.nameWithoutExtension} déplacé" else "erreur du déplacement de ${f.nameWithoutExtension}")
        }

        if(true) {
            val success = file("$srcDirPath/room").deleteRecursively()
            println(if (success) "Dossier room supprimé" else "erreur de la suppression du dossier room")
        }

    }
}

tasks.register("openApiGenerateAll") {
    dependsOn("openApiGenerateRetrofit", "openApiGenerateRoom", "updateRetrofitModel", "updateRoomModel", "initRoomLists", "cleanupAndMergeGenerated")
    group = "openapi"
}

tasks.register("copyCertificate") {
    doLast {
        val certificatePath = "${rootDir.absolutePath}/../api/build/exportedCertificateForAndroid.cer"
        val certificateFile = file(certificatePath)
        val targetDir = file("src/main/res/raw")
        val targetFile = File(targetDir, "auto_signed_api_certificate.cer")

        if (!certificateFile.exists()) {
            println("Certificate not found at: $certificatePath")
            return@doLast
        }

        targetDir.mkdirs()
        certificateFile.copyTo(targetFile, overwrite = true)

        println("Certificate copied to: ${targetFile.absolutePath}")
    }
}

tasks.named("preBuild") {
    dependsOn("copyCertificate", "openApiGenerateAll")
}