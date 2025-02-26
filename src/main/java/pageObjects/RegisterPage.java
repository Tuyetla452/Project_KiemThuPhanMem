package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage extends GeneralPage {

    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPIDPassport = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblErrorMsg = By.xpath("//*[@id='content']");
    private final By _lblSuccessMsg = By.id("content");
    private final By _lblPasswordErrorMsg = By.xpath("//*[@id='RegisterForm']/fieldset/ol/li[2]/label[2]");
    private final By _lblPIDErrorMsg = By.xpath("//div[1]/div[2]/form/fieldset/ol/li[4]/label[2]");
    private final By _lblErrorMsgConfirmPw = By.xpath("//*[@id='content']/p[2]");
    private final By _tabRegister = By.xpath("//a[contains(text(),'Register')]");

    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    public WebElement getTxtPIDPassport() {
        return Constant.WEBDRIVER.findElement(_txtPIDPassport);
    }

    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }

    public WebElement getLblErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblErrorMsg);
    }

    public WebElement getLblSuccessMsg() {
        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(_lblSuccessMsg));
        return Constant.WEBDRIVER.findElement(_lblSuccessMsg);
    }

    public void register(String email, String password, String confirmPassword, String pidPassport) {
        getTxtEmail().sendKeys(email);
        getTxtPassword().sendKeys(password);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPIDPassport().sendKeys(pidPassport);
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", getBtnRegister());
        getBtnRegister().click();

        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(_lblErrorMsg));
    }

    public String getErrorMessage() {

        return Constant.WEBDRIVER.findElement(_lblErrorMsgConfirmPw).getText();
    }

    public String getSuccessMessage() {
        new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(_lblSuccessMsg));
        return Constant.WEBDRIVER.findElement(_lblSuccessMsg).getText();
    }

    public String getPasswordErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPasswordErrorMsg).getText();
    }

    public String getPIDErrorMessage() {
        return Constant.WEBDRIVER.findElement(_lblPIDErrorMsg).getText();
    }
}
