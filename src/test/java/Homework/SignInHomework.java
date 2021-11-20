package Homework;

import com.github.javafaker.Faker;
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

import java.util.concurrent.TimeUnit;

public class SignInHomework {
    Faker accountFaker =new Faker();
    WebDriver driver;
    String email= accountFaker.internet().emailAddress();
    String fname= accountFaker.name().firstName();
    String lname= accountFaker.name().lastName();
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
    }

    @Test
    public void accountRegistrationTest() throws InterruptedException {
        verifyText();
        sendCredentialKeys();
        verifyAddress();
        verifyCreation();

    }

    public void verifyText() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='login']")).click();

        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
        WebElement crAccount=driver.findElement(By.xpath("//h1[@class='page-heading']"));

        Assert.assertTrue(crAccount.isDisplayed());
        WebElement personalInfo=driver.findElement(By.xpath("//h3[@class='page-subheading']"));
        Assert.assertTrue(personalInfo.isDisplayed());
        WebElement actTitle=driver.findElement(By.xpath("//label[.='Title']"));
        Assert.assertTrue(actTitle.isDisplayed());

    }


    public void sendCredentialKeys() throws InterruptedException {

        WebElement mrOption=driver.findElement(By.xpath("//input[@value=1]"));
        if(!mrOption.isSelected()){
            mrOption.click();
        }
        Thread.sleep(10000);

        String password= accountFaker.internet().password();

        driver.findElement(By.cssSelector("input[id='customer_firstname']")).sendKeys(fname);
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lname);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);

        WebElement days=driver.findElement(By.id("days"));
        WebElement months=driver.findElement(By.id("months"));
        WebElement years=driver.findElement(By.id("years"));
        Select selectDay=new Select(days);
        Select selectMonth=new Select(months);
        Select selectYear=new Select(years);
        Thread.sleep(5000);
        selectDay.selectByValue("1");
        selectMonth.selectByValue("6");
        selectYear.selectByValue("2000");
        WebElement checkBox1=driver.findElement(By.xpath("//input[@id='newsletter']"));
        WebElement checkBox2=driver.findElement(By.xpath("//input[@id='optin']"));

        if(!checkBox1.isSelected()){
            checkBox1.click();
        }
        if(checkBox2.isSelected()){
            checkBox2.click();
        }
        //        Then verify that checkbox1 and checkbox 2 is checked.
        Assert.assertTrue(checkBox1.isSelected());
        Assert.assertTrue(!checkBox2.isSelected());


    }

    public void verifyAddress(){
        String address1=accountFaker.address().fullAddress();
        String city=accountFaker.address().city();
        String postCode=accountFaker.address().zipCode();
        String mobilePhone=accountFaker.phoneNumber().cellPhone();
        String aliasAddress=accountFaker.address().streetAddress();
        driver.findElement(By.id("firstname")).sendKeys(fname);
        driver.findElement(By.id("lastname")).sendKeys(lname);
        driver.findElement(By.id("address1")).sendKeys(address1);
        driver.findElement(By.id("city")).sendKeys(city);
        WebElement state=driver.findElement(By.id("id_state"));
        Select states=new Select(state);
        states.selectByVisibleText("Alabama");
        driver.findElement(By.id("postcode")).sendKeys(postCode);
        WebElement country=driver.findElement(By.id("id_country"));
        Select countries=new Select(country);
        countries.getFirstSelectedOption();
        driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys(mobilePhone);
        driver.findElement(By.id("alias")).sendKeys(aliasAddress);


    }


    public void verifyCreation(){
        driver.findElement(By.xpath("//*[.='Register']")).click();
        WebElement myAccountText = driver.findElement(By.xpath("//h1[.='My account']"));
        Assert.assertTrue(myAccountText.isDisplayed());


    }

    @After
    public void tearDown(){
        driver.close();
    }

}
