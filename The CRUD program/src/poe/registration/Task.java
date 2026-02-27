
package poe.registration;

public final class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerFirstName;
    private String developerLastName;
    private int taskDuration;  // Task duration in hours
    private String taskStatus;
    private String taskID;

    // Constructor
    public Task(String taskName, int taskNumber, String taskDescription, 
                String developerFirstName, String developerLastName, 
                int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        
        if (checkTaskDescription(taskDescription)) {
            this.taskDescription = taskDescription;
        } else {
            throw new IllegalArgumentException("Task description should be less than 50 characters.");
        }
        
        this.taskID = createTaskID();
    }

    // Method to check if task description is valid (<= 50 characters)
    public boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        String taskNamePart = taskName.substring(0, 2).toUpperCase();
        String lastNamePart = developerLastName.length() >= 3 ? developerLastName.substring(developerLastName.length() - 3).toUpperCase() : developerLastName.toUpperCase();
        return taskNamePart + ":" + taskNumber + ":" + lastNamePart;
    }

    // Method to return task details as a formatted string
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n"
                + "Developer Details: " + developerFirstName + " " + developerLastName + "\n"
                + "Task Number: " + taskNumber + "\n"
                + "Task Name: " + taskName + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Task ID: " + taskID + "\n"
                + "Duration: " + taskDuration + " hours";
    }

    // Method to return duration of task
    public int getTaskDuration() {
        return taskDuration;
    }
}

