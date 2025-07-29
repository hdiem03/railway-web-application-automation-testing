package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabTimetable= By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");
    private final By tabMyTicket= By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");

//    private final By lblWelcomeMessage= By.xpath("//div[@class='account']/strong[text() = ' Welcome diem123@gmail.com']");

    private final By lblWelcomeMessage= By.xpath("//div[@class='account']/strong");

    private final By lblWelcomeRegisterMessage= By.xpath("//div[@id='content']");
//        private final By lblWelcomeMessage= ;

    // Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabLogout()
    {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getlblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    protected WebElement getlblWelcomeRegisterMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeRegisterMessage);

    }
    protected WebElement getTabBookTicket() {
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }

    protected WebElement getTabMyticket() {
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }
    protected WebElement getTabTimetable() {
        return Constant.WEBDRIVER.findElement(tabTimetable);
    }
    // Methods
    public String getWelcomeMessage()
    {
        return this.getlblWelcomeMessage().getText();
    }
    public String getWelcomeRegisterMessage()
    {
        return this.getlblWelcomeRegisterMessage().getText();
    }
    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public RegisterPage gotoRegisterPage()
    {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    public BookTicketPage gotoBookTicketPage()
    {
        this.getTabBookTicket().click();
        return new BookTicketPage();
    }
//    public void clickBookTicketTab() {
//        Constant.WEBDRIVER.findElement(tabBookTicket).click();
//    }

    public MyticketPage gotoMyticketPage()
    {
        this.getTabMyticket().click();
        return new MyticketPage();
    }
    public TimetablePage gotoTimetablePage()
    {
        this.getTabTimetable().click();
        return new TimetablePage();
    }


}
