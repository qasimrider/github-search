plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
//    kotlinxSerialization()
}
dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":repositories"))
    implementation(project(":dtos"))

}