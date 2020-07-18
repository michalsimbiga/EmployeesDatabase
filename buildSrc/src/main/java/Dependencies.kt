@file:Suppress("MayBeConstant")

object ApplicationID {
    val id = "com.employeedatabase"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Modules {
    val app = ":app"
    val domain = ":domain"
    val data = ":data"
    val db = ":db"
}

object Versions {
    val kotlin = "1.3.50"

    val gradle = "3.6.3"
    val minSDK = 21
    val targetSDK = 29
    val compileSDK = 29

    val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val kotlinCoroutines = "1.3.2"

    val navigation = "2.2.0"
    val appCompat = "1.1.0"
    val material = "1.1.0"
    val core_ktx = "1.2.0"
    val constraint = "1.1.3"

    val koinAndroidX = "2.1.5"
    val koinViewModelAndroidX = "2.1.5"

    val mvrx = "1.4.0"
    val epoxy = "3.9.0"

    val sqldelight = "1.2.2"

    val junit = "4.12"
    val mockk = "1.9.3"

    val timber = "4.7.1"

    val leakCanary = "2.0"

    val ktlint = "0.29.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

    val koinAndroidX = "org.koin:koin-androidx-scope:${Versions.koinAndroidX}"
    val koinViewModelAndroidX = "org.koin:koin-androidx-viewmodel:${Versions.koinViewModelAndroidX}"

    val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"

    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyKapt = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"

    val mvrx = "com.airbnb.android:mvrx:${Versions.mvrx}"

    val sqldelightAndroidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val material = "com.google.android.material:material:${Versions.material}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
}

object TestLibraries {
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val junit = "junit:junit:${Versions.junit}"
}
