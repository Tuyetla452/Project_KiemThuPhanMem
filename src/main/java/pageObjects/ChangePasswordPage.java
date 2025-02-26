package pageObjects;

import Common.Constant;
import org.openqa.selenium.By;

public class ChangePasswordPage {
    private final By _txtCurrentPassword = By.id("currentPassword");
    private final By _txtNewPassword = By.id("newPassword");
    private final By _txtConfirmPassword = By.id("confirmPassword");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblSuccessMessage = By.cssSelector(".message");

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        Constant.WEBDRIVER.findElement(_txtCurrentPassword).sendKeys(currentPassword);
        Constant.WEBDRIVER.findElement(_txtNewPassword).sendKeys(newPassword);
        Constant.WEBDRIVER.findElement(_txtConfirmPassword).sendKeys(confirmPassword);
        Constant.WEBDRIVER.findElement(_btnChangePassword).click();
    }

    public String getSuccessMessage() {

        return Constant.WEBDRIVER.findElement(_lblSuccessMessage).getText();
    }

    public boolean isPageDisplayed() {

        return Constant.WEBDRIVER.findElement(_txtCurrentPassword).isDisplayed();
    }
}
