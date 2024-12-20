package net.breezeware.Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.breezeware.Compass.LogInPage;

public class BaseTest {
    public static WebDriver driver;

    public WebDriver adminDriver;

    public LogInPage loginPage;

    /**
     * Initialize the WebDriver.
     * @return             driver on which the test should execute.
     * @throws IOException
     */

    public static WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//net//breezeware//resources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("----no-sandbox");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
            driver = new FirefoxDriver();

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public WebDriver initializeAdminDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
                + "//src//main//java//net//breezeware//resources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("----no-sandbox");

            adminDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
            adminDriver = new FirefoxDriver();
        }

        // adminDriver.manage().window().maximize();
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return adminDriver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath));

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data =
                mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
                });

        return data;
    }

    /**
     * Takes screenshot of failed test.
     * @param  testCaseName
     * @param  driver
     * @return              screenshot path
     * @throws IOException
     */
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    // @BeforeTest(alwaysRun = true)
    // public LoginPage launchApplication() throws IOException {
    // driver= initializeDriver();
    // loginPage=new LoginPage(driver);
    // loginPage.goTo();
    // loginPage.gotoLogin();
    // return loginPage;
    // }

    // @BeforeTest(alwaysRun = true )
    // public AdminLoginPage launchAdminApplication() throws IOException {
    // adminDriver= initializeAdminDriver();
    // adminLoginPage=new AdminLoginPage(adminDriver);
    // adminLoginPage.goToAdmin();
    // return adminLoginPage;
    // }

    /**
     * Driver will close after all the tests executed.
     */
    @AfterTest
    public void tearDown() {
        System.out.println("Enter");
        driver.close();
    }

    @AfterTest
    public void tearDownAdmin() {
        System.out.println("Enter");
        adminDriver.close();
    }
}
