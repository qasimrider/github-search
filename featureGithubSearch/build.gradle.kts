plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
    kotlinxSerialization()
    navSafeArgsKotlin()
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.imageLibs)

    implementation(project(":common"))
    implementation(project(":business"))
    implementation(project(":dtos"))

}