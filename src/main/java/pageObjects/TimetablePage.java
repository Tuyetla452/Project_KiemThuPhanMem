package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import Common.Constant;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimetablePage {

    private final By _formTable = By.className("MyTable");

    private By getBookTicketButton(String departStation, String arriveStation) {
        return By.xpath("//td[contains(text(), '" + departStation + "')]/following-sibling::td[contains(text(), '" + arriveStation + "')]/following-sibling::td//a[contains(text(), 'book ticket')]");
    }

    public void clickBookTicket(String departStation, String arriveStation) {
        By btnBookTicket = getBookTicketButton(departStation, arriveStation);

        WebElement table = Constant.WEBDRIVER.findElement(_formTable);
        WebElement row = table.findElement(btnBookTicket);

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", row);

        row.click();

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("DepartStation")));
    }
}