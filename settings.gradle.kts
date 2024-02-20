@file:Suppress("UnstableApiUsage")

import java.net.URI

pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()

    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven {
            url= URI("https://jitpack.io")
        }
    }

}

rootProject.name = "ProjectTemplateWithDaggerNative"
include(":library:base")
include(":examples:cat-facts")
include(":app")


