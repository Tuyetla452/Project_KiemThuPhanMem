package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {

    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='Login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _lnkForgotPassword = By.xpath("//a[contains(text(),'Forgot Password page')]");

    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getlblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    public String getErrorMessage() {
        return getlblLoginErrorMsg().getText();
    }

    public HomePage login(String username, String password) {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new HomePage();
    }

    public String getLoginFormTitle() {
        return Constant.WEBDRIVER.findElement(By.cssSelector("h1")).getText();
    }

    public ForgotPasswordPage gotoForgotPasswordPage() {
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", Constant.WEBDRIVER.findElement(_lnkForgotPassword));
        Constant.WEBDRIVER.findElement(_lnkForgotPassword).click();
        return new ForgotPasswordPage(Constant.WEBDRIVER);
    }
}
