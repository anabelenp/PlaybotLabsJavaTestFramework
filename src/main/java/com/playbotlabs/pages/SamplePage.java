package com.playbotlabs.pages;

import com.playbotlabs.config.ConfigManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePage extends BasePage {

    @FindBy(id = "sample-element")
    private WebElement sampleElement;

    public void navigateToPage() {
        navigateTo(ConfigManager.getBaseUrl());
    }

    public void clickSampleElement() {
        click(sampleElement);
    }

    public String getSampleText() {
        return getText(sampleElement);
    }

    public boolean isSampleElementDisplayed() {
        return isElementDisplayed(sampleElement);
    }
}