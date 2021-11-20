package com.techproed;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
public class Day06_Alert {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }
    @Test
    public void acceptAlert(){
//        click on the first alert,
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
//        verify the text “I am a JS Alert” ,
        String alertText=driver.switchTo().alert().getText();
        Assert.assertEquals("I am a JS Alert",alertText);
//        click OK ,
        driver.switchTo().alert().accept();
//        and Verify “You successfully clicked an alert”
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals("You successfully clicked an alert",result);
    }
    @Test
    public void dismissAlert(){
//        click on the second alert,
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
//        verify text "I am a JS Confirm”,
        Assert.assertEquals("Text Doesn't match",
                "I am a JS Confirm",
                driver.switchTo().alert().getText());
//        click cancel,
        driver.switchTo().alert().dismiss();
//        and Verify “You clicked: Cancel”
        Assert.assertEquals("Result Doesn't match",
                "You clicked: Cancel",
                driver.findElement(By.xpath("//p[@id='result']")).getText());
    }
    @Test
    public void sendKeys(){
//        sendKeysAlert()=>
//        click on the third alert,
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
//        verify text “I am a JS prompt”,
        String actual=driver.switchTo().alert().getText();
        String expected="I am a JS prompt";
        Assert.assertEquals(expected,actual);
//        type “Hello World”,
        driver.switchTo().alert().sendKeys("Hello World");
//        click OK,
        driver.switchTo().alert().accept();
//        and Verify “You entered: Hello World”
        Assert.assertEquals("You entered: Hello World",
                driver.findElement(By.xpath("//p[@id='result']")).getText());
        //done....any question
    }
}