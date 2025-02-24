package org.example.uzduotis_3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Task {
    private static final String URL = "https://demowebshop.tricentis.com/";
    private static String email;
    private static String password;

    @BeforeClass
    public static void setUpUser() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@class='button-1 register-button']")).click();

        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        email = RandomStringUtils.randomAlphabetic(5) + "@email.com";
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        password = RandomStringUtils.randomAlphabetic(6);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        driver.quit();
    }

    @Test
    public void test1() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        driver.findElement(By.xpath("//li[@class='inactive']/a[@href='/digital-downloads']")).click();

        try {
            Files.readAllLines(Path.of("src\\main\\java\\org\\example\\uzduotis_3\\data1.txt"))
                    .forEach(x -> driver.findElement(
                            By.xpath(String.format("//a[text()='%s']/ancestor::div[@class='details']//input[@value='Add to cart']", x.strip()))
                            ).click()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        var addrSelectElement = driver.findElements(By.xpath("//select[@id='billing-address-select']"));
        if (addrSelectElement.isEmpty()) {
            var countrySelectElement = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
            var countrySelect = new Select(countrySelectElement);
            countrySelect.selectByVisibleText("Lithuania");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("City");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("Example address 1");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("12345");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("012345678");
        }
        var continueWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue1 = continueWait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 new-address-next-step-button']")));
        continue1.click();

        var continueWait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue2 = continueWait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
        continue2.click();


        var continueWait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue3 = continueWait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
        continue3.click();

        var confirmWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        var confirm = confirmWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
        confirm.click();

        Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'successfully')]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void test2() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);

        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        driver.findElement(By.xpath("//li[@class='inactive']/a[@href='/digital-downloads']")).click();

        try {
            Files.readAllLines(Path.of("src\\main\\java\\org\\example\\uzduotis_3\\data2.txt"))
                    .forEach(x -> driver.findElement(
                                    By.xpath(String.format("//a[text()='%s']/ancestor::div[@class='details']//input[@value='Add to cart']", x.strip()))
                            ).click()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        var addrSelectElement = driver.findElements(By.xpath("//select[@id='billing-address-select']"));
        if (addrSelectElement.isEmpty()) {
            var countrySelectElement = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
            var countrySelect = new Select(countrySelectElement);
            countrySelect.selectByVisibleText("Lithuania");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("City");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("Example address 1");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("12345");
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("012345678");
        }
        var continueWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue1 = continueWait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 new-address-next-step-button']")));
        continue1.click();

        var continueWait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue2 = continueWait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
        continue2.click();


        var continueWait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
        var continue3 = continueWait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
        continue3.click();

        var confirmWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        var confirm = confirmWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
        confirm.click();

        Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'successfully')]")).isDisplayed());

        driver.quit();
    }
}
