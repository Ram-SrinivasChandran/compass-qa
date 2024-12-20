package net.breezeware.Compass;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.breezeware.AbstractComponents.AbstractComponent;

public class LogInPage extends AbstractComponent {

    WebDriver driver;

    Actions actions;

    public LogInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Profile']")
    public WebElement profile;

    @FindBy(css = "div ul div li:nth-child(1)")
    public WebElement login;

    @FindBy(xpath = "//*[@id=\":r0:\"]")
    public WebElement userEmail;

    @FindBy(xpath = "//*[@id=\":r1:\"]")
    public WebElement userPassword;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/button")
    public WebElement submit;

    @FindBy(xpath = "//*[@id=\"alert\"]")
    public WebElement errorMessage;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[1]/p[2]")
    public WebElement emailValidationError;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[2]/p[2]")
    public WebElement passwordValidationError;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div/ul[2]/div")
    public WebElement userProfile;

    public void loginApplication(String email, String password) throws InterruptedException {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        waitForWebElementToAppear(userProfile);
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void clickLogin() {
        profile.click();
        login.click();
    }

    public void enterEmail(String email) throws InterruptedException {
        Thread.sleep(1000);
        userEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void login() {
        submit.click();
    }

    public String getEmailValidationError() {
        waitForWebElementToAppear(emailValidationError);
        return emailValidationError.getText();
    }

    public String getPasswordValidationError() {
        waitForWebElementToAppear(passwordValidationError);
        return passwordValidationError.getText();
    }

    public void clearFields() {
        // Clear the userEmail field
        actions.click(userEmail) // Focus on the email input field
                .keyDown(Keys.CONTROL) // Press Ctrl
                .sendKeys("a") // Select all text
                .keyUp(Keys.CONTROL) // Release Ctrl
                .sendKeys(Keys.BACK_SPACE) // Delete the selected text
                .perform(); // Perform the action

        // Clear the userPassword field
        actions.click(userPassword) // Focus on the password input field
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }
}
