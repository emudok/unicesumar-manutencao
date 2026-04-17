import java.util.Map;

public class NotificationService {

    //este método e o da linha 26 repetem lógica. ambos fazem
    //Map<String, Object> user = LegacyDatabase.getUserById(userId);
    //Map<String, Object> book = LegacyDatabase.getBookById(bookId);
    public void notifyLoanCreated(int userId, int bookId, String date, String dueDate, String channel, String template,
            String managerName) {
        Map<String, Object> user = LegacyDatabase.getUserById(userId);
        Map<String, Object> book = LegacyDatabase.getBookById(bookId);

        if (user != null && book != null) {
            String msg = "Loan created for user " + user.get("name") + " and book " + book.get("title") + " due " + dueDate;
            if ("sms".equals(channel)) {
                System.out.println("SMS: " + msg);
            } else if ("email".equals(channel)) {
                System.out.println("EMAIL: " + msg);
            } else {
                System.out.println("LOG: " + msg);
            }
            LegacyDatabase.addLog("notify-loan-" + userId + "-" + bookId);
        }
    }

    public void notifyReturn(int userId, int bookId, String status, double fine, String channel) {
        Map<String, Object> user = LegacyDatabase.getUserById(userId);
        Map<String, Object> book = LegacyDatabase.getBookById(bookId);

        if (user != null && book != null) {
            String msg = "Book returned: " + book.get("title") + " by " + user.get("name") + ", fine=" + fine;
            if ("sms".equals(channel)) {
                System.out.println("SMS: " + msg);
            } else {
                System.out.println("EMAIL: " + msg);
            }
            LegacyDatabase.addLog("notify-return-" + userId + "-" + bookId + "-" + status);
        }
    }

    //método mal nomeado e pouco claro (x, y, z) são ruins, não da para
    //entender baixa legibilidade e dificulta se um dia for necessário refatorá-lo.
    public void genericNotify(String x, String y, String z, int priority, int retry, String process) {
        if (priority > 5) {
            System.out.println("HIGH: " + x + " | " + y + " | " + z + " | " + process);
        } else {
            System.out.println("LOW: " + x + " | " + y + " | " + z + " | " + process);
        }
        if (retry > 3) {
            LegacyDatabase.addLog("notify-retry-high");
        } else {
            LegacyDatabase.addLog("notify-retry-low");
        }
    }

    public void sendDebtAlert(int userId, double value, int level, String manager) {
        Map<String, Object> user = LegacyDatabase.getUserById(userId);
        if (user != null) {
            if (level == 1) {
                System.out.println("Debt warning to " + user.get("name") + ": " + value);
            } else if (level == 2) {
                System.out.println("Debt urgent warning to " + user.get("name") + ": " + value);
            } else {
                System.out.println("Debt legal warning to " + user.get("name") + ": " + value);
            }
        }
        LegacyDatabase.addLog("notify-debt-" + userId + "-" + manager);
    }
}
