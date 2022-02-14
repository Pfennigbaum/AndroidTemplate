package de.fvinnen.android.template.build.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import kotlin.reflect.KClass

public abstract class BasePlugin< Type: PluginExtension>(private val classT: KClass<Type>) : Plugin<Project> {
    abstract val extensionName: String
    internal lateinit var extension: Type

    abstract fun onApply(target: Project)

    override fun apply(target: Project) {
        extension = target.extensions.create(extensionName, classT)
        onApply(target)
    }


}

