package pageObjects;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailField = By.xpath("//*[@id='email']");
    private final By sendInstructionsButton = By.xpath("//input[@type='submit' and @value='Send Instructions']");
    private final By errorMessage = By.xpath("//p[@class='error']");

    public void enterEmailAddress(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickSendInstructionsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(sendInstructionsButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        wait.until(ExpectedConditions.elementToBeClickable(sendInstructionsButton)).click();
    }

}
