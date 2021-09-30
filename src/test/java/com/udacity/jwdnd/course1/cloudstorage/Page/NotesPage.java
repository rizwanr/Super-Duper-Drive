package com.udacity.jwdnd.course1.cloudstorage.Page;

import org.openqa.selenium.By;
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

    @FindBy(id = "btnEditNote")
    private List<WebElement> editNote;

    @FindBy(id = "deleteNote")
    private List<WebElement> deleteNote;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addNewNote(WebDriver driver, String title, String description){
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));
        notesTab.click();
        WebDriverWait wait = new WebDriverWait(driver, 60000);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("addNewNote")));
        addNewNoteButton.click();

        WebDriverWait wait3 = new WebDriverWait(driver, 60000);
        WebElement marker3 = wait.until(webDriver -> webDriver.findElement(By.id("note-description")));

        this.title.sendKeys(title);
        this.description.sendKeys();
        WebDriverWait wait2 = new WebDriverWait(driver, 60000);
        WebElement marker2 = wait.until(webDriver -> webDriver.findElement(By.id("saveNotebutton")));
        saveNoteButton.click();

    }

    public void deleteNote(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(notesTab)).click();
        wait.until(ExpectedConditions.visibilityOf(deleteNote.get(0))).click();
    }



}
