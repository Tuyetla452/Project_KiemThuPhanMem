package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage extends GeneralPage {
    private final WebDriver driver;
    private final String dynamicCancelTicketButton = "//input[@type='button' and @value='Cancel' and contains(@onclick, 'DeleteTicket(%s)')]";
    private final By _txtheader =By.xpath("//h1[text()='Manage Tickets']");
    
    public MyTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void cancelTicket(String ticketID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(dynamicCancelTicketButton, ticketID))));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancelButton);

        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        System.out.println("Ticket has been successfully canceled.");
    }

    public boolean isTicketCancelled(String ticketID) {
        return driver.findElements(By.xpath(String.format(dynamicCancelTicketButton, ticketID))).isEmpty();
    }

    public boolean isPageDisplayed() {

        return !driver.findElements(_txtheader).isEmpty();
    }




}


