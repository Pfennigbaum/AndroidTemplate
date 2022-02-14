plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "de.fvinnen.android.template"
version="1.0.0"

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}



gradlePlugin {
    plugins {
        create("template-settings") {
            id = "template-settings"
            implementationClass = "de.fvinnen.android.template.build.settings.SettingsPlugin"
        }
        create("template-app") {
            id = "template-app"
            implementationClass = "de.fvinnen.android.template.build.app.AppPlugin"
        }
        create("template-changelog") {
            id = "template-changelog"
            implementationClass = "de.fvinnen.android.template.build.changelog.ChangelogPlugin"
        }
        create("template-check") {
            id = "template-check"
            implementationClass = "de.fvinnen.android.template.build.check.CheckPlugin"
        }
        create("template-library") {
            id = "template-library"
            implementationClass = "de.fvinnen.android.template.build.library.LibraryPlugin"
        }
        create("template-project") {
            id = "template-project"
            implementationClass = "de.fvinnen.android.template.build.project.ProjectPlugin"
        }
        create("template-publish") {
            id = "template-publish"
            implementationClass = "de.fvinnen.android.template.build.publish.PublishPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")

}
