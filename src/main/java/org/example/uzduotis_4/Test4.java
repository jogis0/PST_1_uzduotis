package org.example.uzduotis_4;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test4 {
    private static final String URL = "https://demowebshop.tricentis.com/";
    private static WebDriver driver;
    private static String firstName;

    @BeforeClass
    public static void setUpUser() {
        var options = new ChromeOptions();
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@class='button-1 register-button']")).click();

        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        firstName = RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(RandomStringUtils.randomAlphabetic(5) + "@email.com");
        var password = RandomStringUtils.randomAlphabetic(6);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }

    @Test
    public void RatingOwnReview_Returns_Message() {
        driver.findElement(By.xpath("//a[@href='/141-inch-laptop']")).click();
        driver.findElement(By.xpath("//a[@href='/productreviews/31']")).click();

        driver.findElement(By.xpath("//input[@id='AddProductReview_Title']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
        driver.findElement(By.xpath("//textarea[@id='AddProductReview_ReviewText']")).sendKeys(RandomStringUtils.randomAlphabetic(20));
        driver.findElement(By.xpath("//input[@name='add-review']")).click();
        driver.findElement(By.xpath("//div[@class='product-review-item'][.//span[@class='user'][contains(., '" + firstName +"')]][last()]//span[@class='vote' and text()='Yes']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-review-item'][.//span[@class='user'][contains(., '" + firstName +"')]][last()]//span[@class='vote' and text()='Yes']/ancestor::div[@class='product-review-helpfulness']/span[@class='result']")));
        var result = driver.findElement(By.xpath("//div[@class='product-review-item'][.//span[@class='user'][contains(., '" + firstName +"')]][last()]//span[@class='vote' and text()='Yes']/ancestor::div[@class='product-review-helpfulness']/span[@class='result']")).getText();
        Assert.assertEquals("You cannot vote for your own review", result);
    }
}
