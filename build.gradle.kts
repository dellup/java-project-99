plugins {
	java
	checkstyle
	application
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security:2.7.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.springframework.security:spring-security-oauth2-jose:6.0.3")
	implementation("com.nimbusds:nimbus-jose-jwt:9.31")
	testImplementation("org.springframework.security:spring-security-test:6.2.0")
	implementation("org.springframework.security:spring-security-oauth2-resource-server:6.0.3")
	testImplementation("org.instancio:instancio-core:3.6.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("net.datafaker:datafaker:2.4.2")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.postgresql:postgresql:42.7.2")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:2.39.0")
	testImplementation("org.assertj:assertj-core:3.25.3")
	implementation("org.springframework.security:spring-security-config:6.3.5")
	implementation("org.springframework.security:spring-security-web:6.3.5")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
	inputs.dir(project.extra["snippetsDir"]!!)
	dependsOn(tasks.test)
}

application {
	mainClass = "hexlet.code.demo.AppApplication"
}
