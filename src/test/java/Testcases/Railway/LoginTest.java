package Testcases.Railway;
import Common.Utilities;
import Constant.Constant;
import PageObjects.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LoginTest {
    @BeforeMethod
    public void beforeMethod(){
        System.out.print("Pre-condition");
        System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath()+"\\Executables\\chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public  void afterMethod(){
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC01(){
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg , expectedMsg, "Welcome message is not displayed as expected");
    }
    @Test
    public void TC02(){
        System.out.println("TC02 - User can't login with blank 'Username' textbox");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login("", Constant.PASSWORD);

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();

        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC03(){
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, "invalidPassword");

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        System.out.println("ActualError Message: "+actualErrorMsg);
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        System.out.println("Expected Error Message: "+expectedErrorMsg);

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC04(){
        System.out.println("TCO4 - Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickBookTicketTab();
        Assert.assertTrue(homePage.isLoginPageDisplayed(), "Login page is not displayed when un-logged User clicks on 'Book ticket' tab");

    }
    @Test
    public void TC05(){
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "invalidPassword");
        }

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        System.out.println("Actual Error Message: "+actualErrorMsg);
        String expectedErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        System.out.println("Expected Error Message: "+ expectedErrorMsg);
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC06(){
        System.out.println("TC06 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed once user logged in");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed once user logged in");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed once user logged in");

        homePage.clickMyTicketTab();
        Assert.assertTrue(homePage.isOnMyTicketPage(), "User is not redirected to My ticket page");

        homePage.clickChangePasswordTab();
        Assert.assertTrue(homePage.isOnChangePasswordPage(), "User is not redirected to Change password page");
    }
    @Test
    public void TC07(){
        System.out.println("TC07 - User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();


        RegisterPage registerPage = homePage.gotoRegisterPage();

        String actualMsg = registerPage.Register("Zonesix0108@gmail.com", "tramne123456", "tramne123456", "1234345666667").getWelcomeRegisterMessage();

        String expectedMsg="Thank you for registering your account";

        Assert.assertEquals(actualMsg , expectedMsg, "Welcome message is not displayed as expected");

    }
    @Test
    public void TC08(){
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("mthd@gmail.com", "password00000");

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();

        String expectedErrorMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

    }
    @Test
    public void TC09(){
        System.out.println("TCO9 - User can change password");


        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(Constant.PASSWORD, "123456789", "123456789");

        String actualMsg = changePasswordPage.getSuccessMessage();

        String expectedMsg =  "Your password has been updated";
        Assert.assertEquals(actualMsg , expectedMsg, "Success message is not displayed as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.Register("tramle03@gmail.com", "tramne1234567", "tramle", "123434566666");
        String actualErrorMsg = registerPage.getLblRegisterErrorMsg().getText();
        System.out.println("Actual Error Message: "+actualErrorMsg);
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
        System.out.println("Epected Error Message: "+expectedErrorMsg);
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC11(){
        System.out.println("TC11 - User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();


        RegisterPage registerPage = homePage.gotoRegisterPage();

        registerPage.Register("diemhi@gmail.com", "", "", "");
        String actualErrorMsg = registerPage.getLblRegisterErrorMsg().getText();
        String actualErrorMsg1 = registerPage.getLblPasswordErrorMsg().getText();
        String actualErrorMsg2 = registerPage.getLblPidErrorMsg().getText();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
        String expectedErrorMsg1 = "Invalid password length.";
        String expectedErrorMsg2 = "Invalid ID length.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
        Assert.assertEquals(actualErrorMsg1, expectedErrorMsg1, "Error message is not displayed as expected");
        Assert.assertEquals(actualErrorMsg2, expectedErrorMsg2, "Error message is not displayed as expected");

    }
    @Test
    public void TC12(){
        System.out.println("TC12 - Errors display when password reset token is blank");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTabForgotPsw();
        ForgotPswPage forgotPswPage =loginPage.gotoForgotPswPage();
        forgotPswPage.forgotPassword(Constant.USERNAME);
    }
    @Test
    public void TC13(){
        System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTabForgotPsw();
        ForgotPswPage forgotPswPage =loginPage.gotoForgotPswPage();

    }

    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "123456789");
        BookTicketPage ticketPage = loginPage.gotoBookTicketPage();
        String nextDepartDateString = Utilities.calculateNextDepartDate(4);
        String departFrom ="Sài Gòn";
        String arriveAt = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";
        String ticketID = ticketPage.bookTicket(nextDepartDateString, departFrom,arriveAt,seatType,ticketAmount);

        Assert.assertTrue(ticketPage.comparedTicket(departFrom,arriveAt,seatType, nextDepartDateString,ticketAmount), "Ticket details do not match.");
    }
    @Test
    public void TC15(){
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in Train 'timetable' page");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "123456789");

        TimetablePage timetablePage = homePage.gotoTimetablePage();
        String depart = "Huế";
        String arrive = "Sài Gòn";

        timetablePage.clickBookTicketLink(depart, arrive);


        BookTicketPage bookTicketPage = new BookTicketPage();

        Assert.assertEquals(bookTicketPage.getDepartFromValue(), depart,"Incorrect depart station");
        Assert.assertEquals(bookTicketPage.getArriveAtValue(), arrive,"Incorrect arrive station");

    }
    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "123456789");

        MyticketPage myticketPage = homePage.gotoMyticketPage();
        String departStation = "Sài Gòn";
        String arriveStation = "Nha Trang";
        String seatType = "Soft bed with air conditioner";
        String departDate = "4/5/2024";

        int numberOfTicketsBeforeCancellation = myticketPage.getNumberOfTickets(departStation,arriveStation,seatType,departDate);
        if (numberOfTicketsBeforeCancellation == 0) {
            System.out.println("No tickets found with provided information.");
            return; // Kết thúc chạy test case
        }
        // Hủy vé ở đây
        myticketPage.cancelTicket(departStation,arriveStation,seatType,departDate);

        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();

        int numberOfTicketsAfterCancellation = myticketPage.getNumberOfTickets(departStation,arriveStation,seatType,departDate);

        if (numberOfTicketsBeforeCancellation == 1) {
            // Trường hợp chỉ có 1 vé, kiểm tra xem vé có còn hiển thị sau khi hủy hay không
            Assert.assertFalse(myticketPage.isTicketDisplayed(departStation,arriveStation,seatType,departDate));
        } else {
            // Trường hợp có nhiều vé, kiểm tra xem số lượng vé sau khi hủy có giảm đi 1 so với trước khi hủy hay không
            Assert.assertEquals(numberOfTicketsBeforeCancellation - 1, numberOfTicketsAfterCancellation);
        }

    }

}
