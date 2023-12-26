package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KontaktPage {

    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(xpath="//span[@class='fusion-button-text' and text()='Absenden']")
    private WebElement absendenButton;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(xpath="//div[contains(@class, 'fusion-form-email-field') and contains(@class, 'error')]")
    private WebElement errorMessagesElementForEmailField;

    @FindBy(xpath="//div[contains(@class, 'fusion-form-phone-number-field') and contains(@class, 'error')]")
    private WebElement errorMessagesElementForTelefonField;

    @FindBy(xpath="//div[contains(@class, 'fusion-form-text-field') and contains(@class, 'error')]//input[@name='company']")
    private WebElement errorMessagesElementForFirmaField;

    public KontaktPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeNameinNameFiled(String nameValue) {
        nameField.sendKeys(nameValue);
    }

    public void clickAbsendenButton() {
        absendenButton.click();
    }

    public void waitTillValidationMessageForMailFieldAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                String validationMessage = (String) jsExecutor.executeScript("return arguments[0].validationMessage;", emailField);
                return validationMessage.equals("Please fill in this field.");
            }
        });
    }

    public boolean isErrorMessageVisibleForEmailField() {
        try {
            return errorMessagesElementForEmailField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorMessageVisibleForTelefonField() {
        try {
            return errorMessagesElementForTelefonField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorMessageVisibleForFirmaField() {
        try {
            return errorMessagesElementForFirmaField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
