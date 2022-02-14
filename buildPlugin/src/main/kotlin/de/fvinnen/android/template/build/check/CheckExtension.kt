package de.fvinnen.android.template.build.check

import de.fvinnen.android.template.build.plugin.PluginExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property

abstract class CheckExtension(project: Project): PluginExtension {
    val detekt: Property<Boolean> = project.objects.property<Boolean>().convention(true)
    val ktlint: Property<Boolean> = project.objects.property<Boolean>().convention(false)
    val warningAsError: Property<Boolean> =  project.objects.property<Boolean>().convention(true)
}