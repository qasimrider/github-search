import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.internal.artifacts.dependencies.AbstractExternalModuleDependency

/**
 * This class have all the dependencies of the project
 * along with the Extension functions on the Dependency handler
 * creating the list of specific group of dependencies
 */
object AppDependencies {




    //region All Dependencies

    //region Kotlin std
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    //endregion

    //region Android UI
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout ="androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.materialComponents}"
    //endregion

    //region Navigation
    private val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKotlin}"
    private val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationKotlin}"

    //endregion

    //region LifeCycle
    private val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
    private val lifeCycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifeCycle}"
    //endregion

    //region Coroutines
    private val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    //endregion

    //region Retrofit
    private val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitAdapter}"
    private val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    private val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    private val retrofitLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLogging}"
    //endregion

    //region Androidx
    private val scopeAndroidx = "org.koin:koin-androidx-scope:${Versions.androidx}"
    private val viewModelAndroidx = "org.koin:koin-androidx-viewmodel:${Versions.androidx}"
    private val fragmentAndroidx =  "org.koin:koin-androidx-fragment:${Versions.androidx}"
    private val koinAndroid =  "org.koin:koin-android:${Versions.androidx}"
//    private val extensionsAndroidx =  "org.koin:koin-androidx-ext:${Versions.androidx}"
    //endregion

    //region MultiScreen Support
    private val multiScreenSupport="com.intuit.sdp:sdp-android:${Versions.multiScreenSizes}"
    //endregion

    //region Testing
    private val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    //endregion

    //region Image Libs
    private val glide = "com.github.bumptech.glide:glide:4.11.0"
    private val circleImageView = "de.hdodenhof:circleimageview:3.1.0"
    //endregion

    //region Serialization

    private val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    //endregion
    // endregion

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(navigationFragment)
        add(navigationUi)
        add(lifeCycle)
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(retrofitAdapter)
        add(retrofit2)
        add(retrofitConverter)
        add(retrofitLogging)
        add(viewModelAndroidx)
        add(fragmentAndroidx)
        add(koinAndroid)
        add(kotlinSerialization)
        add(retrofitLogging)
    }


    val imageLibs = arrayListOf<String>().apply {
        add(glide)
        add(circleImageView)
    }

    val customLibs = arrayListOf<String>().apply {
        add(multiScreenSupport)
//        add(expandableRecyclerview)

    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(fragmentTesting)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockito)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.implementation(singleDependency: String): String {
    return "implementation$singleDependency"

}

fun DependencyHandler.implementationProject(moduleDependency: String): String {
    return "implementation project($moduleDependency)"

}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}