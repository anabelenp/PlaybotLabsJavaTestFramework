package com.playbotlabs.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Configuration file not found at " + CONFIG_FILE_PATH);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getBrowser() {
        return System.getProperty("browser", getProperty("browser", "chrome"));
    }

    public static String getBaseUrl() {
        return getProperty("base.url", "http://127.0.0.1:5006/");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    // Forms Forest specific configuration methods
    public static String getFormsForestPath() {
        return getProperty("forms.forest.path", "/forms-forest");
    }

    public static String getFormsForestUrl() {
        String baseUrl = getBaseUrl();
        String path = getFormsForestPath();
        // Remove trailing slash from base URL if present, and ensure path starts with slash
        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            return baseUrl + path.substring(1);
        } else if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
            return baseUrl + "/" + path;
        }
        return baseUrl + path;
    }

    public static int getFormsForestFormTimeout() {
        return Integer.parseInt(getProperty("forms.forest.form.timeout", "15"));
    }

    public static int getFormsForestValidationTimeout() {
        return Integer.parseInt(getProperty("forms.forest.validation.timeout", "10"));
    }
}