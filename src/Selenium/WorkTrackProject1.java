package Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WorkTrackProject1 {

    WebDriver driver;
    WebDriverWait wait;

    // Login Credentials
    String userEmail = "anbukkarasisethu32@gmail.com";
    String userPassword = "Anbuu@200528.";

    String url =
            "https://employee-worktrack-5lmu.vercel.app/login";


    // =========================================
    // SETUP
    // =========================================

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        driver.manage().window().maximize();

        driver.get(url);

        System.out.println("WorkTrack Pro Website Opened");
    }


    // =========================================
    // TEST 1 - LOGIN
    // =========================================

    @Test(priority = 1)
    public void loginTest() {

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='email']")
                )
        );

        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='password']")
                )
        );

        email.sendKeys(userEmail);
        password.sendKeys(userPassword);

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Login']")
                )
        );

        loginButton.click();

        wait.until(
                ExpectedConditions.urlContains("/dashboard")
        );


        // EXPECTED RESULT
        String expectedResult = "/dashboard";

        // ACTUAL RESULT
        String actualResult = driver.getCurrentUrl();


        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + actualResult);


        // ASSERTION
        Assert.assertTrue(
                actualResult.contains(expectedResult),
                "Login Failed - Dashboard was not opened"
        );


        System.out.println("Login Test Passed");
    }


    // =========================================
    // TEST 2 - DASHBOARD
    // =========================================

    @Test(
            priority = 2,
            dependsOnMethods = "loginTest"
    )
    public void dashboardTest() {

        wait.until(
                ExpectedConditions.urlContains("/dashboard")
        );


        // EXPECTED RESULT
        String expectedResult = "/dashboard";

        // ACTUAL RESULT
        String actualResult = driver.getCurrentUrl();


        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + actualResult);


        // ASSERTION
        Assert.assertTrue(
                actualResult.contains(expectedResult),
                "Dashboard Verification Failed"
        );


        System.out.println("Dashboard Test Passed");
    }


    // =========================================
    // TEST 3 - CLOCK IN
    // =========================================

    @Test(
            priority = 3,
            dependsOnMethods = "dashboardTest"
    )
    public void clockInTest() {

        WebElement clockInButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//button[contains(normalize-space(.),'Clock In')]"
                        )
                )
        );


        // EXPECTED RESULT
        String expectedResult = "Clock In button is displayed";


        // ACTUAL RESULT
        String actualResult;

        if (clockInButton.isDisplayed()) {

            actualResult = "Clock In button is displayed";

        } else {

            actualResult = "Clock In button is not displayed";
        }


        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + actualResult);


        // ASSERTION
        Assert.assertEquals(
                actualResult,
                expectedResult,
                "Clock In button verification failed"
        );


        clockInButton.click();

        System.out.println("Clock In Button Clicked");
        System.out.println("Clock In Test Passed");
    }


    // =========================================
    // TEST 4 - LOGOUT
    // =========================================

    @Test(
            priority = 4,
            dependsOnMethods = "dashboardTest"
    )
    public void logoutTest() {

        WebElement logoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//button[contains(translate(normalize-space(.),"
                                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                                + "'abcdefghijklmnopqrstuvwxyz'),"
                                + "'logout')]"
                        )
                )
        );


        logoutButton.click();


        wait.until(
                ExpectedConditions.urlContains("/login")
        );


        // EXPECTED RESULT
        String expectedResult = "/login";

        // ACTUAL RESULT
        String actualResult = driver.getCurrentUrl();


        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + actualResult);


        // ASSERTION
        Assert.assertTrue(
                actualResult.contains(expectedResult),
                "Logout Failed - Login page was not opened"
        );


        System.out.println("Logout Test Passed");
    }


    // =========================================
    // CLOSE BROWSER
    // =========================================

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println("Browser Closed");
        }
    }
}


