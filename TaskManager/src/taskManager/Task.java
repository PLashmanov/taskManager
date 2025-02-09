package taskManager;

import java.io.Serializable;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String dueDate;
    private boolean isCompleted;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + title + " (Due: " + dueDate + ")";
    }

    public static void saveTasks(List<Task> tasks, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        }
    }

    public static List<Task> loadTasks(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Task>) ois.readObject();
        }
    }
}
