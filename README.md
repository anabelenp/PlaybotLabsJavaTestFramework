# PlayBotLabs Java Automation Framework

This repository contains a **Java Automation Framework** designed to automate a **Test Playground** website that I created. The project combines **prompt engineering** with traditional coding to build a comprehensive test automation solution. Both the Automation Test website and this Automation framework are in progress.

## Features

- **Selenium WebDriver 4.x** - Latest WebDriver implementation
- **TestNG** - Powerful testing framework with parallel execution
- **Cucumber** - BDD framework for readable test scenarios
- **Page Object Model** - Maintainable and scalable test architecture
- **WebDriverManager** - Automatic driver management
- **Allure Reports** - Beautiful test reporting
- **Cross-browser support** - Chrome, Firefox, Safari, Edge

## Project Structure

```
src/
├── main/java/com/playbotlabs/
│   ├── pages/          # Page Object classes
│   ├── utils/          # Utility classes
│   └── config/         # Configuration classes
└── test/
    ├── java/com/playbotlabs/
    │   ├── steps/      # Cucumber step definitions
    │   └── runners/    # TestNG test runners
    └── resources/
        ├── features/   # Cucumber feature files
        ├── testng.xml  # TestNG suite configuration
        └── config.properties
```

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Git

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd PlayBotLabsJavaAutomation
```

2. Install dependencies:
```bash
mvn clean install
```

3. Run tests:
```bash
mvn test
```

## Running Tests

### All Tests
```bash
mvn clean test
```

### Specific Test Suite
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

### With Browser Selection
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Generate Allure Report
```bash
mvn allure:report
mvn allure:serve
```

## Configuration

Update `src/test/resources/config.properties` for:
- Base URLs
- Browser preferences
- Timeouts
- Environment settings

## Writing Tests

### Page Objects
Create page classes in `src/main/java/com/playbotlabs/pages/`

### Feature Files
Write BDD scenarios in `src/test/resources/features/`

### Step Definitions
Implement steps in `src/test/java/com/playbotlabs/steps/`

## Contributing
TBD

