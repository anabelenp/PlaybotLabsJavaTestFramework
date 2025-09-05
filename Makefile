# PlayBotLabs Java Automation Makefile

.PHONY: help install clean compile test test-chrome test-firefox test-headless report serve-report

help: ## Show this help message
	@echo 'Usage: make [target]'
	@echo ''
	@echo 'Targets:'
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "  %-15s %s\n", $$1, $$2}' $(MAKEFILE_LIST)

install: ## Install dependencies
	mvn clean install

clean: ## Clean build artifacts
	mvn clean

compile: ## Compile the project
	mvn compile test-compile

test: ## Run all tests
	mvn test

test-chrome: ## Run tests in Chrome
	mvn test -Dbrowser=chrome

test-firefox: ## Run tests in Firefox
	mvn test -Dbrowser=firefox

test-headless: ## Run tests in headless mode
	mvn test -Dheadless=true

test-smoke: ## Run smoke tests only
	mvn test -Dcucumber.filter.tags="@smoke"

test-regression: ## Run regression tests only
	mvn test -Dcucumber.filter.tags="@regression"

test-forms-forest: ## Run all Forms Forest tests
	mvn test -Dcucumber.filter.tags="@forms-forest"

test-basic-forms: ## Run Basic Form Elements tests
	mvn test -Dcucumber.filter.tags="@forms-forest and @basic-forms"

test-advanced-controls: ## Run Advanced Controls tests
	mvn test -Dcucumber.filter.tags="@forms-forest and @advanced-controls"

test-file-upload: ## Run File Upload tests
	mvn test -Dcucumber.filter.tags="@forms-forest and @file-upload"

test-multi-step: ## Run Multi-Step Form tests
	mvn test -Dcucumber.filter.tags="@forms-forest and @multi-step"

test-forms-forest-chrome: ## Run Forms Forest tests in Chrome
	mvn test -Dbrowser=chrome -Dcucumber.filter.tags="@forms-forest"

test-forms-forest-firefox: ## Run Forms Forest tests in Firefox
	mvn test -Dbrowser=firefox -Dcucumber.filter.tags="@forms-forest"

test-forms-forest-headless: ## Run Forms Forest tests in headless mode
	mvn test -Dheadless=true -Dcucumber.filter.tags="@forms-forest"

test-categories-all: ## Run all categorized Forms Forest tests
	mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-forms-forest-categories.xml

report: ## Generate Allure report
	mvn allure:report

serve-report: ## Serve Allure report
	mvn allure:serve

docker-test: ## Run tests in Docker
	docker run --rm -v $(PWD):/workspace -w /workspace maven:3.8.6-openjdk-11 make test-headless

# CI/CD Integration Commands
ci-test-integration: ## Test CI/CD integration locally
	./scripts/test-ci-integration.sh

ci-smoke: ## Run CI smoke tests
	mvn test -Pforms-forest-smoke -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @smoke"

ci-regression: ## Run CI regression tests
	mvn test -Pforms-forest-regression -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @regression"

ci-basic-forms: ## Run CI basic forms tests
	mvn test -Pforms-forest-categories -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @basic-forms"

ci-advanced-controls: ## Run CI advanced controls tests
	mvn test -Pforms-forest-categories -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @advanced-controls"

ci-file-upload: ## Run CI file upload tests
	mvn test -Pforms-forest-categories -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @file-upload"

ci-multi-step: ## Run CI multi-step tests
	mvn test -Pforms-forest-categories -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @multi-step"

ci-all-forms-forest: ## Run all Forms Forest tests in CI mode
	mvn test -Pforms-forest-ci -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest"

ci-cross-browser-smoke: ## Run smoke tests on both browsers
	mvn test -Pforms-forest-smoke -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @smoke"
	mvn test -Pforms-forest-smoke -Dbrowser=firefox -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @smoke"

ci-cross-browser-regression: ## Run regression tests on both browsers
	mvn test -Pforms-forest-regression -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @regression"
	mvn test -Pforms-forest-regression -Dbrowser=firefox -Dheadless=true -Dcucumber.filter.tags="@forms-forest and @regression"

ci-parallel-categories: ## Run all categories in parallel (simulated)
	make ci-basic-forms &
	make ci-advanced-controls &
	make ci-file-upload &
	make ci-multi-step &
	wait

ci-validate-config: ## Validate CI/CD configuration
	@echo "Validating CI/CD Configuration..."
	@echo "✓ Checking GitHub Actions workflow..."
	@test -f .github/workflows/ci.yml && echo "  ✓ GitHub Actions workflow found" || echo "  ✗ GitHub Actions workflow missing"
	@echo "✓ Checking Jenkinsfile..."
	@test -f Jenkinsfile && echo "  ✓ Jenkinsfile found" || echo "  ✗ Jenkinsfile missing"
	@echo "✓ Checking Maven profiles..."
	@grep -q "forms-forest-ci" pom.xml && echo "  ✓ CI profiles found" || echo "  ✗ CI profiles missing"
	@echo "✓ Checking TestNG configurations..."
	@test -f src/test/resources/testng-forms-forest-ci.xml && echo "  ✓ CI TestNG config found" || echo "  ✗ CI TestNG config missing"
	@echo "✓ Checking test script..."
	@test -x scripts/test-ci-integration.sh && echo "  ✓ CI test script found and executable" || echo "  ✗ CI test script missing or not executable"

ci-generate-reports: ## Generate comprehensive CI reports
	mvn allure:report
	mvn verify
	@echo "Reports generated:"
	@echo "  - Allure: target/allure-report/index.html"
	@echo "  - Cucumber: target/cucumber-reports/cucumber-html-reports/overview-features.html"