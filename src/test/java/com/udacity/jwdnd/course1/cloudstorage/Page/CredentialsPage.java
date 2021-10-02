package com.udacity.jwdnd.course1.cloudstorage.Page;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CredentialsPage {

//    Write a test that creates a set of credentials, verifies that they are displayed, and verifies
//    that the displayed password is encrypted.
//    Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
//    Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.


    ResultPage resultPage;
    WebDriverWait wait;
    UserService userService;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id="addANote")
    public WebElement addCredential;

    @FindBy(id="credential-url")
    public WebElement textboxUrl;


    @FindBy(id="credential-username")
    public WebElement textboxUsername;

    @FindBy(id="credential-password")
    public WebElement textboxPassword;

    @FindBy(id="saveCredential")
    public WebElement saveCredential;


    @FindBy(id="tbrowurl")
    public WebElement tbrowUrl;

    @FindBy(id="tbrowusername")
    public WebElement tbrowUsername;

    @FindBy(id="tbrowpassword")
    public WebElement tbrowPassword;


    @FindBy(id = "deleteCredential")
    private List<WebElement> deleteCredential;

    @Autowired
    private CredentialService credentialService;


    public CredentialsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);


    }

    public void addCredential(WebDriver driver, String url, String username, String password) {
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(credentialTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addCredential)).click();
        wait.until(ExpectedConditions.elementToBeClickable(textboxUrl)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(textboxUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(textboxPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredential)).click();
        resultPage=new ResultPage(driver);
        resultPage.returnToHome();
        wait.until(ExpectedConditions.elementToBeClickable(credentialTab)).click();

    }

    public Credential getFirstCredential(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        String url = wait.until(ExpectedConditions.elementToBeClickable(tbrowUrl)).getText();
        String username = wait.until(ExpectedConditions.elementToBeClickable(tbrowUsername)).getText();
        String password = wait.until(ExpectedConditions.elementToBeClickable(tbrowPassword)).getText();
        return new Credential(url,username, password);
    }


//    private String getDecryptedPassword(String encryptedPassword) throws Exception {
//        User user = userService.getUser("r");
//
//        credentialService.getCredentials(user.getUserId()).get(0);
//        //return encryptionService.decryptValue(encryptedPassword, credential.getKey());
//    }

    public  String getEncryptedPassword(Integer userId) throws Exception {
        String encryptedPassword= credentialService.getCredentials(userId).get(0).getPassword();

        return encryptedPassword;



    }

    public void deleteCredential(WebDriver driver){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(deleteCredential.get(0))).click();


    }







}
