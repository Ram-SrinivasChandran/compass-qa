package net.breezeware.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import net.breezeware.Components.BaseTest;

public class ErrorValidations extends BaseTest {
    @Test
    public void loginErrorValidation() throws InterruptedException {
        loginPage.loginApplication("ramsrinivas@breezeware.net", "Breeze@123");
        Assert.assertEquals("The credential that you've entered is incorrect", loginPage.getErrorMessage());
    }

}
