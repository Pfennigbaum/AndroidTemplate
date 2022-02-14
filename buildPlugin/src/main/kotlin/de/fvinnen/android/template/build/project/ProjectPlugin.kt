package de.fvinnen.android.template.build.project

import de.fvinnen.android.template.build.plugin.BasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import javax.inject.Inject


public class ProjectPlugin: BasePlugin<ProjectExtension>(ProjectExtension::class) {

    override val extensionName: String
        get() = "fvProject"

    override fun onApply(target: Project) {

    }
}