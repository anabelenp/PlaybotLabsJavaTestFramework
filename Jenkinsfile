pipeline {
    agent any
    
    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: [
                'forms-forest-smoke',
                'forms-forest-regression', 
                'forms-forest-basic',
                'forms-forest-advanced',
                'forms-forest-file-upload',
                'forms-forest-multi-step',
                'forms-forest-all',
                'all-tests'
            ],
            description: 'Select test suite to run'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'both'],
            description: 'Browser to run tests on'
        )
        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run tests in headless mode'
        )
        choice(
            name: 'PARALLEL_THREADS',
            choices: ['1', '2', '3', '4'],
            description: 'Number of parallel threads for test execution'
        )
    }
    
    environment {
        MAVEN_OPTS = '-Xmx1024m'
        ALLURE_RESULTS_DIR = 'target/allure-results'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                script {
                    env.BUILD_TIMESTAMP = sh(
                        script: 'date +"%Y%m%d_%H%M%S"',
                        returnStdout: true
                    ).trim()
                }
            }
        }
        
        stage('Setup Environment') {
            steps {
                script {
                    // Determine browsers to test
                    if (params.BROWSER == 'both') {
                        env.BROWSERS = 'chrome,firefox'
                    } else {
                        env.BROWSERS = params.BROWSER
                    }
                    
                    // Determine test tags based on suite selection
                    switch(params.TEST_SUITE) {
                        case 'forms-forest-smoke':
                            env.TEST_TAGS = '@forms-forest and @smoke'
                            break
                        case 'forms-forest-regression':
                            env.TEST_TAGS = '@forms-forest and @regression'
                            break
                        case 'forms-forest-basic':
                            env.TEST_TAGS = '@forms-forest and @basic-forms'
                            break
                        case 'forms-forest-advanced':
                            env.TEST_TAGS = '@forms-forest and @advanced-controls'
                            break
                        case 'forms-forest-file-upload':
                            env.TEST_TAGS = '@forms-forest and @file-upload'
                            break
                        case 'forms-forest-multi-step':
                            env.TEST_TAGS = '@forms-forest and @multi-step'
                            break
                        case 'forms-forest-all':
                            env.TEST_TAGS = '@forms-forest'
                            break
                        case 'all-tests':
                            env.TEST_TAGS = ''
                            break
                        default:
                            env.TEST_TAGS = '@forms-forest and @smoke'
                    }
                }
                
                echo "Test Suite: ${params.TEST_SUITE}"
                echo "Browsers: ${env.BROWSERS}"
                echo "Test Tags: ${env.TEST_TAGS}"
                echo "Headless Mode: ${params.HEADLESS}"
                echo "Parallel Threads: ${params.PARALLEL_THREADS}"
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Forms Forest Tests') {
            parallel {
                stage('Chrome Tests') {
                    when {
                        expression { env.BROWSERS.contains('chrome') }
                    }
                    steps {
                        script {
                            def testCommand = "mvn test -Dbrowser=chrome -Dheadless=${params.HEADLESS} -Dparallel.threads=${params.PARALLEL_THREADS}"
                            if (env.TEST_TAGS) {
                                testCommand += " -Dcucumber.filter.tags=\"${env.TEST_TAGS}\""
                            }
                            
                            sh testCommand
                        }
                    }
                    post {
                        always {
                            // Archive Chrome test results
                            archiveArtifacts artifacts: 'target/cucumber-reports/**/*', allowEmptyArchive: true
                            archiveArtifacts artifacts: 'target/allure-results/**/*', allowEmptyArchive: true
                            
                            // Copy results for Chrome-specific reporting
                            sh '''
                                mkdir -p target/chrome-results
                                cp -r target/allure-results/* target/chrome-results/ 2>/dev/null || true
                                cp -r target/cucumber-reports/* target/chrome-results/ 2>/dev/null || true
                            '''
                        }
                    }
                }
                
                stage('Firefox Tests') {
                    when {
                        expression { env.BROWSERS.contains('firefox') }
                    }
                    steps {
                        script {
                            def testCommand = "mvn test -Dbrowser=firefox -Dheadless=${params.HEADLESS} -Dparallel.threads=${params.PARALLEL_THREADS}"
                            if (env.TEST_TAGS) {
                                testCommand += " -Dcucumber.filter.tags=\"${env.TEST_TAGS}\""
                            }
                            
                            sh testCommand
                        }
                    }
                    post {
                        always {
                            // Archive Firefox test results
                            archiveArtifacts artifacts: 'target/cucumber-reports/**/*', allowEmptyArchive: true
                            archiveArtifacts artifacts: 'target/allure-results/**/*', allowEmptyArchive: true
                            
                            // Copy results for Firefox-specific reporting
                            sh '''
                                mkdir -p target/firefox-results
                                cp -r target/allure-results/* target/firefox-results/ 2>/dev/null || true
                                cp -r target/cucumber-reports/* target/firefox-results/ 2>/dev/null || true
                            '''
                        }
                    }
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                script {
                    // Generate Allure report
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                    
                    // Generate consolidated test report
                    sh '''
                        mkdir -p target/consolidated-reports
                        
                        # Create summary report
                        cat > target/consolidated-reports/test-summary.html << 'EOF'
<!DOCTYPE html>
<html>
<head>
    <title>Forms Forest Test Execution Summary</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .summary { background: #f8f9fa; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
        .test-info { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .info-card { background: white; padding: 15px; border-radius: 5px; border-left: 4px solid #007bff; }
        .browsers { margin-top: 20px; }
        .browser-card { display: inline-block; margin: 10px; padding: 15px; background: #e9ecef; border-radius: 5px; }
    </style>
</head>
<body>
    <h1>Forms Forest Test Execution Summary</h1>
    <div class="summary">
        <h2>Execution Details</h2>
        <div class="test-info">
            <div class="info-card">
                <h3>Test Suite</h3>
                <p>''' + params.TEST_SUITE + '''</p>
            </div>
            <div class="info-card">
                <h3>Build Number</h3>
                <p>#''' + env.BUILD_NUMBER + '''</p>
            </div>
            <div class="info-card">
                <h3>Execution Time</h3>
                <p>''' + env.BUILD_TIMESTAMP + '''</p>
            </div>
            <div class="info-card">
                <h3>Parallel Threads</h3>
                <p>''' + params.PARALLEL_THREADS + '''</p>
            </div>
        </div>
        <div class="browsers">
            <h3>Browsers Tested</h3>
EOF
                        
                        # Add browser information
                        IFS=',' read -ra BROWSER_ARRAY <<< "${BROWSERS}"
                        for browser in "${BROWSER_ARRAY[@]}"; do
                            echo "<div class=\"browser-card\"><strong>$browser</strong> (Headless: ${HEADLESS})</div>" >> target/consolidated-reports/test-summary.html
                        done
                        
                        cat >> target/consolidated-reports/test-summary.html << 'EOF'
        </div>
    </div>
    <div>
        <h2>Test Reports</h2>
        <p><a href="../allure-report/index.html">View Detailed Allure Report</a></p>
    </div>
</body>
</html>
EOF
                    '''
                }
            }
        }
    }
    
    post {
        always {
            // Publish test results
            publishTestResults testResultsPattern: 'target/cucumber-reports/**/*.xml'
            
            // Archive all artifacts
            archiveArtifacts artifacts: 'target/**/*', allowEmptyArchive: true
            
            // Clean workspace if successful
            cleanWs(cleanWhenSuccess: true, cleanWhenFailure: false)
        }
        
        success {
            echo "✅ Forms Forest tests completed successfully!"
            
            // Send success notification (configure as needed)
            script {
                def message = """
Forms Forest Test Execution Completed Successfully!

Test Suite: ${params.TEST_SUITE}
Browsers: ${env.BROWSERS}
Build: #${env.BUILD_NUMBER}
Duration: ${currentBuild.durationString}

View Reports: ${env.BUILD_URL}allure/
                """.trim()
                
                echo message
            }
        }
        
        failure {
            echo "❌ Forms Forest tests failed!"
            
            // Send failure notification (configure as needed)
            script {
                def message = """
Forms Forest Test Execution Failed!

Test Suite: ${params.TEST_SUITE}
Browsers: ${env.BROWSERS}
Build: #${env.BUILD_NUMBER}
Duration: ${currentBuild.durationString}

View Logs: ${env.BUILD_URL}console
View Reports: ${env.BUILD_URL}allure/
                """.trim()
                
                echo message
            }
        }
        
        unstable {
            echo "⚠️ Forms Forest tests completed with some failures!"
            
            script {
                def message = """
Forms Forest Test Execution Completed with Issues!

Test Suite: ${params.TEST_SUITE}
Browsers: ${env.BROWSERS}
Build: #${env.BUILD_NUMBER}
Duration: ${currentBuild.durationString}

Some tests may have failed. Please check the reports.
View Reports: ${env.BUILD_URL}allure/
                """.trim()
                
                echo message
            }
        }
    }
}