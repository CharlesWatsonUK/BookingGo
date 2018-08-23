# BookingGo

My solution for the [BookingGo technical assesment](https://github.com/rideways/technical_test).
**This repository consists of two projects (for parts 1 and 2 of the task)**

Download the respository.
```
$ git clone https://github.com/CharlesWatsonUK/BookingGo
```

### Run the solutions (pre-compiled)
Requires: Java 8 - JRE 8

1) Go to the dist folder.
```
$ cd BookingGo/target
```

2)   Run the distributable (JAR) files.

A) Run the JAR for Task 1 - CLI Solution.
```
$ java -jar BookingGo_Part1.jar <pickup> <dropoff> <passengers>
```
B) Run the JAR for Task 2 - REST API Solution.
```
$ java -jar <BookingGo_Part2.jar
```


### Compile and run the solutions (including tests)
Requires: Java 8 - (JDK & JRE), Apache Maven 3
1) Go to the project root for the solution part you want.
```
$ cd BookingGo/BookingGo_Part1
```
OR
```
$ cd BookingGo/BookingGoPart2
```
2) Maven compile
```
$ mvn clean compile
```
3) Package compiled classes and dependencies into a JAR - tests are also run.
```
$ mvn package
```
4) Run your newly compiled and packaged distributable.

A) If you've just packaged Part 1...
```
$ cd target
$ java -jar <JAR file> <pickup> <dropoff> <passengers>
```
B) If you've just packaged Part 2...
```
$ cd target
$ java -jar <JAR file>
```
[Consult the API documentation](https://github.com/CharlesWatsonUK/apiDocs.md) to make requests.

### Technologies
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
  
