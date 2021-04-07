plugins {
    androidApplication()
    kotlinAndroid()
    kotlinKapt()
    navSafeArgsKotlin()

}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.dawn.githubsearch"
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }




    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}



dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":repositories"))
    implementation(project(":network"))
    implementation(project(":business"))
    implementation(project(":featureGithubSearch"))


}