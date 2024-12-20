package net.breezeware.Compass;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.breezeware.AbstractComponents.AbstractComponent;

public class ForgetPasswordPage extends AbstractComponent {

    WebDriver driver;

    Actions actions;

    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[3]/a/p")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[@id=\":r2:\"]")
    public WebElement resetEmailField;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[2]/button")
    public WebElement sendCodeButton;

    @FindBy(xpath = "//*[@id=\":r3:\"]")
    public WebElement otpField;

    @FindBy(xpath = "//*[@id=\"alert\"]")
    public WebElement invalidOtp;

    @FindBy(xpath = "//*[@id=\":r4:\"]")
    public WebElement newPasswordField;

    @FindBy(xpath = "//*[@id=\":r5:\"]")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[6]/button")
    public WebElement submitResetButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div/div[2]/form/div/div[3]/p[2]")
    public WebElement resendOtpLink;

    @FindBy(css = "//*[@id=\"alert\"]/div[2]")
    public WebElement errorMessage;

    @FindBy(xpath = "//*[@id=\"alert\"]")
    public WebElement otpSentMessage;


    public void navigateToForgotPassword() {
        forgotPasswordLink.click();
    }

    public void enterResetEmail(String email) {
        resetEmailField.sendKeys(email);
    }

    public void clickSendCode() {
        sendCodeButton.click();
    }

    public void enterOtp(String otp) {
        otpField.sendKeys(otp);
    }

    public String getInvalidOtpError(){
        return invalidOtp.getText();
    }

    public void enterNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword){
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void submitPasswordReset() {
        submitResetButton.click();
    }

    public void clickResendOtp() {
        resendOtpLink.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getOtpSentMessage() {
        return otpSentMessage.getText();
    }

    public void backToSignIn(){
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().refresh();
    }

    public void clearFields() {
        // Clear the otpField
        actions.click(otpField) // Focus on the email input field
                .keyDown(Keys.CONTROL) // Press Ctrl
                .sendKeys("a") // Select all text
                .keyUp(Keys.CONTROL) // Release Ctrl
                .sendKeys(Keys.BACK_SPACE) // Delete the selected text
                .perform(); // Perform the action

        // Clear the newPasswordField
        actions.click(newPasswordField) // Focus on the password input field
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();

        // Clear the confirmPasswordField
        actions.click(confirmPasswordField) // Focus on the password input field
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }
}