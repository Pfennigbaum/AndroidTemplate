package de.fvinnen.android.template.build.project

import de.fvinnen.android.template.build.plugin.PluginExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property

abstract class ProjectExtension(project: Project) : PluginExtension{
    val compileSdkVersion: Property<Int> = project.objects.property<Int>().convention(31)
    val applicationId: Property<String> = project.objects.property<String>().convention("de.fvinnen.android.template")
    val minSdk:  Property<Int> = project.objects.property<Int>().convention(27)
    val targetSdk: Property< Int> = project.objects.property<Int>().convention(31)
    val versionCode: Property<Int> = project.objects.property<Int>().convention(1)
    val versionName: Property<String> = project.objects.property<String>().convention("0.0.0")
    val javaVersion: Property<JavaVersion> = project.objects.property<JavaVersion>().convention(JavaVersion.VERSION_11)
}
