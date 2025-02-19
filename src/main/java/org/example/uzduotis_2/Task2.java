package org.example.uzduotis_2;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task2 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://web.archive.org/web/20240112153757/https://demoqa.com/");

        driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

        //FIXME Naudoti custom wait until logika vietoje while ciklo
        //FIXME randomizuoti ivesties duomenis
        var tableWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        tableWait.until(d -> {
            d.findElement(By.xpath("//button[@id='addNewRecordButton']")).click();
            d.findElement(By.xpath("//input[@id='firstName']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
            d.findElement(By.xpath("//input[@id='lastName']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
            d.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(RandomStringUtils.randomAlphabetic(4) + "@email.com");
            d.findElement(By.xpath("//input[@id='age']")).sendKeys(RandomStringUtils.randomNumeric(2));
            d.findElement(By.xpath("//input[@id='salary']")).sendKeys(RandomStringUtils.randomNumeric(5));
            d.findElement(By.xpath("//input[@id='department']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
            d.findElement(By.xpath("//button[@id='submit']")).click();

            var pageCount = d.findElement(By.xpath("//span[@class='-totalPages']"));
            return Integer.parseInt(pageCount.getText()) == 2;
        });

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var next = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='-btn' and text()='Next']")));
        next.click();

        driver.findElement(By.xpath("//span[@id='delete-record-11']")).click();
        var pageNum = driver.findElement(By.xpath("//input[@aria-label='jump to page']")).getDomAttribute("value");
        Assert.assertEquals("1", pageNum);
        driver.quit();
    }
}
