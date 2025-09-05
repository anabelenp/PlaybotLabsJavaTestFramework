package com.playbotlabs.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigManagerTest {

    @Test
    public void testBaseUrlConfiguration() {
        String baseUrl = ConfigManager.getBaseUrl();
        Assert.assertEquals(baseUrl, "http://127.0.0.1:5006/", "Base URL should be configured correctly");
    }

    @Test
    public void testFormsForestConfiguration() {
        String formsForestPath = ConfigManager.getFormsForestPath();
        Assert.assertEquals(formsForestPath, "/forms-forest", "Forms Forest path should be configured correctly");
        
        String formsForestUrl = ConfigManager.getFormsForestUrl();
        Assert.assertEquals(formsForestUrl, "http://127.0.0.1:5006/forms-forest", "Forms Forest URL should be constructed correctly");
        
        int formTimeout = ConfigManager.getFormsForestFormTimeout();
        Assert.assertEquals(formTimeout, 15, "Forms Forest form timeout should be configured correctly");
        
        int validationTimeout = ConfigManager.getFormsForestValidationTimeout();
        Assert.assertEquals(validationTimeout, 10, "Forms Forest validation timeout should be configured correctly");
    }

    @Test
    public void testBrowserConfiguration() {
        String browser = ConfigManager.getBrowser();
        Assert.assertEquals(browser, "chrome", "Default browser should be chrome");
    }

    @Test
    public void testTimeoutConfiguration() {
        int implicitWait = ConfigManager.getImplicitWait();
        Assert.assertEquals(implicitWait, 10, "Implicit wait should be configured correctly");
        
        int explicitWait = ConfigManager.getExplicitWait();
        Assert.assertEquals(explicitWait, 20, "Explicit wait should be configured correctly");
    }

    @Test
    public void testHeadlessConfiguration() {
        boolean headless = ConfigManager.isHeadless();
        Assert.assertTrue(headless, "Headless mode should be enabled by default");
    }
}