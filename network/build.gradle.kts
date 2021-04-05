plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))

}