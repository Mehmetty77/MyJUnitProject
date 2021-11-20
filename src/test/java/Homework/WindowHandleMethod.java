package Homework;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHandleMethod {

    WebDriver driver;

    public String getNewTab(Set<String> aWH, String wH1){
        String window2Handle="";
        StringBuilder window2Handlesb=new StringBuilder(window2Handle);
        for (String eachWindowHandle:aWH) {
            if (!eachWindowHandle.equals(wH1)){
                window2Handle=driver.switchTo().window(eachWindowHandle).getWindowHandle();
                window2Handlesb.append(window2Handle);
                break;
            }
        }
        window2Handle =window2Handlesb.toString();
        return window2Handle;
    }



}
