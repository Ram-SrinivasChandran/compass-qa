package net.breezeware.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.breezeware.Compass.LogInPage;

public class AbstractComponent {

    WebDriver driver;

    WebDriver adminDriver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.adminDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Profile']")
    WebElement profile;

    @FindBy(css = "div ul div li:nth-child(1)")
    WebElement login;

    @FindBy(xpath = "//button[normalize-space()='Projects']")
    WebElement project;
    @FindBy(css = "button[aria-label='Go to next page']")
    WebElement gotoNextPage;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    public LogInPage gotoLogin() {
        waitForWebElementToAppear(profile);
        profile.click();
        login.click();
        LogInPage loginPage = new LogInPage(driver);
        return loginPage;
        // return null;
    }

    public void pagination() throws InterruptedException {

        gotoNextPage.click();
        Thread.sleep(2000);

    }

}
