package com.playbotlabs.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class TestDataUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FORMS_FOREST_DATA_FILE = "forms-forest-data.json";

    public static Map<String, String> readJsonTestData(String fileName) {
        Map<String, String> testData = new HashMap<>();
        try {
            File file = new File("src/test/resources/testdata/" + fileName);
            JsonNode jsonNode = objectMapper.readTree(file);
            
            jsonNode.fields().forEachRemaining(entry -> {
                testData.put(entry.getKey(), entry.getValue().asText());
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from: " + fileName, e);
        }
        return testData;
    }

    public static String getTestData(String fileName, String key) {
        Map<String, String> testData = readJsonTestData(fileName);
        return testData.get(key);
    }

    /**
     * Read Forms Forest test data for a specific test scenario
     * @param scenarioName the name of the test scenario (e.g., "validData", "invalidEmailFormat")
     * @return Map containing all form field data for the scenario
     */
    public static Map<String, String> getFormsForestTestData(String scenarioName) {
        Map<String, String> testData = new HashMap<>();
        try {
            File file = new File("src/test/resources/testdata/" + FORMS_FOREST_DATA_FILE);
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode scenarioNode = rootNode.get(scenarioName);
            
            if (scenarioNode == null) {
                throw new RuntimeException("Test scenario '" + scenarioName + "' not found in " + FORMS_FOREST_DATA_FILE);
            }
            
            scenarioNode.fields().forEachRemaining(entry -> {
                testData.put(entry.getKey(), entry.getValue().asText());
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Forms Forest test data for scenario: " + scenarioName, e);
        }
        return testData;
    }

    /**
     * Get a specific field value from Forms Forest test data
     * @param scenarioName the name of the test scenario
     * @param fieldName the name of the form field
     * @return the field value as string
     */
    public static String getFormsForestFieldData(String scenarioName, String fieldName) {
        Map<String, String> testData = getFormsForestTestData(scenarioName);
        return testData.get(fieldName);
    }

    /**
     * Get all available Forms Forest test scenario names
     * @return Set of scenario names available in the test data file
     */
    public static Set<String> getFormsForestScenarios() {
        Set<String> scenarios = new HashSet<>();
        try {
            File file = new File("src/test/resources/testdata/" + FORMS_FOREST_DATA_FILE);
            JsonNode rootNode = objectMapper.readTree(file);
            
            rootNode.fieldNames().forEachRemaining(scenarios::add);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Forms Forest scenarios from: " + FORMS_FOREST_DATA_FILE, e);
        }
        return scenarios;
    }

    /**
     * Get Forms Forest test data for valid scenarios only
     * @return Map containing scenario names and their data for valid test cases
     */
    public static Map<String, Map<String, String>> getValidFormsForestTestData() {
        Map<String, Map<String, String>> validData = new HashMap<>();
        Set<String> scenarios = getFormsForestScenarios();
        
        for (String scenario : scenarios) {
            if (scenario.toLowerCase().contains("valid") && !scenario.toLowerCase().contains("invalid")) {
                validData.put(scenario, getFormsForestTestData(scenario));
            }
        }
        return validData;
    }

    /**
     * Get Forms Forest test data for invalid scenarios only
     * @return Map containing scenario names and their data for invalid test cases
     */
    public static Map<String, Map<String, String>> getInvalidFormsForestTestData() {
        Map<String, Map<String, String>> invalidData = new HashMap<>();
        Set<String> scenarios = getFormsForestScenarios();
        
        for (String scenario : scenarios) {
            if (scenario.toLowerCase().contains("invalid") || 
                scenario.toLowerCase().contains("empty") ||
                scenario.toLowerCase().contains("special") ||
                scenario.toLowerCase().contains("sql") ||
                scenario.toLowerCase().contains("xss") ||
                scenario.toLowerCase().contains("long") ||
                scenario.toLowerCase().contains("terms") && scenario.toLowerCase().contains("not")) {
                invalidData.put(scenario, getFormsForestTestData(scenario));
            }
        }
        return invalidData;
    }

    /**
     * Get Forms Forest test data for edge case scenarios
     * @return Map containing scenario names and their data for edge case test scenarios
     */
    public static Map<String, Map<String, String>> getEdgeCaseFormsForestTestData() {
        Map<String, Map<String, String>> edgeCaseData = new HashMap<>();
        Set<String> scenarios = getFormsForestScenarios();
        
        for (String scenario : scenarios) {
            if (scenario.toLowerCase().contains("special") ||
                scenario.toLowerCase().contains("unicode") ||
                scenario.toLowerCase().contains("long") ||
                scenario.toLowerCase().contains("numeric") ||
                scenario.toLowerCase().contains("whitespace") ||
                scenario.toLowerCase().contains("international") ||
                scenario.toLowerCase().contains("alternate")) {
                edgeCaseData.put(scenario, getFormsForestTestData(scenario));
            }
        }
        return edgeCaseData;
    }

    /**
     * Check if a Forms Forest test scenario represents valid data
     * @param scenarioName the name of the test scenario
     * @return true if the scenario represents valid test data
     */
    public static boolean isValidFormsForestScenario(String scenarioName) {
        return scenarioName.toLowerCase().contains("valid") && 
               !scenarioName.toLowerCase().contains("invalid");
    }

    /**
     * Check if a Forms Forest test scenario represents invalid data
     * @param scenarioName the name of the test scenario
     * @return true if the scenario represents invalid test data
     */
    public static boolean isInvalidFormsForestScenario(String scenarioName) {
        return scenarioName.toLowerCase().contains("invalid") ||
               scenarioName.toLowerCase().contains("empty") ||
               scenarioName.toLowerCase().contains("terms") && scenarioName.toLowerCase().contains("not");
    }

    /**
     * Check if a Forms Forest test scenario represents edge case data
     * @param scenarioName the name of the test scenario
     * @return true if the scenario represents edge case test data
     */
    public static boolean isEdgeCaseFormsForestScenario(String scenarioName) {
        return scenarioName.toLowerCase().contains("special") ||
               scenarioName.toLowerCase().contains("unicode") ||
               scenarioName.toLowerCase().contains("long") ||
               scenarioName.toLowerCase().contains("sql") ||
               scenarioName.toLowerCase().contains("xss") ||
               scenarioName.toLowerCase().contains("numeric") ||
               scenarioName.toLowerCase().contains("whitespace");
    }
}