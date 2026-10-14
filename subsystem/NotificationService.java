package subsystem;
public class NotificationService {
    public void notifyCustomer(String email, String message) {
        System.out.println("Notification to " + email + ": " + message);
    }
}