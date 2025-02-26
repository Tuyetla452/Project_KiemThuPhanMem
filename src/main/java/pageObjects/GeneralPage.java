package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    private final By tabLogin = By.xpath("//div[@id= 'menu']//a[@href = '/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id = 'menu']//a[@href = '/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class = 'account']/strong");

    protected WebElement getTabLogin () {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout () {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }

    protected WebElement getLblWelcomeMessage () {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }

    public String getWelcomeMessage()
    {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public static LoginPage gotoBookTicketPage() {
        Constant.WEBDRIVER.findElement(By.linkText("Book ticket")).click();
        return new LoginPage();
    }

    public static BookTicketPage gotoBookTicketPage1() {
        Constant.WEBDRIVER.findElement(By.linkText("Book ticket")).click();
        return new BookTicketPage();
    }

    public RegisterPage gotoSignUpPage() {
        Constant.WEBDRIVER.findElement(By.linkText("Register")).click();
        return new RegisterPage();
    }

    public LogOutPage gotoLogOutPage() {
        Constant.WEBDRIVER.findElement(By.linkText("Log out")).click();
        return new LogOutPage();
    }

    public MyTicketPage gotoMyTicketPage() {
        Constant.WEBDRIVER.findElement(By.linkText("My ticket")).click();
        return new MyTicketPage(Constant.WEBDRIVER);
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        Constant.WEBDRIVER.findElement(By.linkText("Change password")).click();
        return new ChangePasswordPage();
    }

    public TimetablePage gotoTimetablePage() {
        Constant.WEBDRIVER.findElement(By.linkText("Timetable")).click();
        return new TimetablePage();
    }

    public boolean isTabDisplayed(String tabName) {
        try {
            return Constant.WEBDRIVER.findElement(By.linkText(tabName)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
