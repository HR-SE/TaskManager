//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
   private ArrayList<Task> tasks = new ArrayList<>();
   private Scanner scanner = new Scanner(System.in);
   public boolean isTaskIdExists(int id) { //New validation method
       for (Task task : tasks) {
           if (task.getId() == id) {
               return true;
           }
       }
       return false;
   }

   public void addTask(int id, String title, String status, String category) {
       if (isTaskIdExists(id)) {
           System.out.println("Error: Task ID " + id + " already exists.");
           return;
       }
       if (title.trim().isEmpty()){
           System.out.println("Error: Task title cannot be empty.");
           return;
       }
       tasks.add(new Task(id, title, status, category));
       System.out.println("Task added: " + title);
   }
   public void viewTasks() {
       if (tasks.isEmpty()) {
           System.out.println("No tasks available.");
       } else {
           for (Task task : tasks) {
               System.out.println(task.getDetails());
           }
       }
   }
   public void deleteTask(int id) {
       for (int i = 0; i < tasks.size(); i++) {
           if (tasks.get(i).getId() == id) {
               System.out.println("Task removed: " + tasks.get(i).getDetails());
               tasks.remove(i);
               return;
           }
       }
       System.out.println("Task ID " + id + " not found.");
   }
   public void updateTaskStatus(int id, String newStatus) {
       for (Task task : tasks) {
           if (task.getId() == id) {
               System.out.println("Updated task: " + task.getDetails());
               task.setStatus(newStatus);
               System.out.println("New status: " + task.getDetails());
               return;
           }
       }
       System.out.println("Task ID " + id + " not found.");
   }
   public void run () {
       while (true) {
           System.out.println("\nTask Manager Menu:");
           System.out.println("1. Add Task");
           System.out.println("2. View Tasks");
           System.out.println("3. Delete Task");
           System.out.println("4. Update Task Status");
           System.out.println("5. Exit");
           System.out.print("Choose an option: ");
           int choice;
           try {
               choice = scanner.nextInt();
               scanner.nextLine(); //Clear buffer
           } catch (Exception e) {
               System.out.println("Error: Please enter a valid number.");
               scanner.nextLine(); // Clear invalid input
               continue;
           }
           switch (choice) {
               case 1:
                   System.out.print("Enter task ID: ");
                   int id; //note2self: variable declared out of try block so it can be used outside try block in next lines
                   try {
                       id = scanner.nextInt();
                       scanner.nextLine();
                   } catch (Exception e) {
                       System.out.println("Error: Invalid ID.");
                       scanner.nextLine();
                       break;
                   }
                   System.out.print("Enter task title: ");
                   String title = scanner.nextLine();
                   System.out.print("Enter Task status: ");
                   String status = scanner.nextLine();
                   System.out.print("Enter task category (e.g., Work, Personal): ");
                   String category = scanner.nextLine();
                   addTask(id, title, status, category);
                   break;
               case 2:
                   viewTasks();
                   break;
               case 3:
                   System.out.print("Enter task ID to delete: ");
                   try {
                       int deleteId = scanner.nextInt();
                       deleteTask(deleteId);
                   } catch (Exception e) {
                       System.out.println("Error: Invalid ID.");
                       scanner.nextLine();
                   }
                   break;
               case 4:
                   System.out.print("Enter task ID to update: ");
                   try {
                       int updateId = scanner.nextInt();
                       scanner.nextLine();
                       System.out.print("Enter new status: ");
                       String newStatus = scanner.nextLine();
                       updateTaskStatus(updateId, newStatus);
                   } catch (Exception e) {
                       System.out.println("Error: invalid ID.");
                       scanner.nextLine();
                       }
                       break;
               case 5:
                   System.out.println("Exiting Task Manager.");
                   return;
               default:
                   System.out.println("Invalid option. Try again.");
           }
       }
    }
   public static void main(String[] args) {
       TaskManager manager = new TaskManager();
       manager.run(); //starts interactive menu
   }
}