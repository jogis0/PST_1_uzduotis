package org.example.uzduotis_1;

import org.openqa.selenium.chrome.ChromeDriver;

public class Task1 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com");
//        driver.close();
        driver.quit();
    }
}