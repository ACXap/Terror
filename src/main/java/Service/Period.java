package Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Period {

    //region PrivateField
    private final LocalDateTime _startDate = LocalDateTime.now();
    private LocalDateTime _stopDate;
    //endregion PrivateField

    //region PublicMethod
    public String GetStartDate() {
        return FormatDate(_startDate);
    }

    public String GetStopDate(){
        _stopDate = LocalDateTime.now();
        return  FormatDate(_stopDate);
    }

    public String GetPeriod(){
        Duration dur = Duration.between(_startDate, _stopDate);
        return  GetTimeString(dur);
    }
    //endregion PublicMethod

    //region PrivateMethod
    private static String GetTimeString(Duration dur) {
        long hours = dur.toHours();
        long minutes = dur.minusHours(hours).toMinutes();
        long seconds = dur.minusHours(hours).minusMinutes(minutes).toMillis() / 1000;
        long mills = dur.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillis();
        return String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, mills);
    }

    private static String FormatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
    //endregion PrivateMethod
}