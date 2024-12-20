package net.breezeware.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Test;

import net.breezeware.Compass.LogInPage;
import net.breezeware.ManageDrivers.CompassShareContext;
import net.breezeware.data.TestDataProvider;

public class LoginTest {
    CompassShareContext compassShareContext;
    LogInPage logInPage;

    public LoginTest() throws IOException {
        compassShareContext = new CompassShareContext();
        logInPage = compassShareContext.compassPageObjectManager().getLoginPage();
    }

    @Test(groups = "login", alwaysRun = true)
    public void emptyFieldsValidation() {
        // When I try to login without entering any credentials
        logInPage.login();

        // Then I should see validation messages
        String emailError = logInPage.getEmailValidationError();
        String passwordError = logInPage.getPasswordValidationError();

        Assert.assertEquals("Email is required", emailError);
        Assert.assertEquals("Password is required", passwordError);
    }

    @Test(groups = "login", alwaysRun = true, dependsOnMethods = "emptyFieldsValidation")
    public void invalidCredentialsValidation() throws InterruptedException {
        // When I enter incorrect credentials
        logInPage.enterEmail("invalid@example.com");
        logInPage.enterPassword("wrongPassword");
        logInPage.login();

        // Then I should see an error message
        String error = logInPage.getErrorMessage();
        Assert.assertEquals("Incorrect username or password.", error);

        // And the login fields should be cleared
        logInPage.clearFields();
        Thread.sleep(2000);
        Assert.assertEquals("", logInPage.userEmail.getAttribute("value"));
        Assert.assertEquals("", logInPage.userPassword.getAttribute("value"));
    }

    @Test(dataProvider = "getData", dataProviderClass = TestDataProvider.class, groups = "login",
            dependsOnMethods = { "net.breezeware.Tests.ForgetPasswordTest.resendOtp" })
    public void LoginRenterApplication(HashMap<String, String> input) throws InterruptedException {
        logInPage.loginApplication(input.get("email"), input.get("password"));
    }
}
