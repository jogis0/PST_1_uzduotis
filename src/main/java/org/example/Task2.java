package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/gift-cards']")).click();

        var giftCards = driver.findElements(By.xpath("//div[@class='product-item']"));
        var higherThan99GiftCard = giftCards.getFirst();

        for (var giftCard : giftCards) {
            float price = Float.parseFloat( //Kazkodel visada cia yra pirmo elemento kaina
                giftCard.findElement(
                        By.xpath("//span[contains(@class, 'actual-price')]"))
                        .getText()
            );
            System.out.println(price);

            if (price > 99)
                higherThan99GiftCard = giftCard;
        }

        higherThan99GiftCard.findElement(By.xpath("//div[@class='picture']/a")).click();

        //TODO: Surastas elementas su maziausia kaina, atlikti visa kita
    }
}
