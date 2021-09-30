package com.udacity.jwdnd.course1.cloudstorage.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {



//    @FindBy(css = "#inputFirstName")
//    private WebElement firstNameField;


    @FindBy(id="login-link")
    private WebElement logInLink;

    @FindBy(id="inputFirstName")
    private WebElement firstName;

    @FindBy(id="inputLastName")
    private WebElement lastName;

    @FindBy(id="inputUsername")
    private WebElement username;

    @FindBy(id="inputPassword")
    private WebElement password;

    @FindBy(id="submitButton")
    private WebElement submitSignupButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password ){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submitSignupButton.click();
    }

    public void navigateToLoginPage(){
        logInLink.click();
    }



}
