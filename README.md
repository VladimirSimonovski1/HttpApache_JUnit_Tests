## Info & Instructions

In this **README.md** you will find all the necessary info about this framework like:
- What is the main programming language used?
- Which frameworks are used?
- What test design pattern is followed?
- Which plugins & extensions are used and how they are used?
- How to run the tests and what should you expect at the end of  test execution?

### Code & Frameworks

- Language used: Java
- HTTP library: HTTP Apache Client
- Test Framework: JUnit
- Request/Response model: Project Lombok
- Assertion Framework: Hamcrest Assertions

### Plugins

- **Log**, powerful logger from project Lombok
- **allure-reporter**, an HTML report for JUnit tests

### How to run the tests:

1. Open Maven Goal and type: **mvn clean install test** (it will run the tests and create an .xml surefire report in the target folder
2. Open Terminal, navigate to the root project and enter: **allure serve target/surefire-reports** (this will convert the .xml into an .html format and will automatically open the report in a browser)
