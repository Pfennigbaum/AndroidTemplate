package de.fvinnen.android.template.build.app

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppPlugin
import de.fvinnen.android.template.build.plugin.BasePlugin
import de.fvinnen.android.template.build.project.ProjectExtension
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainSpec
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformAndroidPlugin

public class AppPlugin : BasePlugin<AppExtension>(AppExtension::class) {
    override val extensionName: String
        get() = "fvApp"

    override fun onApply(target: Project) {
        target.apply<AppPlugin>()

        target.rootProject.extensions.configure<ProjectExtension>{
            val projectExtension = this
            target.extensions.configure<ApplicationExtension>{
                val debugSigningConfig = signingConfigs.getByName("debug"){
                    storeFile = target.rootProject.file("keystores/debug.keystore")
                    storePassword = "android"
                    keyAlias = "androiddebugkey"
                    keyPassword = "android"
                }

                val releaseSigningConfig = signingConfigs.create("release"){
                    storeFile = target.rootProject.file("keystores/release.keystore")

                    val keystoreAlias: String? by target.rootProject
                    val keyPass: String? by target.rootProject
                    val storePass: String? by target.rootProject

                    storePassword = storePass ?: System.getenv("KEYSTORE_PASSWORD")
                    keyAlias = keystoreAlias ?: System.getenv("KEYSTORE_ALIAS")
                    keyPassword = keyPass ?: System.getenv("KEY_PASSWORD")
                }

                defaultConfig {
                    applicationId = projectExtension.applicationId.get()
                    compileSdk = projectExtension.compileSdkVersion.get()
                    minSdk = projectExtension.minSdk.get()
                    targetSdk = projectExtension.targetSdk.get()
                    versionCode = projectExtension.versionCode.get()
                    versionName = projectExtension.versionName.get()
                    signingConfig = debugSigningConfig
                    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
                }
                buildTypes {
                    getByName("release"){
                        signingConfig = releaseSigningConfig
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                    getByName("debug"){
                        applicationIdSuffix =".debug"
                    }
                }
                compileOptions {
                    sourceCompatibility = projectExtension.javaVersion.get()
                    targetCompatibility = projectExtension.javaVersion.get()
                }

            }
        }
    }
}
