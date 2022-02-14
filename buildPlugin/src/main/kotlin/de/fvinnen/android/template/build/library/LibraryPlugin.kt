package de.fvinnen.android.template.build.library

import com.android.build.gradle.LibraryPlugin
import de.fvinnen.android.template.build.plugin.BasePlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformAndroidPlugin

public class LibraryPlugin : BasePlugin<LibraryExtension>(LibraryExtension::class) {

    override val extensionName: String
        get() = "fvLibrary"

    override fun onApply(target: Project) {
        target.apply<LibraryPlugin>()
    }
}