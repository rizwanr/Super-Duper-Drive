package com.udacity.jwdnd.course1.cloudstorage.Page;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotesPage {

    ResultPage resultPage;
    WebDriverWait wait;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="addNewNote")
    private WebElement addNewNoteButton;

    @FindBy(id="loginButton")
    private WebElement submitLoginButton;

    @FindBy(id="note-title")
    private WebElement textboxTitle;

    @FindBy(id="note-description")
    private WebElement texboxDescription;

    @FindBy(id="saveNotebutton")
    private WebElement saveNoteButton;


    @FindBy(id="addTitle")
    private WebElement tbTitleText;

    @FindBy(id="addDescription")
    private WebElement tbDescriptionText;

    @FindBy(id = "btnEditNote")
    private  List<WebElement> editNote;

    @FindBy(id = "deleteNote")
    private List<WebElement> deleteNote;


    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addNewNote(WebDriver driver, String title, String description) {
        notesTab.click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(addNewNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(textboxTitle)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(texboxDescription)).sendKeys(description);
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();



    }

    public void deleteNote(WebDriver driver){
        resultPage = new ResultPage(driver);
        resultPage.returnToHome();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(notesTab)).click();
        wait.until(ExpectedConditions.visibilityOf(notesTab)).click();
        wait.until(ExpectedConditions.visibilityOf(deleteNote.get(0))).click();
    }

    public void editNote(WebDriver driver, String title, String description){
        resultPage = new ResultPage(driver);
        resultPage.returnToHome();
        wait = new WebDriverWait(driver, 20);
        notesTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(editNote.get(0))).click();
        wait.until(ExpectedConditions.elementToBeClickable(textboxTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(texboxDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(textboxTitle)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(texboxDescription)).sendKeys(description);
        wait.until(ExpectedConditions.elementToBeClickable(saveNoteButton)).click();
        resultPage.returnToHome();
        notesTab.click();



    }


    public Note getFirstNote(WebDriver driver) {
        wait = new WebDriverWait(driver, 20);
        String title = wait.until(ExpectedConditions.elementToBeClickable(tbTitleText)).getText();
        String description = tbDescriptionText.getText();
        return new Note(title, description);
    }







}
