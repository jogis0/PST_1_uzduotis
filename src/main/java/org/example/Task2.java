package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task2 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/gift-cards']")).click();

        var giftCards = driver.findElements(By.xpath("//div[@class='product-item']"));
        var higherThan99GiftCard = giftCards.get(0);

        float price;
        for (var giftCard : giftCards) {
            price = Float.parseFloat( //Kazkodel visada cia yra pirmo elemento kaina
                giftCard.findElement(
                        By.xpath(".//span[contains(@class, 'actual-price')]"))
                        .getText()
            );

            if (price > 99)
                higherThan99GiftCard = giftCard;
        }

        higherThan99GiftCard.findElement(By.xpath(".//div[@class='picture']/a")).click();

        driver.findElement(By.xpath("//input[@class='recipient-name']")).sendKeys("test name");
        driver.findElement(By.xpath("//input[@class='sender-name']")).sendKeys("test name");
        var qtyInput = driver.findElement(By.xpath("//input[@class='qty-input']"));
        qtyInput.clear();
        qtyInput.sendKeys("5000");

        driver.findElement(By.xpath("//input[@id='add-to-cart-button-4']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));

        driver.findElement(By.xpath("//input[@id='add-to-wishlist-button-4']")).click();

        driver.findElement(By.xpath("//div[contains(@class, 'block-category-navigation')]/div/ul/li/a[@href='/jewelry']")).click();
        driver.findElement(By.xpath("//a[@href='/create-it-yourself-jewelry']")).click();

        var select = new Select(driver.findElement(By.xpath("//select[@id='product_attribute_71_9_15']")));
        select.selectByVisibleText("Silver (1 mm)");

        driver.findElement(By.xpath("//input[@id='product_attribute_71_10_16']")).sendKeys("80");
        driver.findElement(By.xpath("//input[@id='product_attribute_71_11_17_50']")).click();

        var jlrQtyInput = driver.findElement(By.xpath("//input[@id='addtocart_71_EnteredQuantity']"));
        jlrQtyInput.clear();
        jlrQtyInput.sendKeys("26");

        driver.findElement(By.xpath("//input[@id='add-to-cart-button-71']")).click();

        WebDriverWait waitJlr = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitJlr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));

        driver.findElement(By.xpath("//input[@id='add-to-wishlist-button-71']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));

        driver.findElement(By.xpath("//a[@href='/wishlist']")).click();

        driver.findElements(By.xpath("//input[@name='addtocart']")).forEach(WebElement::click);
        driver.findElement(By.xpath("//input[@name='addtocartbutton']")).click();

        var totalQty = driver.findElement(By.xpath("//span[@class='cart-qty']"));
        Assert.assertEquals(totalQty.getText(), "(10026)");

        driver.quit();
    }
}
