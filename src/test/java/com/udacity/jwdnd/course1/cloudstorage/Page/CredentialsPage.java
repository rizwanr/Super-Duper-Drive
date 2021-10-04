package com.udacity.jwdnd.course1.cloudstorage.Page;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


    @FindBy(id="btnEditCredential")
    private List<WebElement> editCredentialButton;


    private CredentialService credentialService;
    private EncryptionService encryptionService;


    public CredentialsPage(WebDriver driver, CredentialService credentialService, EncryptionService encryptionService) {
        PageFactory.initElements(driver, this);
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;


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


    public String getDecryptedPassword(String  username, CredentialService credentialService, EncryptionService encryptionService) throws Exception {


        String encryptedPassword = getEncryptedPassword(username,credentialService);
        String key = credentialService.getCredentialByUsername(username).getKey();
        return encryptionService.decryptValue(encryptedPassword, key);
    }



    public  String getEncryptedPassword( String  username, CredentialService credentialService) throws Exception {

        Credential credential = credentialService.getCredentialByUsername(username);

        String encryptedPassword=credential.getPassword();

        return encryptedPassword;



    }


    public void deleteCredential(WebDriver driver){
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(credentialTab)).click();
        wait.until(ExpectedConditions.visibilityOf(deleteCredential.get(0))).click();


    }


    public void editCredential(WebDriver driver, String url, String username, String password) throws InterruptedException {
        wait = new WebDriverWait(driver, 20);
        credentialTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(editCredentialButton.get(0))).click();
        wait.until(ExpectedConditions.elementToBeClickable(textboxUrl)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(textboxUsername)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(textboxPassword)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(textboxUrl)).sendKeys(url);
        wait.until(ExpectedConditions.elementToBeClickable(textboxUsername)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(textboxPassword)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(saveCredential)).click();
        resultPage = new ResultPage(driver);
        resultPage.returnToHome();
        credentialTab.click();








    }
}
