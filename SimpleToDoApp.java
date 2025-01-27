import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleToDoApp {

    // Task class to hold task details
    static class Task {
        String title;
        boolean completed;

        Task(String title) {
            this.title = title;
            this.completed = false;
        }

        void markAsCompleted() {
            completed = true;
        }

        @Override
        public String toString() {
            return title + (completed ? " [Completed]" : " [Pending]");
        }
    }

    // List to store tasks
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    // Add a task
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    tasks.add(new Task(title));
                    System.out.println("Task added.");
                    break;

                case 2:
                    // View tasks
                    System.out.println("\nTasks List:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    break;

                case 3:
                    // Mark task as completed
                    System.out.print("Enter task number to mark as completed: ");
                    int taskToComplete = scanner.nextInt();
                    if (taskToComplete > 0 && taskToComplete <= tasks.size()) {
                        tasks.get(taskToComplete - 1).markAsCompleted();
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 4:
                    // Remove a task
                    System.out.print("Enter task number to remove: ");
                    int taskToRemove = scanner.nextInt();
                    if (taskToRemove > 0 && taskToRemove <= tasks.size()) {
                        tasks.remove(taskToRemove - 1);
                        System.out.println("Task removed.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
