# Technologies
##### App
- Java 8 - core language
- GSON - JSON handling
- Spring Boot - REST API (part 2 only)

##### Test
- JUnit 5 (Jupiter API) (part 1 only)
- Spring Boot Test - (part 2 only)
    * Spring Boot Test package comes with JUnit4.

##### Build
- Apache Maven 3 - build & dependency management
  - Maven Surefire Plugin - so JUnit5 tests are picked up by Maven (part 1 only)
  - Apache Shade Plugin - puts everything in the JAR (part 1 only)
  - Spring Boot Maven Plugin - puts everything in the JAR (part 2 only)

---

# Part1A & Part1B Dependencies
- **GSON** com.google.code.gson - gson - 2.8.5
- **JUnit5** org.junit.jupiter - junit-jupiter-api - 5.0.3
- **JUnit Surefire Provider** org.junit.platform - junit-platform-surefire-provider - 1.0.3
- **JUnit Jupiter Engine** org.junit.jupiter - junit-jupiter-engine - 5.0.3
- **Maven JAR Plugin** - org.apache.maven.plugins - maven-jar-plugin - 3.1.0
- **Maven Shade Plugin** - org.apache.maven.plugins - maven-shade-plugin - 1.6
- **Maven Surefire Plugin** - maven-surefire-plugin - 2.19.1

# Part2 Dependencies
- **Spring Boot Strater Parent** - org.springframework.boot - spring-boot-starter-parent - 2.0.3.RELEASE
- **Spring Boot Starter Web** - org.springframework.boot - spring-boot-starter-web
- **Spring Boot Starter Test** - org.springframework.boot - spring-boot-starter-test
- **JSON-Path** - com.jayway.jsonpath - json-path
- **GSON** - com.google.code.gson - gson - 2.8.5
- **Spring Boot Maven Plugin** - org.springframework.boot - pring-boot-maven-plugin

For a full list of dependencies (inlcuding those of inherited POMs) you can generate an effective POM with the following command in the root of the project you are interested in. This will be displayed in the command line window.
```
$ cd BookingGo/BookingGo_Part2
$ mvn help:effective-pom
```
