plugins {
    id("java")
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-dbcp2:2.9.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.hibernate:hibernate-core:5.6.15.Final")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-orm")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
}

tasks.jar {
    archiveFileName.set("app.jar")
}

tasks.test {
    useJUnitPlatform()
}
