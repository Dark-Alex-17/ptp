# Pandamonium Theorem Prover
## 2023 Note: This project was for a final project for a CS course in college. The repo is now archived and locked.

This project contains several different modules for different parts of the system.

* [persistence](persistence/README.md)
* [web-server](src/README.md)

## Getting Started

1. Clone the project. `git clone git@github.com:atusa17/tsp.git`
2. Open the `pandamonium-theorem-prover` folder with IntelliJ. Use auto-import for a Gradle project.
3. Individual Components have their own README. Continue there.

## Running the Tests

This project is unit tested with JUnit and Mockito. You can run the unit tests with IntelliJ or Gradle. To run them with IntelliJ, browse to any `*Test.java` file and use IntelliJ's built-in test runner to run or debug the test. To run all the unit tests with Gradle:

* On a Linux or Macintosh machine:

    ```$ ./gradlew test```
    
* On a Windows machine:

    ```$ gradlew.bat test```

You can also test modules individually:

* On a Linux or Macintosh machine:

    ```$ ./gradlew persistence:test```
  
* On a Windows machine:

    ```$ gradlew.bat persistence:test```

## Integration Tests

To run the integration tests with IntelliJ, browse to any `*Test.java` file residing in any module name `integrationTest` and use IntelliJ's built-in test runner to run or debug the test. To run all the integration tests with Gradle:

* On a Linux or Macintosh machine:

    ```$ ./gradlew integrationTest```
  
* On a Windows machine

    ```$ gradlew.bat integrationTest```
  
## Built with

* [Spring Boot](https://projects.spring.io/spring-boot/) - Web framework
* [Spring Web Flow](https://projects.spring.io/spring-webflow/) - MVC framework
* [Gradle](https://gradle.org/) - Dependency management
* [JUnit](http://junit.org/junit4/) - Unit tests
* [Mockito](http://site.mockito.org/) - Mock objects library
* [Lombok](https://projectlombok.org/) - Boilerplate Code Generator
