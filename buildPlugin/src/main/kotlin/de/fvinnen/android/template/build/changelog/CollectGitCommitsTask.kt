package de.fvinnen.android.template.build.changelog

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class CollectGitCommitsTask: DefaultTask() {
    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Input
    abstract val recentVersionTag: String
    @get:Input
    abstract val currentVersionTag: String

    @TaskAction
    fun execute() {
        val currentTagSha = getCommitHash(currentVersionTag)
        val recentTagSha = getCommitHash(recentVersionTag)
        val commits = getCommits(currentTagSha,recentTagSha)
        outputFile.get().asFile.bufferedWriter().apply {
            commits.forEach {
                write(it)
                newLine()
            }
            flush()
            close()
        }
    }

    private fun getCommitHash(versionTag: String): String {
        return ""
    }


    fun getCommits(from: String, until: String): List<String>{
        return listOf<String>(
            "feat!:",
            "feat(app)!:",
            "fix:",
            "fix():",
            "chore:",
            "chore():",
            "docs:",
            "docs():",
            "style:",
            "style():",
            "test:",
            "test():",
        )
    }

    data class Changelog(
        val features: List<String>,
        val fixes: List<String>,

        )

}