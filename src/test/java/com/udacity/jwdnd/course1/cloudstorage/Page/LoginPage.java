package com.udacity.jwdnd.course1.cloudstorage.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="inputUsername")
    private WebElement userName;

    @FindBy(id="inputPassword")
    private WebElement password;

    @FindBy(id="loginButton")
    private WebElement submitLoginButton;



    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
       this.userName.sendKeys(username);
        this.password.sendKeys(password);
        submitLoginButton.click();




    }


}
