package net.breezeware.ManageDrivers;

import org.openqa.selenium.WebDriver;

import net.breezeware.Compass.ForgetPasswordPage;
import net.breezeware.Compass.LogInPage;

public class CompassPageObjectManager {

    private WebDriver adminDriver;
    private LogInPage loginPage;
    private ForgetPasswordPage forgetPasswordPage;

    public CompassPageObjectManager(WebDriver adminDriver) {
        this.adminDriver = adminDriver;
    }

    public LogInPage getLoginPage() {
        loginPage = new LogInPage(adminDriver);
        return loginPage;
    }

    public ForgetPasswordPage getForgetPasswordPage() {
        forgetPasswordPage = new ForgetPasswordPage(adminDriver);
        return forgetPasswordPage;
    }

}
