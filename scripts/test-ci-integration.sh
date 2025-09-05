#!/bin/bash

# Forms Forest CI/CD Integration Test Script
# This script tests the CI/CD integration locally before deploying

set -e

echo "🚀 Testing Forms Forest CI/CD Integration"
echo "========================================"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default values
BROWSER=${1:-chrome}
TEST_SUITE=${2:-forms-forest-smoke}
HEADLESS=${3:-true}
PARALLEL_THREADS=${4:-2}

echo -e "${BLUE}Configuration:${NC}"
echo "  Browser: $BROWSER"
echo "  Test Suite: $TEST_SUITE"
echo "  Headless: $HEADLESS"
echo "  Parallel Threads: $PARALLEL_THREADS"
echo ""

# Function to run tests with specific configuration
run_test_suite() {
    local suite=$1
    local browser=$2
    local headless=$3
    local threads=$4
    
    echo -e "${YELLOW}Running $suite tests with $browser browser...${NC}"
    
    case $suite in
        "forms-forest-smoke")
            mvn test -Pforms-forest-smoke -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @smoke"
            ;;
        "forms-forest-regression")
            mvn test -Pforms-forest-regression -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @regression"
            ;;
        "forms-forest-basic")
            mvn test -Pforms-forest-categories -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @basic-forms"
            ;;
        "forms-forest-advanced")
            mvn test -Pforms-forest-categories -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @advanced-controls"
            ;;
        "forms-forest-file-upload")
            mvn test -Pforms-forest-categories -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @file-upload"
            ;;
        "forms-forest-multi-step")
            mvn test -Pforms-forest-categories -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest and @multi-step"
            ;;
        "forms-forest-all")
            mvn test -Pforms-forest-ci -Dbrowser=$browser -Dheadless=$headless -Dparallel.threads=$threads -Dcucumber.filter.tags="@forms-forest"
            ;;
        *)
            echo -e "${RED}Unknown test suite: $suite${NC}"
            return 1
            ;;
    esac
}

# Function to generate reports
generate_reports() {
    echo -e "${YELLOW}Generating Allure reports...${NC}"
    
    if [ -d "target/allure-results" ] && [ "$(ls -A target/allure-results)" ]; then
        mvn allure:report
        echo -e "${GREEN}✅ Allure report generated successfully${NC}"
        echo "📊 Report available at: target/allure-report/index.html"
    else
        echo -e "${YELLOW}⚠️  No test results found for Allure report${NC}"
    fi
    
    # Generate Cucumber reports if available
    if [ -d "target/cucumber-reports" ] && [ "$(ls -A target/cucumber-reports)" ]; then
        mvn verify
        echo -e "${GREEN}✅ Cucumber report generated successfully${NC}"
        echo "📊 Cucumber report available at: target/cucumber-reports/cucumber-html-reports/overview-features.html"
    else
        echo -e "${YELLOW}⚠️  No Cucumber results found${NC}"
    fi
}

# Function to test parallel execution
test_parallel_execution() {
    echo -e "${BLUE}Testing parallel execution capabilities...${NC}"
    
    local start_time=$(date +%s)
    
    # Run tests with different thread counts to verify parallel execution
    echo -e "${YELLOW}Running with 1 thread...${NC}"
    run_test_suite "forms-forest-smoke" "$BROWSER" "$HEADLESS" "1"
    local single_thread_time=$(date +%s)
    
    echo -e "${YELLOW}Running with $PARALLEL_THREADS threads...${NC}"
    run_test_suite "forms-forest-smoke" "$BROWSER" "$HEADLESS" "$PARALLEL_THREADS"
    local multi_thread_time=$(date +%s)
    
    local single_duration=$((single_thread_time - start_time))
    local multi_duration=$((multi_thread_time - single_thread_time))
    
    echo -e "${BLUE}Parallel Execution Results:${NC}"
    echo "  Single thread duration: ${single_duration}s"
    echo "  Multi-thread duration: ${multi_duration}s"
    
    if [ $multi_duration -lt $single_duration ]; then
        echo -e "${GREEN}✅ Parallel execution is working correctly${NC}"
    else
        echo -e "${YELLOW}⚠️  Parallel execution may not be optimized${NC}"
    fi
}

# Function to validate CI/CD configuration
validate_ci_config() {
    echo -e "${BLUE}Validating CI/CD configuration...${NC}"
    
    # Check GitHub Actions workflow
    if [ -f ".github/workflows/ci.yml" ]; then
        echo -e "${GREEN}✅ GitHub Actions workflow found${NC}"
        
        # Validate workflow syntax (basic check)
        if grep -q "forms-forest" ".github/workflows/ci.yml"; then
            echo -e "${GREEN}✅ Forms Forest integration found in GitHub Actions${NC}"
        else
            echo -e "${RED}❌ Forms Forest integration missing in GitHub Actions${NC}"
        fi
    else
        echo -e "${RED}❌ GitHub Actions workflow not found${NC}"
    fi
    
    # Check Jenkinsfile
    if [ -f "Jenkinsfile" ]; then
        echo -e "${GREEN}✅ Jenkinsfile found${NC}"
        
        # Validate Jenkinsfile content (basic check)
        if grep -q "forms-forest" "Jenkinsfile"; then
            echo -e "${GREEN}✅ Forms Forest integration found in Jenkinsfile${NC}"
        else
            echo -e "${RED}❌ Forms Forest integration missing in Jenkinsfile${NC}"
        fi
    else
        echo -e "${RED}❌ Jenkinsfile not found${NC}"
    fi
    
    # Check Maven profiles
    if grep -q "forms-forest-ci" "pom.xml"; then
        echo -e "${GREEN}✅ Forms Forest CI profiles found in pom.xml${NC}"
    else
        echo -e "${RED}❌ Forms Forest CI profiles missing in pom.xml${NC}"
    fi
    
    # Check TestNG configurations
    local testng_configs=(
        "src/test/resources/testng-forms-forest-ci.xml"
        "src/test/resources/testng-forms-forest-smoke.xml"
        "src/test/resources/testng-forms-forest-regression.xml"
    )
    
    for config in "${testng_configs[@]}"; do
        if [ -f "$config" ]; then
            echo -e "${GREEN}✅ $config found${NC}"
        else
            echo -e "${YELLOW}⚠️  $config not found${NC}"
        fi
    done
}

# Main execution
main() {
    echo -e "${BLUE}Step 1: Validating CI/CD Configuration${NC}"
    validate_ci_config
    echo ""
    
    echo -e "${BLUE}Step 2: Cleaning previous results${NC}"
    mvn clean
    echo ""
    
    echo -e "${BLUE}Step 3: Compiling project${NC}"
    mvn compile
    echo ""
    
    echo -e "${BLUE}Step 4: Running test suite${NC}"
    if run_test_suite "$TEST_SUITE" "$BROWSER" "$HEADLESS" "$PARALLEL_THREADS"; then
        echo -e "${GREEN}✅ Test execution completed successfully${NC}"
    else
        echo -e "${RED}❌ Test execution failed${NC}"
        exit 1
    fi
    echo ""
    
    echo -e "${BLUE}Step 5: Generating reports${NC}"
    generate_reports
    echo ""
    
    if [ "$TEST_SUITE" = "forms-forest-smoke" ]; then
        echo -e "${BLUE}Step 6: Testing parallel execution${NC}"
        test_parallel_execution
        echo ""
    fi
    
    echo -e "${GREEN}🎉 CI/CD Integration test completed successfully!${NC}"
    echo ""
    echo -e "${BLUE}Next steps:${NC}"
    echo "1. Commit and push changes to trigger GitHub Actions"
    echo "2. Configure Jenkins with the provided Jenkinsfile"
    echo "3. Set up Allure reporting in your CI/CD environment"
    echo "4. Configure notifications for test results"
}

# Help function
show_help() {
    echo "Usage: $0 [BROWSER] [TEST_SUITE] [HEADLESS] [PARALLEL_THREADS]"
    echo ""
    echo "Parameters:"
    echo "  BROWSER           Browser to use (chrome, firefox) - default: chrome"
    echo "  TEST_SUITE        Test suite to run - default: forms-forest-smoke"
    echo "                    Options: forms-forest-smoke, forms-forest-regression,"
    echo "                            forms-forest-basic, forms-forest-advanced,"
    echo "                            forms-forest-file-upload, forms-forest-multi-step,"
    echo "                            forms-forest-all"
    echo "  HEADLESS          Run in headless mode (true, false) - default: true"
    echo "  PARALLEL_THREADS  Number of parallel threads - default: 2"
    echo ""
    echo "Examples:"
    echo "  $0                                    # Run with defaults"
    echo "  $0 firefox forms-forest-regression    # Run regression tests on Firefox"
    echo "  $0 chrome forms-forest-all false 4    # Run all tests on Chrome with GUI, 4 threads"
}

# Check for help flag
if [ "$1" = "-h" ] || [ "$1" = "--help" ]; then
    show_help
    exit 0
fi

# Run main function
main