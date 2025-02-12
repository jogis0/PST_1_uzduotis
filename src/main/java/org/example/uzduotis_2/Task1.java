package org.example.uzduotis_2;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task1 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/");

        driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']")).click();
        driver.findElement(By.xpath("//span[text()='Progress Bar']")).click();

        driver.findElement(By.xpath("//button[text()='Start']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='progressbar' and text()='100%']")));
        driver.findElement(By.xpath("//button[text()='Reset']")).click();

        Assert.assertEquals("0%", driver.findElement(By.xpath("//div[@role='progressbar']")).getText());
        driver.quit();
    }
}
