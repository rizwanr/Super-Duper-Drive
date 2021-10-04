package com.udacity.jwdnd.course1.cloudstorage.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriverWait wait;

    @FindBy(id="inputUsername")
    private WebElement usernameTextbox;

    @FindBy(id="inputPassword")
    private WebElement passwordTextbox;

    @FindBy(id="loginButton")
    private WebElement submitLoginButton;



    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(WebDriver driver, String username, String password){
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.elementToBeClickable(usernameTextbox)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(passwordTextbox)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(submitLoginButton)). click();





    }


}
