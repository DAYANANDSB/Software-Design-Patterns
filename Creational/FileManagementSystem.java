import java.util.Date;

// Singleton class for managing files
class FileSystemManager {
    private static FileSystemManager instance;
    private static final String FILE_PATH = "/path/to/files/";

    private FileSystemManager() {
        // Prevent instantiation from outside
    }

    public static synchronized FileSystemManager getInstance() {
        if (instance == null) {
            instance = new FileSystemManager();
        }
        return instance;
    }

    public void readFile(String fileName) {
        System.out.println("Reading file: " + FILE_PATH + fileName);
    }

    public void writeFile(String fileName, String content) {
        System.out.println("Writing to file: " + FILE_PATH + fileName);
    }

    public void deleteFile(String fileName) {
        System.out.println("Deleting file: " + FILE_PATH + fileName);
    }

    public void displayFileMetadata(String fileName) {
        System.out.println("Metadata for file: " + FILE_PATH + fileName);
        System.out.println("File Size: 1MB"); 
        System.out.println("Last Modified: " + new Date());
    }

    public void trackFileHistory(String fileName) {
        System.out.println("History for file: " + FILE_PATH + fileName);
        System.out.println("Version 1.0 - Created");
        System.out.println("Version 1.1 - Updated content");
        System.out.println("Version 1.2 - Fixed errors");
    }
}

// Class representing a user
class User {
    private String name;
    private boolean canDelete;

    public User(String name, boolean canDelete) {
        this.name = name;
        this.canDelete = canDelete;
    }

    public String getName() {
        return name;
    }

    public boolean canDelete() {
        return canDelete;
    }
}

public class FileManagementSystem {
    public static void main(String[] args) {
        FileSystemManager fileManager = FileSystemManager.getInstance();

        User admin = new User("Admin", true);
        User guest = new User("Guest", false);

        String fileName = "example.txt";

        // Admin operations
        if (admin.canDelete()) {
            fileManager.deleteFile(fileName);
        }
        fileManager.readFile(fileName);
        fileManager.writeFile(fileName, "Sample content");
        fileManager.displayFileMetadata(fileName);
        fileManager.trackFileHistory(fileName);

        // Guest operations
        if (guest.canDelete()) {
            fileManager.deleteFile(fileName);
        } else {
            System.out.println(guest.getName() + " does not have permission to delete files.");
        }
        fileManager.readFile(fileName);
        fileManager.writeFile(fileName, "Sample content");
        fileManager.displayFileMetadata(fileName);
        fileManager.trackFileHistory(fileName);
    }
}
