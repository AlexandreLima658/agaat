plugins {
    java
    jacoco
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("unitTests") {
    useJUnitPlatform {
        includeTags("unitTest")
    }
}

tasks.register<Test>("integrationTests") {
    useJUnitPlatform {
        includeTags("integrationTest")
    }
}

tasks.register<Test>("e2eTests") {
    useJUnitPlatform {
        includeTags("e2eTest")
    }
}
