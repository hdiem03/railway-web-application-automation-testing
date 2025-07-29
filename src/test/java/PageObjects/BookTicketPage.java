package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;

public class BookTicketPage extends GeneralPage {

    // Locators
    private final By selectDepartDate = By.xpath("//select[@name='Date']");
    private final By selectDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By selectArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By selectSeatType = By.xpath("//select[@name='SeatType']");
    private final By selectTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");

    // Method

    // Method to book ticket
    public String bookTicket(String departDate, String departFrom, String arriveAt, String seatType, String ticketAmount) {
        selectOption(selectDepartDate, departDate);
        selectOption(selectDepartFrom, departFrom);
        selectOption(selectArriveAt, arriveAt);
        selectOption(selectSeatType, seatType);
        selectOption(selectTicketAmount, ticketAmount);
        Constant.WEBDRIVER.findElement(btnBookTicket).click();
//        return getIDFromCurrentURL();
        // Lưu trữ ID của vé
        setTicketID(Constant.WEBDRIVER.getCurrentUrl());
        return getTicketID();
    }


    // Method to select option from dropdown
    private void selectOption(By locator, String option) {
        Select dropdown = new Select(Constant.WEBDRIVER.findElement(locator));
        dropdown.selectByVisibleText(option);
    }

     private String getIDFromUrl(String currentUrl) throws MalformedURLException {
         URL url = new URL(currentUrl);
         String query = url.getQuery();
         String[] params = query.split("&");
         for (String param : params) {
             String[] keyValue = param.split("=");
             if (keyValue.length == 4 && keyValue[0].equals("id")) {
                 return keyValue[1];
             }
         }
         return null;
     }


    public String getDepartFromValue() {
        Select select = new Select(Constant.WEBDRIVER.findElement(selectDepartFrom));
        return select.getFirstSelectedOption().getText();
    }
    public String getArriveAtValue() {
        Select select = new Select(Constant.WEBDRIVER.findElement(selectArriveAt));
        return select.getFirstSelectedOption().getText();
    }


    // Method to get book ticket success message
    public String getBookTicketSuccessMessage() {
        WebElement successMessageElement = Constant.WEBDRIVER.findElement(By.xpath("//h1[@align='center']"));
        return successMessageElement.getText();
    }

    // So sánh thông tin
    public boolean comparedTicket(String departStation, String arriveStation, String seatType, String departDate, String amount) {
        WebElement table = Constant.WEBDRIVER.findElement(By.xpath("//table[@class='MyTable WideTable']"));
        WebElement row = table.findElement(By.xpath("//tr[@class='OddRow']")); // Assuming there's only one row for simplicity

        String actualDepartStation = row.findElement(By.xpath("//td[1]")).getText();
        String actualArriveStation = row.findElement(By.xpath("//td[2]")).getText();
        String actualSeatType = row.findElement(By.xpath("//td[3]")).getText();
        String actualDepartDate = row.findElement(By.xpath("//td[4]")).getText();
        String actualAmount = row.findElement(By.xpath("//td[7]")).getText();
        // Assuming ID is in the first column
        String idInRow = row.findElement(By.xpath("//td[1]")).getText();
        return actualDepartStation.equals(departStation)
                && actualArriveStation.equals(arriveStation)
                && actualSeatType.equals(seatType)
                && actualDepartDate.equals(departDate)
                && actualAmount.equals(amount)
                && idInRow.equals(ticketID);
    }
    // Lưu trữ ID của vé
    private String ticketID;

    // Phương thức để lấy ID từ URL
    public void setTicketID(String currentUrl) {
        try {
            ticketID = getIDFromUrl(currentUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy ID của vé
    public String getTicketID() {
        return ticketID;
    }

}
