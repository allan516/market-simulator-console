package helper;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat nf = new DecimalFormat(
            "R$ #,##0.00",new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String dateToString(Date date) {
        return Utils.sdf.format(date);
    }

    public static String doubleToString(Double value) {
        return Utils.nf.format(value);
    }

    public static Double stringToDouble(String value) {
        try {
            return (Double)Utils.nf.parse(value);
        } catch(ParseException e) {
            return null;
        }
    }

    public static void stop(int seconds) {
        try {
            TimeUnit.SECONDS.sleep((seconds));
        } catch(InterruptedException e) {
            System.out.println("Erro ao pausar por " + seconds + " segundos. ");
        }
    }
}
