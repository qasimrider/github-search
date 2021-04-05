plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
}




dependencies {

    implementation(AppDependencies.appLibraries)
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":dtos"))

}