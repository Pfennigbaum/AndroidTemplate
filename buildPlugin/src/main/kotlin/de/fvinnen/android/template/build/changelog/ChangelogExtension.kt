package de.fvinnen.android.template.build.changelog

import de.fvinnen.android.template.build.plugin.PluginExtension
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.kotlin.dsl.listProperty


abstract class ChangelogExtension(project: Project): PluginExtension {
    val changelog: ListProperty<String> = project.objects.listProperty()

}