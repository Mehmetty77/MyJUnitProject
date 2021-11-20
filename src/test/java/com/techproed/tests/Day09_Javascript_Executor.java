package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Day09_Javascript_Executor extends TestBase {

    JavascriptExecutor je=(JavascriptExecutor) driver;

    @Test
    public void scrollIntoView() throws InterruptedException {
        driver.get("http://www.carettahotel.com/");
          /*
//        * Given user is on the application url
//        * Then verify the text "Recent Blog" is on the page
//        * */
        //We will scroll down to this element
        WebElement recentBlog = driver.findElement(By.xpath("//*[.='Recent Blog']"));


        //Creating javascript executor object
        JavascriptExecutor je = (JavascriptExecutor) driver;

        //Scrolling down to the element using scrollIntoView(true) method
        je.executeScript("arguments[0].scrollIntoView(true);",recentBlog);
        //Waiting for 3 second for the element text
        Thread.sleep(3000);
        Assert.assertEquals(recentBlog.getText(),"Recent Blog");
    }
}