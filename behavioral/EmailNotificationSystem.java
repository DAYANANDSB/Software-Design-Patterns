import java.util.ArrayList;
import java.util.List;

// Observer interface for receiving updates
interface Observer {
    void update(String message, String type);
}

// Managing email notifications
class EmailClient {
    private List<Observer> observers = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message, String type) {
        for (Observer observer : observers) {
            observer.update(message, type);
        }
        notifications.add(new Notification(message, type, "unread"));
    }

    public void receiveEmail(String email) {
        String message = "New email received: " + email;
        System.out.println(message);
        notifyObservers(message, "New Email");
    }

    public void emailReadReceipt(String email) {
        String message = "Email read receipt: " + email;
        System.out.println(message);
        notifyObservers(message, "Read Receipt");
    }

    public void printNotifications() {
        System.out.println("All Notifications:");
        for (Notification notification : notifications) {
            System.out.println(notification);
        }
    }

    public void markNotificationAsRead(String message) {
        for (Notification notification : notifications) {
            if (notification.getMessage().equals(message) && notification.getStatus().equals("unread")) {
                notification.setStatus("read");
                System.out.println("Marked as read: " + message);
                return;
            }
        }
        System.out.println("Notification not found or already read.");
    }
}

// User receiving notifications
class User implements Observer {
    private String name;
    private List<String> notificationTypes;

    public User(String name, List<String> notificationTypes) {
        this.name = name;
        this.notificationTypes = notificationTypes;
    }

    @Override
    public void update(String message, String type) {
        if (notificationTypes.contains(type)) {
            System.out.println(name + " received notification: " + message);
        }
    }

    public String getName() {
        return name;
    }

    public void setNotificationTypes(List<String> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
}

//  storing message details
class Notification {
    private String message;
    private String type;
    private String status;

    public Notification(String message, String type, String status) {
        this.message = message;
        this.type = type;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notification [Message: " + message + ", Type: " + type + ", Status: " + status + "]";
    }
}

public class EmailNotificationSystem {
    public static void main(String[] args) {
        EmailClient emailClient = new EmailClient();

        // Define notification types for users
        List<String> aliceTypes = new ArrayList<>();
        aliceTypes.add("New Email");
        User alice = new User("Alice", aliceTypes);

        List<String> bobTypes = new ArrayList<>();
        bobTypes.add("Read Receipt");
        bobTypes.add("New Email");
        User bob = new User("Bob", bobTypes);

        // Subscribe users
        emailClient.attach(alice);
        emailClient.attach(bob);

        emailClient.receiveEmail("Meeting at 10 AM");
        emailClient.emailReadReceipt("Meeting at 10 AM");

        // Display notifications
        emailClient.printNotifications();

        // Mark one notification as read
        emailClient.markNotificationAsRead("New email received: Meeting at 10 AM");

        // Display notifications showing status change
        emailClient.printNotifications();
    }
}
