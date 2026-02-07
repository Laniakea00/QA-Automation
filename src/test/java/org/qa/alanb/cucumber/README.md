# Assignment 8 – BDD (Cucumber + TestNG + Selenium)

## Tech stack
- Java
- Gradle
- Selenium
- Cucumber (Gherkin)
- TestNG

## Scenarios (BDD)
Feature files are located in:
`src/test/resources/features`

Implemented features:
- `login_logout.feature` (The Internet Herokuapp)
- `booking_pom.feature` (SauceDemo checkout)

## How to run
Requirements:
- Java (JDK 17+ recommended)
- Google Chrome installed

Run tests:
```bash
cd src/test/java/org/qa/alanb/cucumber/runner
./gradlew test
