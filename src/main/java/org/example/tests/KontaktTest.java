package org.example.tests;

import org.example.pages.KontaktPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class KontaktTest {

    WebDriver driver;
    KontaktPage kontaktPage;
    Map<String, String> kontaktData;

    @Before
    public void setUp() throws IOException {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        System.setProperty("webdriver.chrome.bin", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.selenium-consulting.ch/kontakt/");

        kontaktPage = new KontaktPage(driver);

        kontaktData = readKontaktData("/Users/michalinazablocka/IdeaProjects/qtestiq/src/main/resources/KontaktData.csv");
    }

    private Map<String, String> readKontaktData(String filePath) throws IOException {
        Map<String, String> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    data.put(key, value);
                }
            }
        }
        return data;
    }

    @Test
    public void fillTheFormOnlyWithName() {
        String nameValue = kontaktData.get("name");
        kontaktPage.typeNameinNameFiled(nameValue);
        kontaktPage.clickAbsendenButton();
        kontaktPage.waitTillValidationMessageForMailFieldAppears();
        assertTrue("Error message for email field is not visible", kontaktPage.isErrorMessageVisibleForEmailField());
        assertTrue("Error message for telefon field is not visible", kontaktPage.isErrorMessageVisibleForTelefonField());
        assertTrue("Error message for firma field is not visible", kontaktPage.isErrorMessageVisibleForFirmaField());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
