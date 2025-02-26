package testcases;


import Common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class ChangePasswordTests {
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
    public void TC09() {
        System.out.println("TC09 - User can change password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("meomeo@gmail.com", "tuyetla452@@");

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(Constant.PASSWORD, "meomeomeomeo", "meomeomeomeo");

        String actualMsg = changePasswordPage.getSuccessMessage();
        String expectedMsg = "Your password has been updated";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
}
