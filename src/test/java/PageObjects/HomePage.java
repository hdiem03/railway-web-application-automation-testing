package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends GeneralPage{
    // Locators
    // Elements
    // Methods
    // Locators for Book ticket tab
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
//    private final By loginPageHeader = By.xpath("//h1[@align='center']");
    private final By loginPageHeader = By.xpath("//h1[contains(text(), 'Login')]");

    private final By myTicketTab = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By changePasswordTab = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By logoutTab = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");

    // Method to click on Book ticket tab
    public void clickBookTicketTab() {
        Constant.WEBDRIVER.findElement(tabBookTicket).click();
    }

    public HomePage open()
    {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }
    // Method to navigate to Change Password page
    public ChangePasswordPage gotoChangePasswordPage() {
        Constant.WEBDRIVER.findElement(changePasswordTab).click();
        return new ChangePasswordPage();
    }
    // Method to check if the login page is displayed
    public boolean isLoginPageDisplayed() {
        return Constant.WEBDRIVER.findElement(loginPageHeader).isDisplayed();
    }

    // Method to check if My ticket tab is displayed
    public boolean isMyTicketTabDisplayed() {
        WebElement element = Constant.WEBDRIVER.findElement(myTicketTab);
        return element.isDisplayed();
    }

    // Method to check if Change password tab is displayed
    public boolean isChangePasswordTabDisplayed() {
        WebElement element = Constant.WEBDRIVER.findElement(changePasswordTab);
        return element.isDisplayed();
    }

    // Method to check if Logout tab is displayed
    public boolean isLogoutTabDisplayed() {
        WebElement element = Constant.WEBDRIVER.findElement(logoutTab);
        return element.isDisplayed();
    }

    // Method to click on My ticket tab
    public void clickMyTicketTab() {
        Constant.WEBDRIVER.findElement(myTicketTab).click();
    }

    // Method to click on Change password tab
    public void clickChangePasswordTab() {
        Constant.WEBDRIVER.findElement(changePasswordTab).click();
    }

    // Method to verify if user is on My ticket page
    public boolean isOnMyTicketPage() {
        // Implement code to verify if user is on My ticket page
        return true; // Example implementation, replace with actual implementation
    }

    // Method to verify if user is on Change password page
    public boolean isOnChangePasswordPage() {
        // Implement code to verify if user is on Change password page
        return true; // Example implementation, replace with actual implementation
    }
    // Locators
    private final By registerButton = By.id("registerButton"); // Giả sử nút "Register" có id là "registerButton"

    // Method to get the register button WebElement
    public WebElement getRegisterButton() {
        return Constant.WEBDRIVER.findElement(registerButton);
    }


}

