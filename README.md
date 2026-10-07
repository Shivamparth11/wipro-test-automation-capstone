# Hybrid Test Automation Framework — Capstone Project

A single framework covering the full Wipro training curriculum: **Selenium WebDriver, TestNG, BDD with Cucumber, and API testing with Postman + RestAssured.**

## Tech Stack
- Java 11, Maven
- Selenium WebDriver 4 + WebDriverManager (auto driver setup, no manual chromedriver download)
- TestNG (annotations, groups, dependencies, data providers, parallel execution)
- Cucumber (Gherkin feature files + step definitions, Page Object Model)
- RestAssured + Postman (API automation: GET/POST/PUT/DELETE, status codes, JSON assertions)

## Target Applications
- UI: [saucedemo.com](https://www.saucedemo.com/) (public Selenium practice site)
- API: [reqres.in](https://reqres.in/) (public REST API for testing)

## Project Structure
```
wipro-capstone/
├── pom.xml
├── testng.xml
├── src/test/java/
│   ├── pages/          → Page Object Model classes (BasePage, LoginPage, InventoryPage)
│   ├── tests/          → TestNG test class (LoginTest) with annotations, groups, data provider
│   ├── stepdefs/       → Cucumber step definitions (LoginSteps)
│   ├── runners/        → Cucumber-TestNG runner with HTML report config
│   └── api/            → RestAssured API tests (UserApiTest)
├── src/test/resources/features/
│   └── login.feature   → Gherkin BDD scenarios
└── postman/
    └── capstone-api-collection.json → Importable Postman collection
```

## How Each Curriculum Topic Maps to This Project
| Topic | Where it's demonstrated |
|---|---|
| Eclipse, WebDriver basics | `pages/BasePage.java` |
| Locators, WebElement interaction | `pages/LoginPage.java`, `pages/InventoryPage.java` |
| Waits (implicit/explicit) | `WebDriverWait` in `BasePage` |
| TestNG annotations, groups, dependency, data provider | `tests/LoginTest.java` |
| Automation framework / POM design | Entire `pages` + `tests` package structure |
| Maven lifecycle | `pom.xml`, run via `mvn test` |
| BDD / Gherkin | `login.feature` |
| Cucumber step defs, hooks | `stepdefs/LoginSteps.java` |
| Cucumber reporting (pretty/HTML) | `runners/CucumberTestRunner.java` |
| REST vs API concepts, HTTP methods, status codes | `api/UserApiTest.java` |
| Postman | `postman/capstone-api-collection.json` |
| RestAssured (GIVEN/WHEN/THEN, JSONPath, POJO) | `api/UserApiTest.java` |

## How to Run
```bash
# Clone and enter the project
git clone <your-repo-url>
cd wipro-capstone

# Run everything (UI + BDD + API) via the TestNG suite
mvn clean test

# Reports generated at:
#   target/surefire-reports/           (TestNG)
#   target/cucumber-reports/           (Cucumber pretty HTML)
```

To run the Postman collection: open Postman → Import → select `postman/capstone-api-collection.json` → Run Collection.

## Prerequisites
- JDK 11+
- Maven 3.6+
- Google Chrome installed (WebDriverManager handles the driver binary automatically)
