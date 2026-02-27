package poe.registration;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Login {
    private Registration registration;
    private int taskCounter = 0;
    private int totalTaskDuration = 0;
    private ArrayList<String> developerNames = new ArrayList<>();
    private ArrayList<String> taskNames = new ArrayList<>();
    private ArrayList<String> taskIDs = new ArrayList<>();
    private ArrayList<Integer> taskDurations = new ArrayList<>();
    private ArrayList<String> taskStatuses = new ArrayList<>();

    // Constructor to pass Registration object
    public Login(Registration registration) {
        this.registration = registration;
    }

    public static void main(String[] args) {
        Registration reg = new Registration("user123", "Password@123", "John", "Doe");
        Login login = new Login(reg);
        login.returnLoginStatus();
    }

    // Method to check username validity
    public boolean checkUserName() {
        String userName = registration.getUserName();
        return userName.contains("_") && userName.length() <= 5;
    }

    // Method to check password complexity
    public boolean checkPasswordComplexity() {
        String password = registration.getPassword();
        boolean length = password.length() >= 8;
        boolean number = false;
        boolean specialCharacter = false;
        boolean upperCase = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                number = true;
            }
            if (Character.isUpperCase(ch)) {
                upperCase = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                specialCharacter = true;
            }
        }

        return length && number && specialCharacter && upperCase;
    }

    // Method to register the user
    public void registerUser() {
        if (checkUserName()) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.");
        }

        if (checkPasswordComplexity()) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure the password contains at least 8 characters, a capital letter, a number, and a special character.");
        }
    }

    // Method to login the user
    public boolean loginUser() {
        String username2 = JOptionPane.showInputDialog("Enter the username you used to create an account:");
        String password2 = JOptionPane.showInputDialog("Enter the password you used to create an account:");

        return registration.getUserName().equals(username2) && registration.getPassword().equals(password2);
    }

    // Method to return login status
    public String returnLoginStatus() {
        if (loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

            boolean running = true;

            while (running) {
                String menu = """
                        Please choose an option:
                        1) Add tasks
                        2) Show report
                        3) Quit
                        4) Search task by name
                        5) Search tasks by developer
                        6) Delete a task
                        7) Display full report
                        """;
                String choiceStr = JOptionPane.showInputDialog(menu);
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1 -> {
                        String taskCountStr = JOptionPane.showInputDialog("How many tasks would you like to add?");
                        int numberOfTasks = Integer.parseInt(taskCountStr);
                        addTasks(numberOfTasks);
                    }
                    case 2 -> JOptionPane.showMessageDialog(null, "Which report would you like to see?");
                    case 3 -> {
                        running = false;
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                    }
                    case 4 -> {
                        String searchName = JOptionPane.showInputDialog("Enter Task Name to search:");
                        searchTaskByName(searchName);
                    }
                    case 5 -> {
                        String developerName = JOptionPane.showInputDialog("Enter Developer Name to search:");
                        searchTasksByDeveloper(developerName);
                    }
                    case 6 -> {
                        String taskName = JOptionPane.showInputDialog("Enter Task Name to delete:");
                        deleteTaskByName(taskName);
                    }
                    case 7 -> displayAllTasksReport();
                    default -> JOptionPane.showMessageDialog(null, "Invalid option, please try again.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting...");
        }
        return "Thanks for using this program";
    }

    public void addTasks(int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name"); 
            String taskDescription;
            while (true) {
                taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                if (taskDescription.length() <= 50) {
                    JOptionPane.showMessageDialog(null, "Task successfully captured.");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                }
            }

            String firstName = JOptionPane.showInputDialog("Enter developer's first name:");
            String lastName = JOptionPane.showInputDialog("Enter developer's last name:");
            String fullName = firstName + " " + lastName;

            String durationStr = JOptionPane.showInputDialog("Enter task duration (in hours):");
            int taskDuration = Integer.parseInt(durationStr);

            totalTaskDuration += taskDuration;

            String[] options = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String taskID = generateTaskID(taskName, taskCounter, lastName);

            taskNames.add(taskName);
            developerNames.add(fullName);
            taskIDs.add(taskID);
            taskDurations.add(taskDuration);
            taskStatuses.add(taskStatus);

            JOptionPane.showMessageDialog(null, "Task Added Successfully\nTask ID: " + taskID);
            taskCounter++;
        }
    }

    private String generateTaskID(String taskName, int taskNumber, String lastName) {
        String taskNamePart = taskName.substring(0, 2).toUpperCase();
        String lastNamePart = lastName.length() >= 3 ? lastName.substring(lastName.length() - 3).toUpperCase() : lastName.toUpperCase();
        return taskNamePart + ":" + taskNumber + ":" + lastNamePart;
    }

    public void searchTaskByName(String searchName) {
        StringBuilder result = new StringBuilder("Tasks matching name '" + searchName + "':\n");
        boolean found = false;
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(searchName)) {
                result.append("Developer: ").append(developerNames.get(i))
                        .append("\nTask Status: ").append(taskStatuses.get(i)).append("\n");
                found = true;
            }
        }
        if (!found) result.append("No tasks found.");
        JOptionPane.showMessageDialog(null, result.toString());
    }
            //Method to search for task infor using developer
    public void searchTasksByDeveloper(String developerName) {
        StringBuilder result = new StringBuilder("Tasks assigned to '" + developerName + "':\n");
        boolean found = false;
        for (int i = 0; i < developerNames.size(); i++) {
            if (developerNames.get(i).equalsIgnoreCase(developerName)) {
                result.append("Task Name: ").append(taskNames.get(i))
                        .append("\nTask Status: ").append(taskStatuses.get(i)).append("\n");
                found = true;
            }
        }
        if (!found) result.append("No tasks found.");
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void deleteTaskByName(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                taskNames.remove(i);
                developerNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task with name '" + taskName + "' not found.");
    }

    public void displayAllTasksReport() {
        StringBuilder report = new StringBuilder("Full Task Report:\n");
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("-------------------------------\n")
                  .append("Task Name: ").append(taskNames.get(i)).append("\n")
                  .append("Developer: ").append(developerNames.get(i)).append("\n")
                  .append("Task ID: ").append(taskIDs.get(i)).append("\n")
                  .append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n")
                  .append("Task Status: ").append(taskStatuses.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }
}
