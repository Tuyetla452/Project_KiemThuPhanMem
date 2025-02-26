package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;

public class LogOutPage {

    public HomePage logout() {
        Constant.WEBDRIVER.findElement(By.linkText("Log out")).click();
        return new HomePage();
    }
}
