/**
 * Gradle KTS file to set different environment-related settings.
 * These settings can be used throughout the app
 * Mention any extra configuration in buildConfigField to use in modules
 * Use the RunConfig.kt file from the base library module to use the different config mentioned here
 *@since 1.0.0
 */

//Environment setting configurations

//enum class for different environments
public enum class ENVIRONMENT {
    DEV,
    UAT,
    PRE_PROD,
    PROD
}

//Set the current environment for the app
var environment: ENVIRONMENT by extra { ENVIRONMENT.DEV }

//app android sdk version settings
var baseCompileSDK by extra { 34 }
var baseTargetSDK by extra { 34 }
val baseMinSDK by extra { 26 }

// default app version
var versionName by extra { "1.0.0" }
var versionCode by extra { 1 }

//network and other constants settings
var baseUrl by extra { "" }

//Set different configurations based on the env
when (environment) {

    ENVIRONMENT.DEV -> {
        baseUrl = "https://catfact.ninja/"
        versionName = "1.0.0"
        versionCode = 1
    }

    ENVIRONMENT.UAT -> {
        baseUrl = ""
        versionName = "1.0.0"
        versionCode = 1

    }

    ENVIRONMENT.PRE_PROD -> {
        baseUrl = ""
        versionName = "1.0.0"
        versionCode = 1

    }

    ENVIRONMENT.PROD -> {
        baseUrl = ""
        versionName = "1.0.0"
        versionCode = 1

    }

    else -> {
        baseUrl = ""
        versionName = "1.0.0"
        versionCode = 1
    }
}
