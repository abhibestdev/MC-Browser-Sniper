package me.abhi.sniper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sniper {

    public static void main(String[] args) {
        String chromeDriverPath = "C:\\Users\\mrabh\\Desktop\\chromedriver.exe";

        try {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--window-size=1920,1200", "--ignore-certificate-errors", "--no-sandbox", "--disable-dev-shm-usage", "start-maximized", "disable-infobars", "--disable-extensions");
            chromeOptions.setProxy(null);

            ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(chromeDriverPath)).usingPort(4444).build();
            service.start();

            WebDriver driver = new ChromeDriver(service, chromeOptions);

            System.out.println("Navigating to the Minecraft login page!");
            driver.get("https://my.minecraft.net/en-us/login/");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ready?");


            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {


                String username = driver.findElement(By.xpath("//span[@data-testid='profile_name']")).getText();

                System.out.println("Username: " + username);

                LocalTime localTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                String time = localTime.format(formatter);

                System.out.println("Enter time of drop");
                System.out.println("Current time: " + time);
                String dropTime = scanner.next();

                System.out.println("Drop time: " + dropTime);

                ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                scheduledExecutorService.scheduleAtFixedRate(new SniperTask(driver, dropTime), 0, 275L, TimeUnit.MILLISECONDS);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
