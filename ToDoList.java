import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ToDoList {
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. View Tasks");
            System.out.println("2. Add task");
            System.out.println("3. remove task");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask(scanner);
                    break;
                case 3:
                    removeTask(scanner);
                    break;
                case 4:
                    saveTasks();
                    System.out.println("Tasks saved. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again");
            }
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty!");
        } else {
            System.out.println("\nYour To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        tasks.add(task);
       System.out.println("Task added!");
    }

    private static void removeTask(Scanner scanner) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int taskNumber = scanner.nextInt();

        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task removed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task: tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error Saving Tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String task;
            while ((task = reader.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}