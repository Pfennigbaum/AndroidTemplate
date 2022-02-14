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

        val latestTag = GitCmdRunner.latestTag(target)
//        val latestTagCommitSha = GitCmdRunner.getCommitSha(target, latestTag)
        val latestTagCommitSha = GitCmdRunner.getCommitSha(target, "v0.0.2")
        val currentCommitSha = GitCmdRunner.getCurrentCommitSha(target)
        println(latestTagCommitSha)
        println(currentCommitSha)
        val history = GitCmdRunner.gitHistory(target,latestTagCommitSha, currentCommitSha)
        println()
//        println(GitCmdRunner.getCommitSha(target, "v0.0.2"))
//        println(GitCmdRunner.gitHistory(target,"",""))
        //# configuration:
        // -

        // collect commits
        // parse commits into datastructure
        // generate changelog

    }
}