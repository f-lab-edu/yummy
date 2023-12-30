plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
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
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "com.huijin.yummy.YummyApplication"
	}
}