package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class RegisterPage extends GeneralPage{
    // Locators
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPid = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
    private final By _lblPasswordErrorMsg = By.xpath("//label[@class='validation-error' and text()='Invalid password length']");

    private final By _lblPidErrorMsg = By.xpath("//label[@class='validation-error' and text()='Invalid ID length']");
    private final By _lblFormErrorMsg = By.xpath("//p[@class='message error']");
    private final By _lblConfirmPswErrorMsg = By.xpath("//label[@class='validation-error']");

    // Elements
    public WebElement getTxtEmail()
    {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    public WebElement getTxtPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getTxtConfirmPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    public WebElement getTxtPid()
    {
        return Constant.WEBDRIVER.findElement(_txtPid);
    }
    public WebElement getBtnRegister()
    {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }


    public WebElement getLblRegisterErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMsg);
    }
    public WebElement getLblPasswordErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_lblPasswordErrorMsg);
    }
    public WebElement getLblPidErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_lblPidErrorMsg);
    }
    public WebElement getLblFormErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblFormErrorMsg);
    }
    public String getFormErrorMessage() {
        return getLblFormErrorMsg().getText();
    }
    public WebElement getLblConfirmPswErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblConfirmPswErrorMsg);
    }
    // Methods
    public HomePage Register(String email, String password, String confirmPassword, String pid)
    {
        //Submit login credentials
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getTxtPid().sendKeys(pid);
        scrollToElement(getBtnRegister());
        this.getBtnRegister().click();

        //Land on Home page
        return new HomePage();
    }
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public String getConfirmPswErrorMessage() {
        return getLblConfirmPswErrorMsg().getText();
    }
}
