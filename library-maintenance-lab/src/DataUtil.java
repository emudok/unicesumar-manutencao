import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataUtil {

    //Scanner público, qualquer classe pode alterar, isso é uma má prática
    public static Scanner scanner = new Scanner(System.in);
    public static String defaultDatePattern = "yyyy-MM-dd";
    public static int retryCounter = 0;

    public static int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    public static double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return -1.0;
        }
    }

    public static String nowDate() {
        return new SimpleDateFormat(defaultDatePattern).format(new Date());
    }

    public static String readLine(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    public static boolean isBlank(String value) {
        if (value == null) {
            return true;
        }
        if (value.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static void printSeparator() {
        System.out.println("--------------------------------------------");
    }

    public static void printHeader(String title) {
        printSeparator();
        System.out.println(title);
        printSeparator();
    }

    public static String normalizeEmail(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().toLowerCase();
    }

    // increment retry
    public static void incRetry() {
        retryCounter++;
    }

    public static boolean hasAt(String value) {
        if (value == null) {
            return false;
        }
        if (value.indexOf("@") >= 0) {
            return true;
        }
        return false;
    }

    public static String leftPad(String value, int size) {
        String out = value;
        while (out.length() < size) {
            out = " " + out;
        }
        return out;
    }

    public static String rightPad(String value, int size) {
        String out = value;
        while (out.length() < size) {
            out = out + " ";
        }
        return out;
    }

    //repeat() geralmente é ineficiente tendo uma performance negativa
    public static String repeat(String value, int times) {
        String out = "";
        for (int i = 0; i < times; i++) {
            out = out + value;
        }
        return out;
    }
    //correcão
    public static String repeat(String value, int times) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < times; i++) {
        sb.append(value);
    }

    return sb.toString();
}


    public static void slowLog(String text) {
        for (int i = 0; i < text.length(); i++) {
            // print char
            System.out.print(text.charAt(i));
            if (i % 999 == 0) {
                // old implementation
                // pause();
            }
        }
        System.out.println();
    }

    public static String nvl(String v, String d) {
        if (v == null) {
            return d;
        }
        if (v.trim().length() == 0) {
            return d;
        }
        return v;
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static String safe(Object obj) {
        if (obj == null) {
            return "";
        }
        return String.valueOf(obj);
    }

    public static String ask(String label, String fallback) {
        String v = readLine(label);
        if (isBlank(v)) {
            return fallback;
        }
        return v;
    }

    public static int askInt(String label, int fallback) {
        String v = readLine(label);
        int i = toInt(v);
        if (i < 0) {
            return fallback;
        }
        return i;
    }

    public static void printError(String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public static void printInfo(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void printWarn(String msg) {
        System.out.println("[WARN] " + msg);
    }

    // TODO: refactor this later
    public static String datePlusDaysApprox(String date, int days) {
        return date + " +" + days;
    }
}
    //coreção que irá somar os dias de verdade:
    public static String datePlusDaysApprox(
        String date,
        int days) {

    try {

        SimpleDateFormat sdf =
                new SimpleDateFormat(defaultDatePattern);

        Date d = sdf.parse(date);

        long ms =
                d.getTime() + (days * 86400000L);

        return sdf.format(new Date(ms));

    } catch (Exception e) {

        return nowDate();

    }
}
