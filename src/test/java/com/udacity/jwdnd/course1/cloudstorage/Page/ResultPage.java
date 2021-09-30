package com.udacity.jwdnd.course1.cloudstorage.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "result-success")
    private WebElement returnFromResultToHomePage;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void returnToHome(){
         //WebDriverWait wait = new WebDriverWait(driver, 20);
       //  wait.until(ExpectedConditions.elementToBeClickable(returnFromResultToHomePage)).click();
        returnFromResultToHomePage.click();

    }

}
