plugins {
    id("java")
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.minh.speechmanage"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot Starter Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // PostgreSQL
    implementation("org.postgresql:postgresql:42.7.5")

    // Lombok
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring Boot Starter Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Spring Boot DevTools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Spring Boot Starter Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.test {
    useJUnitPlatform()
}