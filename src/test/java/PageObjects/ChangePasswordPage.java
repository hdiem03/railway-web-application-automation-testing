package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends GeneralPage{
    // Locators
    private final By _txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    private final By _lblChangePasswordErrorMsg = By.xpath("//p[@class='message error']");
    // Locators for success message
    private final By successMessage = By.cssSelector("div[id='content'] p[class='message success']");
    // Elements
    public WebElement getTxtCurrentPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtCurrentPassword);
    }
    public WebElement getTxtNewPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    public WebElement getTxtConfirmPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    public WebElement getBtnChangePassword()
    {
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }


    public WebElement getLbChangePasswordErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_lblChangePasswordErrorMsg);
    }
    // Methods
    public void changePassword(String currentPassword, String newPassword, String confirmPassword)
    {
        //Submit login credentials
        this.getTxtCurrentPassword().sendKeys(currentPassword);
        this.getTxtNewPassword().sendKeys(newPassword);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getBtnChangePassword().click();

        //Land on Home page
//        return new HomePage();
    }
    // Method to get success message after changing password
    public String getSuccessMessage() {
        WebElement successMessageElement = Constant.WEBDRIVER.findElement(successMessage);
        return successMessageElement.getText();
    }

}
