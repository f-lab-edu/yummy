import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.huijin"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	/*Web*/
	implementation("org.springframework.boot:spring-boot-starter-web")

	/*Security*/
	implementation("org.springframework.boot:spring-boot-starter-security")

	/*Thymeleaf*/
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

	/*test*/
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	/*JPA*/
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	/*Mysql*/
	runtimeOnly("com.mysql:mysql-connector-j")

	/*JWT 의존성*/
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.12.3")

	/*Kotlin*/
	implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "com.huijin.yummy.YummyApplication"
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}