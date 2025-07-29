package PageObjects;

import Constant.Constant;
import org.openqa.selenium.*;

import java.util.List;


public class MyticketPage extends GeneralPage{
    private final By _tabSomme = By.xpath("//a[contains(text(), 'Web hosting by Somee.com')]");
    private final By mytable = By.xpath("//table[@class='MyTable']");

    public void scrollDownToBookTicketButton() {
        WebElement TabSomme = Constant.WEBDRIVER.findElement(_tabSomme);

        // Scroll to the "Book Ticket" button
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", TabSomme);
    }


    public BookTicketPage cancelTicket(String depart, String arrive, String seatType, String departDate) {
        // Tìm bảng chứa thông tin về các chuyến tàu
        WebElement table = Constant.WEBDRIVER.findElement(mytable);

        // Tìm tất cả các hàng trong bảng
        List<WebElement> rows = table.findElements(By.xpath(".//tr[@class='OddRow' or @class='EvenRow']"));

        for (WebElement row : rows) {
            // Lấy giá trị của các cột trong từng hàng
            String departStation = row.findElement(By.xpath("./td[2]")).getText();
            String arriveStation = row.findElement(By.xpath("./td[3]")).getText();
            String seatTypeColumn = row.findElement(By.xpath("./td[4]")).getText();
            String departDateColumn = row.findElement(By.xpath("./td[5]")).getText();

            // Kiểm tra xem các giá trị có khớp với các tham số đầu vào hay không
            if (departStation.equals(depart) && arriveStation.equals(arrive)
                    && seatTypeColumn.equals(seatType) && departDateColumn.equals(departDate)) {
                // Kiểm tra loại nút
                WebElement cancelTicketButton = null;
                if (row.findElements(By.xpath("./td[11]/input[@value='Cancel']")).size() > 0) {
                    cancelTicketButton = row.findElement(By.xpath("./td[11]/input[@value='Cancel']"));
                } else if (row.findElements(By.xpath("./td[11]/input[@value='Delete']")).size() > 0) {
                    cancelTicketButton = row.findElement(By.xpath("./td[11]/input[@value='Delete']"));
                }

                // Nếu phù hợp, nhấn vào nút
                if (cancelTicketButton != null) {
                    ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", cancelTicketButton);
                    cancelTicketButton.click();
                }
                break; // Kết thúc vòng lặp sau khi đã tìm thấy nút
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }


    public boolean isTicketDisplayed(String departStation, String arriveStation, String seatType, String departDate) {
        String xpathExpression = String.format("//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",
                departStation, arriveStation, seatType, departDate);
        return Constant.WEBDRIVER.findElements(By.xpath(xpathExpression)).size() > 0;
    }

    public int getNumberOfTickets(String departStation, String arriveStation, String seatType, String departDate) {
        String xpathExpression = String.format("//table[@class='MyTable']//tr[.//td[text()='%s'] and .//td[text()='%s'] and .//td[text()='%s'] and .//td[text()='%s']]",
                departStation, arriveStation, seatType, departDate);
        return Constant.WEBDRIVER.findElements(By.xpath(xpathExpression)).size();
    }



}
