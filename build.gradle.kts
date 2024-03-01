plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "kz.wonder"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.projectlombok:lombok:1.18.20")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")
    compileOnly("org.projectlombok:lombok:1.18.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:1.2.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.2")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")




}

tasks.withType<Test> {
    useJUnitPlatform()
}
