package org.example.uzduotis_2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/");

        driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

        var pageCount = driver.findElement(By.xpath("//span[@class='-totalPages']"));
        while(Integer.parseInt(pageCount.getText()) == 1) {
            driver.findElement(By.xpath("//button[@id='addNewRecordButton']")).click();
            driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("first name");
            driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("last name");
            driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("user@email.com");
            driver.findElement(By.xpath("//input[@id='age']")).sendKeys("21");
            driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("100000");
            driver.findElement(By.xpath("//input[@id='department']")).sendKeys("Sales");
            driver.findElement(By.xpath("//button[@id='submit']")).click();
        }
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='-btn' and text()='Next']")));
        //FIXME wait funkcija kazkodel iskart passina ir nepalaukia, del to mygtukas paspaudziamas, kai dar negali buti
//        Thread.sleep(1000);
        element.click();
    }
}
