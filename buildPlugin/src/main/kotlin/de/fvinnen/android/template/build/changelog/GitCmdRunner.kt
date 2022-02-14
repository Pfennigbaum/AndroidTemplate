package de.fvinnen.android.template.build.changelog

import org.gradle.api.Project
import java.io.ByteArrayOutputStream

object GitCmdRunner {
    fun latestTag(project: Project): String{
        val output = ByteArrayOutputStream().use { outputStream ->
            project.exec {
                args= listOf("describe","--tags", "--abbrev=0")
                executable = "git"
                standardOutput = outputStream
            }
        }
        return output.toString()
    }

    fun getCommitSha(project: Project, versionTag: String): String{
        val output = ByteArrayOutputStream().use { outputStream ->
            project.exec {
                args= listOf("show-ref","--heads", "--hash", versionTag)
                executable = "git"
                standardOutput = outputStream
            }
        }
        return output.toString()
    }

    fun gitHistory(project: Project, fromSha: String, toSha: String): List<String>{
        val output = ByteArrayOutputStream().use { outputStream ->
            project.exec {
                args= listOf("log","$fromSha..$toSha", "--format:{\"title\":\"%s\",\"body\":\"%b\",\"author\":\"%an\",\"hash\":\"%h\"}")
                executable = "git"
                standardOutput = outputStream
            }
        }
        return output.toString().lines()
    }
}