package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XPathTest {
    public static WebDriver webDriver;

    @BeforeClass
    public void chrome(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/org/example/drivers/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--start-maximized");

        webDriver=new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void click(){
        webDriver.get("https://demoqa.com/elements");

        WebElement buttons = webDriver.findElement(By.xpath("//span[contains(text(),'Buttons')]"));
        buttons.click();

        WebElement clickMe=webDriver.findElement(By.xpath("//button[.='Click Me']"));
        clickMe.click();

        WebElement showMessage=webDriver.findElement(By.xpath("//p[@id='dynamicClickMessage']"));

        Assert.assertTrue(showMessage.isDisplayed());
        Assert.assertEquals(showMessage.getText(), "You have done a dynamic click");
    }

    @Test(priority = 1)
    public void record(){
        webDriver.get("https://demoqa.com/webtables");

        WebElement add=webDriver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        add.click();

        //region[Veri girme]
        WebElement fName=webDriver.findElement(By.cssSelector("//input[@id='firstName']"));
        fName.click();
        fName.sendKeys("Özen");

        WebElement lName=webDriver.findElement(By.cssSelector("//input[@id='lastName']"));
        lName.click();
        lName.sendKeys("Taştan");

        WebElement mail=webDriver.findElement(By.cssSelector("//input[@id='userEmail']"));
        mail.click();
        mail.sendKeys("ozentastan1@gmail.com");

        WebElement age=webDriver.findElement(By.cssSelector("//input[@id='age']"));
        age.click();
        age.sendKeys("27");

        WebElement salary=webDriver.findElement(By.cssSelector("//input[@id='salary']"));
        salary.click();
        salary.sendKeys("123456789");

        WebElement dep=webDriver.findElement(By.cssSelector("//input[@id='department']"));
        dep.click();
        dep.sendKeys("Meva");

//endregion

        WebElement sub=webDriver.findElement(By.xpath("//button[@id='submit']"));
        sub.click();
        WebElement depText =webDriver.findElement(By.xpath("//div[@class='rt-tr-group'][4]//div[6]"));
        String showDepText=depText.getText();

        WebElement edit=webDriver.findElement(By.xpath("//span[@id='edit-record-4']"));
        edit.click();

        WebElement dep2=webDriver.findElement(By.xpath("//div[@class='rt-tr-group'][4]//div[6]"));
        dep2.click();
        dep2.sendKeys("Meva-Ökc");

        WebElement sub2=webDriver.findElement(By.xpath("//button[@id='submit']"));
        sub2.click();

        WebElement dep2Text =webDriver.findElement(By.xpath("//div[@class='rt-tr-group'][4]//div[6]"));
        String showDep2Text=dep2Text.getText();

        if (!showDepText.equals(showDep2Text)){
            System.out.println("Veri değiştirildi");
        }else {
            System.out.println("Veri değiştirilmedi");
        }
    }
}
