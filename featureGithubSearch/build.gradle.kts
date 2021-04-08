plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
//    kotlinParcelize()
//    kotlinxSerialization()
    navSafeArgsKotlin()
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.imageLibs)

    implementation(project(":common"))
    implementation(project(":business"))
    implementation(project(":dtos"))


}