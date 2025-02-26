package pageObjects;
import Common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class HomePage extends GeneralPage{
    public HomePage open()
    {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }
}
