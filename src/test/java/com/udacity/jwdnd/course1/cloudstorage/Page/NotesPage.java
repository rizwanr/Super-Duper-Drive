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
    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="addNewNote")
    private WebElement addNewNoteButton;

    @FindBy(id="loginButton")
    private WebElement submitLoginButton;

    @FindBy(id="note-title")
    private WebElement title;

    @FindBy(id="note-description")
    private WebElement description;

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




    ResultPage resultPage;
    WebDriverWait wait;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addNewNote(WebDriver driver, String title, String description) {
        notesTab.click();
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(addNewNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.title)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(this.description)).sendKeys(description);
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
        wait.until(ExpectedConditions.elementToBeClickable(this.title)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.description)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(this.title)).sendKeys(title);
        wait.until(ExpectedConditions.elementToBeClickable(this.description)).sendKeys(description);
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
