package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }
    public static String calculateNextDepartDate(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDepartDate = currentDate.plusDays(daysToAdd);
        if ((daysToAdd >=3) && (daysToAdd<=30))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            return nextDepartDate.format(formatter);
        }
        else
        {return null;}
    }
}