package testcases;

import Common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class LoginTests {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank Username textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);

        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

    }


    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "invalid");

        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on Book ticket tab");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = GeneralPage.gotoBookTicketPage();

        String actualMsg = loginPage.getLoginFormTitle();
        String expectedMsg = "Login Page";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        for(int i = 0; i<3; i++){
            loginPage.login(Constant.USERNAME, "invalidPassword");
        }

        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertTrue(homePage.isTabDisplayed("My ticket"), "My ticket tab is not displayed.");
        Assert.assertTrue(homePage.isTabDisplayed("Change password"), "Change password tab is not displayed.");
        Assert.assertTrue(homePage.isTabDisplayed("Log out"), "Log out tab is not displayed.");

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isPageDisplayed(), "My ticket page is not displayed.");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertTrue(changePasswordPage.isPageDisplayed(), "Change password page is not displayed.");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account that hasn't been activated");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login("invalidEmail@gmail.com", "invalidPassword");

        String expectedErrorMessage = "Invalid username or password. Please try again.";
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage, "Error message is not displayed as expected");
    }
}
