package org.example.tests;

import org.example.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class HomeTest {
    WebDriver driver;
    HomePage homePage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        System.setProperty("webdriver.chrome.bin", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.selenium-consulting.ch/");

        homePage = new HomePage(driver);
    }

    @Test
    public void openKontaktForm() throws InterruptedException {
        homePage.clickOnKontaktButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://www.selenium-consulting.ch/kontakt/"));
        String expectedUrl = "https://www.selenium-consulting.ch/kontakt/";
        assertEquals(expectedUrl, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
