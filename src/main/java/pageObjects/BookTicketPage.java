package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookTicketPage {

    private static final String _txtDepartDate = "//select[@name='Date']";
    private static final String _txtDepartFrom = "//select[@name='DepartStation']";
    private static final String _txtArriveAt = "//span[@id='ArriveStation']/select";
    private static final String _ddlSeatType = "//select[@name='SeatType']";
    private static final String _ddlTicketAmount = "//select[@name='TicketAmount']";
    private static final String _btnSubmit = "//input[@type='submit' and @value='Book ticket']";
    private static final String _lblHeaderSuccessMessage = "//h1[text()='Ticket Booked Successfully!']";
    private static final String _table = "//table[@class='MyTable WideTable']";

    private final WebDriverWait wait;

    public BookTicketPage() {
        wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getHeaderSuccessMessage() {
        WebElement successHeader = waitForElement(By.xpath(_lblHeaderSuccessMessage));
        return successHeader.getText();
    }

    public void selectDepartDate(int daysFromToday) {
        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String formattedDate = targetDate.format(formatter);
        new Select(waitForElement(By.xpath(_txtDepartDate))).selectByVisibleText(formattedDate);
    }

    public void selectDepartFrom(String departFrom) {
        new Select(waitForElement(By.xpath(_txtDepartFrom))).selectByVisibleText(departFrom);
    }

    public void selectArriveAt(String arriveAt) {
        new Select(waitForElement(By.xpath(_txtArriveAt))).selectByVisibleText(arriveAt);
    }

    public void selectSeatType(String seatType) {
        new Select(waitForElement(By.xpath(_ddlSeatType))).selectByVisibleText(seatType);
    }

    public void selectNumberOfTickets(int ticketAmount) {
        new Select(waitForElement(By.xpath(_ddlTicketAmount))).selectByValue(String.valueOf(ticketAmount));
    }

    public void clickSubmit() {
        WebElement submitButton = waitForElement(By.xpath(_btnSubmit));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        js.executeScript("arguments[0].click();", submitButton);
    }

    public String bookTicket(int daysFromToday, String departStation, String arriveStation, String seatType, int numberOfTickets) {
        selectDepartDate(daysFromToday);
        selectDepartFrom(departStation);
        selectArriveAt(arriveStation);
        selectSeatType(seatType);
        selectNumberOfTickets(numberOfTickets);
        clickSubmit();

        String url = Constant.WEBDRIVER.getCurrentUrl();
        return url.split("id=")[1];
    }

    public boolean checkInfBookTicket(int daysFromToday, String expectedDepartFrom, String expectedArriveAt, String expectedSeatType, int expectedAmount) {
        WebElement successMessage = waitForElement(By.xpath(_lblHeaderSuccessMessage));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", successMessage);

        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String expectedDepartDate = targetDate.format(formatter);

        WebElement table = Constant.WEBDRIVER.findElement(By.xpath(_table));

        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement firstRow = rows.get(1);
        List<WebElement> columns = firstRow.findElements(By.tagName("td"));

        String actualDepartFrom = columns.get(0).getText();
        String actualArriveAt = columns.get(1).getText();
        String actualSeatType = columns.get(2).getText();
        String actualDepartDate = columns.get(3).getText();
        String actualAmount = columns.get(6).getText();

        return actualDepartFrom.equals(expectedDepartFrom) &&
                actualArriveAt.equals(expectedArriveAt) &&
                actualDepartDate.equals(expectedDepartDate) &&
                actualSeatType.equals(expectedSeatType) &&
                actualAmount.equals(String.valueOf(expectedAmount));
    }

    public boolean areDepartAndArriveValuesCorrect(String expectedDepartFrom, String expectedArriveAt) {
        WebElement departFromElement = Constant.WEBDRIVER.findElement(By.xpath(_txtDepartFrom));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", departFromElement);

        WebElement arriveAtElement = Constant.WEBDRIVER.findElement(By.xpath(_txtArriveAt));
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", arriveAtElement);

        String actualDepartFrom = new Select(Constant.WEBDRIVER.findElement(By.xpath(_txtDepartFrom))).getFirstSelectedOption().getText();
        String actualArriveAt = new Select(Constant.WEBDRIVER.findElement(By.xpath(_txtArriveAt))).getFirstSelectedOption().getText();

        return actualDepartFrom.equals(expectedDepartFrom) && actualArriveAt.equals(expectedArriveAt);
    }

}
