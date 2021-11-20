package Homework;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.techproed.utilities.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AmazonDropdown extends TestBase {

    @Test
    public void dropdownTest() {
        driver.get("https://www.amazon.de/");
        WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));

        Select dropSelect = new Select(dropDown);
        System.out.println(dropSelect.getFirstSelectedOption().getText());
        String allDepartments = dropSelect.getFirstSelectedOption().getText();
        Assert.assertEquals("first option does'nt match", "Alle Kategorien", allDepartments);
        dropSelect.selectByIndex(3);
        String firstSelected = dropSelect.getFirstSelectedOption().getText();
        System.out.println(firstSelected);
        Assert.assertEquals("Amazon Global Store", firstSelected);
        List<WebElement> allOptions = dropSelect.getOptions();
        List<String> optionsString = new ArrayList<>();
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
            optionsString.add(option.getText());
        }

        System.out.println(allOptions.size());

        if (optionsString.contains("Appliance")) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        List<String> sortedOptions = allOptions.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        if (optionsString.equals(sortedOptions)) {
            System.out.println("Dropdown is in alphabetical order");
        } else {
            System.out.println("Dropdown is NOT in alphabetical order");

        }

    }
}
