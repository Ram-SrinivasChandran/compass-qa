package net.breezeware.ManageDrivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;

public class CompassDriverManager {
    private static WebDriver adminDriver;

    public WebDriver getAdminDriver() throws IOException {
        adminDriver = createAdminDriver();
        return adminDriver;
    }

    public WebDriver createAdminDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//java//net/breezeware//resources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : properties.getProperty("browser");
        if (adminDriver == null) {
            try {
                if (browserName.contains("chrome")) {

                    ChromeOptions options = new ChromeOptions();
                    if (browserName.contains("headless")) {
                        options.addArguments("headless");
                    }

                    options.addArguments("--remote-allow-origins=*");
                    System.setProperty("webdriver.chrome.driver",
                            "/home/ram/Downloads/chromedriver-linux64/chromedriver");
                    adminDriver = new ChromeDriver(options);
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
                    FirefoxOptions options = new FirefoxOptions();
                    FirefoxProfile profile = new FirefoxProfile(new File("/home/ram/Downloads/firefox-123.0/firefox"));
                    options.setHeadless(true);
                    options.addPreference("gfx.direct2d.disabled", true);
                    options.addPreference("layers.acceleration.disabled", true);
                    options.setProfile(profile);
                    adminDriver = new FirefoxDriver(options);

                }

                adminDriver.get("https://qa.dynamo-cloud.com/");
                adminDriver.manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return adminDriver;
    }

    public void waitForSometimeImplicit(int time) {
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    @AfterTest
    public void tearDown() {
        adminDriver.close();
    }

}
