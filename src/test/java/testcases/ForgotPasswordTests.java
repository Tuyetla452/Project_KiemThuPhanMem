package testcases;
import Common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class ForgotPasswordTests {
    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the ChromeDriver and maximizing window");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing the browser");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC12_TC13() {
        System.out.println(" TC12 - Errors display when password reset token is blank and TC13 - Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmailAddress(Constant.USERNAME);
        forgotPasswordPage.clickSendInstructionsButton();

        Assert.assertTrue(false, "BUG SEP OI! CODE lai sau khi fix bug");
    }
}
