package testcases;

import Common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

public class BookTicketTests {

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
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage =GeneralPage.gotoBookTicketPage1();

        bookTicketPage.bookTicket(4, "Sài Gòn", "Nha Trang", "Soft bed with air conditioner", 1);

        String actualSuccessMessage = bookTicketPage.getHeaderSuccessMessage();
        String expectedSuccessMessage = "Ticket Booked Successfully!";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);

        Assert.assertTrue(bookTicketPage.checkInfBookTicket(4, "Sài Gòn", "Nha Trang", "Soft bed with air conditioner", 1),
                "The booking details do not match the expected values.");
    }

    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TimetablePage timetablePage = homePage.gotoTimetablePage();

        timetablePage.clickBookTicket("Sài Gòn", "Huế");
        BookTicketPage bookTicketPage = new BookTicketPage();
        Assert.assertTrue(bookTicketPage.areDepartAndArriveValuesCorrect("Sài Gòn", "Huế"),
                "The 'Depart From' and 'Arrive At' values do not match the expected ones.");
    }

    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = LoginPage.gotoBookTicketPage1();
        String ticketId = bookTicketPage.bookTicket(4, "Sài Gòn", "Huế", "Soft bed", 1);

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        myTicketPage.cancelTicket(ticketId);

        assert myTicketPage.isTicketCancelled(ticketId) : "Ticket cancellation failed!";
    }

}
