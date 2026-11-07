

plugins {
    java
    application
}

group = "ar.edu.utn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    // Persistencia JSON
    implementation("com.google.code.gson:gson:2.13.1")

    // JUnit 5: pruebas automatizadas
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")

    // Permite que Gradle ejecute las pruebas con JUnit Platform
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("ar.edu.utn.sgsa.Main")
}

tasks.test {
    useJUnitPlatform()
}
