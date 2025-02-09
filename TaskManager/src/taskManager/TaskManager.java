package taskManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private static final String FILE_NAME = "tasks.dat";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try {
            tasks = Task.loadTasks(FILE_NAME);
        } catch (Exception e) {
            System.out.println("No previous tasks found.");
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    tasks.add(new Task(title, dueDate));
                    break;
                case 2:
                    System.out.println("\nTasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Enter task number to mark as completed: ");
                    int taskNum = scanner.nextInt();
                    if (taskNum > 0 && taskNum <= tasks.size()) {
                        tasks.get(taskNum - 1).markCompleted();
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 4:
                    try {
                        Task.saveTasks(tasks, FILE_NAME);
                    } catch (IOException e) {
                        System.out.println("Error saving tasks.");
                    }
                    System.out.println("Exiting...\n");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

