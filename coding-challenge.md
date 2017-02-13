# Coding Challenge

## Slot machine

The challenge is to implement a slot machine (like the one in a casino).
The application should be implemented in Java and make use of the Spring Framework and be runnable with Spring Boot. Use a RESTful interface to send and receive JSON formatted messages.
Code your application with as much care and diligence as you would in daily business. For this challenge we already provided you with a skeleton application based on Maven (pom file), located in the source folder.

### Functional requirements

These are the rules of the game: A player tries his luck and pulls the lever of the slot machine. The machine consists of three reels. Possible outcomes of each reel are apple, banana or citrus fruit. If by chance all three reels show the same fruit, the player is going to win.

### Non-functional requirements

1. Use the last major version of Java as programming language.

2. The application can be compiled and built with Maven or Gradle without further adjustments.

3. The application is self-contained and can be executed by calling ```java -jar <application.jar>```

4. Make sure the game works correctly.

5. The game is played by calling a RESTful API. A graphical user interface is not required.

6. Make sure the application can be monitored from the outside and delivers some metrics.

### DevOps task

Sketch in a Markdown file how you would approach the deployment to ensure continuous delivery.

* Which tools would you use and why?
* What are the major concerns regrading test and integration?

### Helpful pages

1. <https://spring.io/guides/gs/spring-boot/>

2. <http://spring.io/guides/gs/rest-service/>

3. <http://martinfowler.com/bliki/TestPyramid.html>

### Information

1. <https://en.wikipedia.org/wiki/Slot_machine>
