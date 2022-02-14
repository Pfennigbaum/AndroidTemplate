package de.fvinnen.android.template.build.check

import com.android.build.gradle.BaseExtension
import com.android.builder.model.LintOptions
import de.fvinnen.android.template.build.plugin.BasePlugin
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import kotlin.reflect.KClass

public class CheckPlugin : BasePlugin<CheckExtension>(CheckExtension::class) {
    override val extensionName: String
        get() = "fvCheck"

    override fun onApply(target: Project) {
        includeKtLint(target)
        includeDetekt(target)
        configureLint(target)
    }

    private fun configureLint(target: Project) {
        target.extensions.configure<BaseExtension>{
            extension.warningAsError.map {
                lintOptions{
                    isWarningsAsErrors = it
                }
            }
        }
    }

    private fun includeKtLint(target: Project) {
        target.afterEvaluate {
            extension.ktlint.apply {
                finalizeValue()
                if (!get()) return@apply
                target.plugins.apply(KtlintPlugin::class.java)
                target.extensions.configure<KtlintExtension> {
                    coloredOutput.set(true)
//                     TODO(#2): Add Ktlint baseline file support
                }
            }
        }
    }

    private fun includeDetekt(target: Project) {
        target.afterEvaluate {
            extension.detekt.apply {
                finalizeValue()
                if (!get()) return@apply

                target.plugins.apply(DetektPlugin::class.java)
                target.extensions.configure<DetektExtension> {
//                     TODO(#1): Add Detekt baseline file support
                }
            }
        }
    }
}