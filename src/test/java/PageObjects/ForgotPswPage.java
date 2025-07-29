package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPswPage {
    private final By _txtEmail_Address = By.xpath("//input[@id='email']");
    private final By _btnSend_Instructions = By.xpath("//input[@value='Send Instructions']");
    private final By _lblForgotPswErrorMsg = By.xpath("//[@class='validation-error']");
    //Elements
    public WebElement getTxtEmail_Address() {
        return Constant.WEBDRIVER.findElement(_txtEmail_Address);
    }

    public WebElement getBtnSend_Instructions() {
        return Constant.WEBDRIVER.findElement(_btnSend_Instructions);
    }
    public WebElement getLblForgotPswErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblForgotPswErrorMsg);
    }
    //Method
    public LoginPage forgotPassword(String email) {
        //Submit registration credentials
        this.getTxtEmail_Address().sendKeys(email);
//        this.getBtnSend_Instructions().click();
        //Land on Home Page
        return new LoginPage();
    }
    public String getForgotPswErrorMessage() {
        return getLblForgotPswErrorMsg().getText();
    }

}
