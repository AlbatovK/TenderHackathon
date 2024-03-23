import com.google.cloud.tools.jib.gradle.JibExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    kotlin("plugin.jpa") version "1.9.10"
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    kotlin("plugin.noarg") version "1.9.10"
    kotlin("plugin.allopen") version "1.9.10"
    id("com.google.cloud.tools.jib") version "3.3.1"
}

allOpen {
    annotations(
        "jakarta.persistence.Entity",
        "jakarta.persistence.Embeddable",
        "jakarta.persistence.MappedSuperclass"
    )
}

noArg {
    annotations(
        "jakarta.persistence.Entity",
        "jakarta.persistence.Embeddable",
        "jakarta.persistence.MappedSuperclass"
    )
}

group = "com.albatros"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {

    // Actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // OpenAI
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")

    // Elasticsearch
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

    // Vaadin
    //implementation("com.vaadin:vaadin-spring-boot-starter")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    annotationProcessor("org.projectlombok:lombok")

    // Persistence
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    runtimeOnly("org.postgresql:postgresql")

    // Swagger-UI + OpenApi
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Spring MVC
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    // Liquibase
    implementation("org.liquibase:liquibase-core")
}

dependencyManagement {
    imports {
        //   mavenBom("com.vaadin:vaadin-bom:24.3.7")
        mavenBom("org.springframework.ai:spring-ai-bom:0.8.1")
    }
}

val jibBaseImage = "eclipse-temurin:17-jre"
val jibImageArch = "amd64"

configure<JibExtension> {
    container.creationTime.set("USE_CURRENT_TIMESTAMP")
    from {
        image = jibBaseImage
        platforms {
            platform {
                architecture = jibImageArch
                os = "linux"
            }
        }
    }
    to {
        image = "spring-security:$version"
        tags = setOf("$version", "latest")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(
        file("detekt.yml")
    )
}

ktlint {
    android.set(false)
    ignoreFailures.set(true)
    reporters {
        reporter(ReporterType.HTML)
    }
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
