package testcases;

import Common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class RegisterTests {
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
    public void TC07() {
        System.out.println("TC7 -User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoSignUpPage();
        registerPage.register("invalidEmail2@gmail.com", "ivalidPassword1231", "validPassword1231", "B98765432");

        String actualSuccessMessage = registerPage.getSuccessMessage();
        String expectedSuccessMessage = "Thank you for registering your account";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Error message is not displayed as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoSignUpPage();
        registerPage.register("yt23f@gmail.com","123456","654321", "A12345678");

        String actualErrorMessage = registerPage.getErrorMessage();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }

    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoSignUpPage();
        registerPage.register("testuser@gmail.com", "", "", "");

        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(registerPage.getErrorMessage(), expectedErrorMessage, "The actual error message differs from the expected result.");

        String expectedPasswordErrorMessage = "Invalid password length.";
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), expectedPasswordErrorMessage, "The actual password error message differs from the expected result.");

        String expectedPIDErrorMessage = "Invalid ID length.";
        Assert.assertEquals(registerPage.getPIDErrorMessage(), expectedPIDErrorMessage, "The actual PID error message differs from the expected result.");

    }
}

