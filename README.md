# BookingGo

My solution for the [BookingGo technical assesment](https://github.com/rideways/technical_test).

**This repository consists of three projects...**
- **Part 1A:** A console application to output ride results [{carType} - {price}] for Dave's taxis only. Searching by pickup and drop off location (2 parameters).
- **Part 1B:** A console application to output ride results [{carType} - {supplier} - {price}] for all three taxi suppliers. Searching by pickup and drop off location, and with a filter on passenger numbers (3 parameters).
- **Part 2:** A REST API that provides the same functionality as Part1B.

---

## Envrionment Setup
Download the respository.
```
$ git clone https://github.com/CharlesWatsonUK/BookingGo
```
**Machine Requirements:**
- **Java JDK8**
- **Apache Maven 3.5**

**Maven Dependencies:**
[Click here](https://github.com/CharlesWatsonUK/tech-dependencies.md) for a list of dependencies/ technologies I've used.


Note: If you get a ```Unable to access jarfile``` error, try closing and reopening the command line window, or try moving the JAR file to another directory and running it from there.

If you have any queries or issues regarding my solutions please don't hesitate to ask me: charles.w9788@googlemail.com
I look forward to hearing your feedback!

---

## Build & Run Part1A
1) Go to the root directory of Part1A
```
$ cd BookingGo/BookingGo_Part1A
```
2) Compile the java source files to class files
```
$ mvn clean compile
```
3) Package these files and any dependencies' files into a single executable JAR file.
This process will also run some tests.
```
$ mvn package
```
4) Run the solution by executing the JAR file (it will be placed into a new folder - /target)
It takes two parameters pickup location and dropoff location.
The ride results should display in the terminal window.
```
$ cd target
$ java -jar BookingGo_Part1A-1.0.0 51.470020,-0.454295 51.470021,-0.454280
```

---

## Build & Run Part1B
1) Go to the root directory of Part1B
```
$ cd BookingGo/BookingGo_Part1B
```
2) Compile the java source files to class files
```
$ mvn clean compile
```
3) Package these files and any dependencies' files into a single executable JAR file.
This process will also run some tests.
```
$ mvn package
```
4) Run the solution by executing the JAR file (it will be placed into a new folder - /target)
It takes three parameters pickup location, dropoff location and the number of passengers.
The ride results should display in the terminal window.
```
$ cd target
$ java -jar BookingGo_Part1B-1.0.0 51.470020,-0.454295 51.470021,-0.454280 5
```

---

## Build & Run Part2
1) Go to the root directory of Part2
```
$ cd BookingGo/BookingGo_Part2
```
2) Compile the java source files to class files
```
$ mvn clean compile
```
3) Package these files and any dependencies' files into a single executable JAR file.
This process will also run some tests.
```
$ mvn package
```
4) Start the API by executing the JAR file (it will be placed into a new folder - /target)
```
$ cd target
$ java -jar BookingGo_Part2-1.0.0
```
5) To make a request to the API consult the [API documentation](https://github.com/CharlesWatsonUK/apiDocs.md)
