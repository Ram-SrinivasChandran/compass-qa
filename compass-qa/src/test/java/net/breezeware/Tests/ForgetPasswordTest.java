package net.breezeware.Tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import net.breezeware.Compass.ForgetPasswordPage;
import net.breezeware.ManageDrivers.CompassShareContext;

public class ForgetPasswordTest {
    CompassShareContext compassShareContext;
    ForgetPasswordPage forgetPasswordPage;

    public ForgetPasswordTest() throws IOException {
        compassShareContext = new CompassShareContext();
        forgetPasswordPage = compassShareContext.compassPageObjectManager().getForgetPasswordPage();
    }

    @Test(groups = "password-reset", alwaysRun = true)
    public void requestingPasswordReset() {
        // Given the Login page is displayed
        forgetPasswordPage.navigateToForgotPassword();

        // Then I should be directed to the password reset page
        Assert.assertTrue("Reset email field is not displayed", forgetPasswordPage.resetEmailField.isDisplayed());
    }

    @Test(groups = "password-reset", alwaysRun = true, dependsOnMethods = "requestingPasswordReset")
    public void requestOtpForPasswordReset() throws InterruptedException {
        // Given I am on the "Forgot Password" page
        forgetPasswordPage.enterResetEmail("reg@example.com");
        forgetPasswordPage.clickSendCode();
        Thread.sleep(2000);
    }

    @Test(groups = "password-reset", alwaysRun = true, dependsOnMethods = "requestOtpForPasswordReset")
    public void otpVerificationAndPasswordReset() {
        // Given I have requested an OTP for password reset
        forgetPasswordPage.enterOtp("123456");
        forgetPasswordPage.enterNewPassword("StrongPassword123");
        forgetPasswordPage.enterConfirmPassword("StrongPassword123");
        forgetPasswordPage.clearFields();
    }

    @Test(groups = "password-reset", alwaysRun = true, dependsOnMethods = "otpVerificationAndPasswordReset")
    public void incorrectOtpEntry() throws InterruptedException {
        // Given I have requested an OTP for password reset
        forgetPasswordPage.enterOtp("654357"); // Incorrect OTP
        forgetPasswordPage.enterNewPassword("StrongPassword13");
        forgetPasswordPage.enterConfirmPassword("StrongPassword13");
        forgetPasswordPage.submitPasswordReset();
        Thread.sleep(2000);
        String error = forgetPasswordPage.getInvalidOtpError();
        forgetPasswordPage.clearFields();

        // Then I should see an error message indicating that the entered OTP is
        // incorrect
        Assert.assertEquals("Invalid verification code provided, please try again.", error);

        // And I should not be allowed to proceed until I enter the correct OTP
        Assert.assertTrue("OTP field is not displayed", forgetPasswordPage.otpField.isDisplayed());
    }

    @Test(groups = "password-reset", alwaysRun = true, dependsOnMethods = "incorrectOtpEntry")
    public void resendOtp() throws InterruptedException {
        // Given I have requested an OTP for password reset
        forgetPasswordPage.clickResendOtp();
        Thread.sleep(2000);

        // Then a new OTP should be generated and sent to my registered email address
        String notification = forgetPasswordPage.getOtpSentMessage();// Assuming a message element for notification
        Assert.assertEquals("OTP has been resent successfully.", notification);
        forgetPasswordPage.backToSignIn();
    }
}
