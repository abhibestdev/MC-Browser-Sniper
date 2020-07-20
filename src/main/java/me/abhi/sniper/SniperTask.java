package me.abhi.sniper;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class SniperTask implements Runnable {

    private final WebDriver webDriver;
    private final String dropTime;

    @Override
    public void run() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String time = localTime.format(formatter);

        String[] dropTimeParts = dropTime.split(":");
        String[] localTimeParts = time.split(":");

        System.out.println(dropTimeParts[0] + " - " + localTimeParts[0] + " | " + dropTimeParts[1] + " - " + localTimeParts[1] + " | " + dropTimeParts[2] + " - " + localTimeParts[2]);

        if ((dropTimeParts[0].equalsIgnoreCase(localTimeParts[0]) && dropTimeParts[1].equalsIgnoreCase(localTimeParts[1])) && Integer.parseInt(dropTimeParts[2]) - 2 <= Integer.parseInt(localTimeParts[2])) {

            //  System.out.println("Start clicking");

            WebElement webElement = webDriver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/main/div/div/div/div/div[2]/div/div[5]/div[2]/div/form/button"));

            if (webElement != null) {
                webElement.click();

                System.out.println("Attempting to grab name!");
            } else {
                System.exit(-1);
            }
        }
    }
}
