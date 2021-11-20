package com.techproed;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day05_DropDown {
/*
/Go to https://the-internet.herokuapp.com/dropdown
//1.Create method selectByIndexTest and Select and Print Option 1 using index
//2.Create method selectByValueTest Select and Print Option 2 by value
//3.Create method selectByVisibleTextTest Select and Print Option 1 value by visible text
//4.Create method printAllTest Print all dropdown value
//5.Create method printFirstSelectedOptionTest Print first selected option
//6.Find the size of the dropdown, Print "Expected Is Not Equal Actual" if there are not 4 elements in the dropdown.
 */
WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void selectByIndex() throws InterruptedException {

        //1. locate the dropdown element
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        //2. use select object
        Select select = new Select(dropdown);
        //3. use any 3 method to select-index,value, visibletext
        Thread.sleep(5000);
        select.selectByIndex(1);
        Thread.sleep(5000);
    }
//2.Create method selectByValueTest Select and Print Option 2 by value
//3.Create method selectByVisibleTextTest Select and Print Option 1 value by visible text
//4.Create method printAllTest Print all dropdown value
//5.Create method printFirstSelectedOptionTest Print first selected option
//6.Find the size of the dropdown, Print "Expected Is Not Equal Actual" if there are not 4 elements in the dropdown.
@Test
public void selectByValueTest() throws InterruptedException {
    //2.Create method selectByValueTest Select Option 2 by value
    WebElement dropdown= driver.findElement(By.id("dropdown"));
    Select select = new Select(dropdown);
    Thread.sleep(5000);
    select.selectByValue("2");
}
    @Test
    public void selectByVisibleTextTest() throws InterruptedException {
        //2.Create method selectByVisibleTextTest Select Option 1 by visible text
        WebElement dropdown= driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        Thread.sleep(5000);
        select.selectByVisibleText("Option 1");
    }
    //4.Create method printAllTest Print all dropdown value
    @Test
    public void printAllTest(){
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        //getOptions(); returns all dropdown options as List<WebElement>
        List<WebElement> allOption = select.getOptions();
        //use a loop to get single elements
        for (WebElement eachOption : allOption){
            System.out.println(eachOption.getText());
        }
    }
    //5.Create method printFirstSelectedOptionTest Print first selected option
    @Test
    public void printFirstSelectedOptionTest(){
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        WebElement firstSelectedOption = select.getFirstSelectedOption();
        System.out.println(firstSelectedOption.getText());
        //Asserting if selected option is Please select an option
        Assert.assertEquals("Please select an option",firstSelectedOption.getText());
    }
//6.Find the size of the dropdown, Print "Expected Is Not Equal Actual" if there are not 4 elements in the dropdown.
//6.Crate methods. sizeTest. Find the size of the dropdown,
// Print "Expected Is Not Equal Actual" if there are not 4 elements in the dropdown.
@Test
public void sizeTest(){
    WebElement dropdown = driver.findElement(By.id("dropdown"));
    Select select = new Select(dropdown);
    List<WebElement> allOptions = select.getOptions();
    int size = allOptions.size();
    Assert.assertEquals("Expected Is Not Equal Actual",4,size);
    //   Expected Is Not Equal Actual will be displayed ONLY IF THE ASSERTION FAILS
}

    @After
    public void tearDown(){
        driver.close();
    }
}
