package de.fvinnen.android.template.build.changelog

import de.fvinnen.android.template.build.plugin.BaseChildPlugin
import de.fvinnen.android.template.build.plugin.BasePlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

public class ChangelogPlugin : BasePlugin<ChangelogExtension>(ChangelogExtension::class) {
    override val extensionName: String
        get() = "fvChangelog"

    override fun onApply(target: Project) {

//        target.tasks.register<CollectGitCommitsTask>("abc") {
//
//            outputFile.map {
//                extension.changelog.set(it.asFile.readLines())
//            }
//        }

        println(GitCmdRunner.latestTag(target))
        println(GitCmdRunner.getCommitSha(target, "v0.0.2"))
        println(GitCmdRunner.gitHistory(target,"",""))
        //# configuration:
        // -

        // collect commits
        // parse commits into datastructure
        // generate changelog

    }
}