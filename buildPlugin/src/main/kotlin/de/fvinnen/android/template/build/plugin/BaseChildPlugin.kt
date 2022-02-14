package de.fvinnen.android.template.build.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType
import kotlin.reflect.KClass

abstract class BaseChildPlugin<Type : PluginExtension, ParentType: PluginExtension>(private val classT: KClass<Type>, private val classP: KClass<ParentType>) : BasePlugin<Type>(classT) {
    internal lateinit var parentExtension: ParentType

    override fun apply(target: Project) {
        parentExtension = target.extensions.getByType(classP)
        extension = parentExtension.extensions.create(extensionName, classT)
        onApply(target)
    }
}