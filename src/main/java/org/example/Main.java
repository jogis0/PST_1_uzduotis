package org.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com");
//        driver.close();
        driver.quit();
    }
}