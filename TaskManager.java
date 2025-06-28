//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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

   public void addTask(int id, String title, String status, String category, String priority) {
       if (isTaskIdExists(id)) {
           System.out.println("Error: Task ID " + id + " already exists.");
           return;
       }
       if (title.trim().isEmpty()){
           System.out.println("Error: Task title cannot be empty.");
           return;
       }
       tasks.add(new Task(id, title, status, category, priority));
       System.out.println("Task added: " + title);
   }
   public void viewTasks() {
       if (tasks.isEmpty()) {
           System.out.println("No tasks available.");
       } else {
           Collections.sort(tasks);
           for (Task task : tasks) {
               System.out.println(task.getDetails());
           }
       }
   }
   public void viewTasksByCategory(String category) {
       boolean found = false;
       for (Task task : tasks) {
           if (task.getCategory().equalsIgnoreCase(category)) {
               System.out.println(task.getDetails());
               found = true;
           }
       }
       if (!found) {
           System.out.println("No tasks found in category: " + category);
       }
   }
   public void viewTasksByPriority(String priority) {
       boolean found = false;
       for (Task task : tasks) {
           if (task.getPriority().equalsIgnoreCase(priority)) {
               System.out.println(task.getDetails());
               found = true;
           }
       }
       if (!found) {
           System.out.println("No tasks found in priority: " + priority);
       }
   }

   public void exportTasks(String filename) {
       try (FileWriter writer = new FileWriter(filename)) {
           if (tasks.isEmpty()) {
               writer.write("No tasks to export.");
           } else {
               Collections.sort(tasks);
               for (Task task : tasks) {
                   writer.write(task.getDetails() + "\n");
               }
           }
           System.out.println("Tasks exported to " + filename);
       } catch (IOException e) {
           System.out.println("Error exporting tasks: " + e.getMessage());
       }
   }

   public void importTasks(String filename) {
       try {File file = new File(filename); // Use File object with provided name
       if (!file.exists()) {
           System.out.println("Error: File " + filename + " not found. Place it in the project root or provide full path.");
           return;
       }
       try (Scanner fileScanner = new Scanner(file))
        {
           while (fileScanner.hasNextLine()) {
               String line = fileScanner.nextLine().trim();// parse line into id, title, status, category, priority
               if (line.startsWith("ID: ")) {
                   String[] parts = line.split(", ");
                   if (parts.length == 5) {
                       String idPart = parts[0].replace("ID: ", "").trim();
                       int id = Integer.parseInt(idPart);
                       String title = parts[1].replace("Title: ", "").trim();
                       String status = parts[2].replace("Status: ", "").trim();
                       String category = parts[3].replace("Category: ", "").trim();
                       String priority = parts[4].replace("Priority: ", "").trim();
                       addTask(id, title, status, category, priority);
                   } else {
                       System.out.println("Skipping invalid line: " + line);
                   }
               }
           }
           System.out.println("Task imported from " + filename);}
       } catch (IOException e) {
           System.out.println("Error importing tasks: " + e.getMessage());
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
           System.out.println("2. View All Tasks");
           System.out.println("3. View Tasks by Category");
           System.out.println("4. View Tasks by Priority");
           System.out.println("5. Export Tasks");
           System.out.println("6. Import Tasks");
           System.out.println("7. Delete Task");
           System.out.println("8. Update Task Status");
           System.out.println("9. Exit");
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
                   System.out.println("Enter task priority (e.g., High, Medium, Low): ");
                   String priority = scanner.nextLine();
                   addTask(id, title, status, category, priority);
                   break;
               case 2:
                   viewTasks();
                   break;
               case 3:
                   System.out.println("Enter category to view (e.g., Work, Personal): ");
                   String viewCategory = scanner.nextLine();
                   viewTasksByCategory(viewCategory);
                   break;
               case 4:
                   System.out.println("Enter priority to view (e.g., High, Medium, Low): ");
                   String viewPriority = scanner.nextLine();
                   viewTasksByPriority(viewPriority);
                   break;
               case 5:
                   System.out.println("Enter filename to export (e.g., tasks.ext): ");
                   String filename = scanner.nextLine();
                   exportTasks(filename);
                   break;
               case 6:
                   System.out.println("Enter filename to import (e.g., tasks.txt) ");
                   String file = scanner.nextLine();
                   //file += ".txt";
                   importTasks(file);
                   break;
               case 7:
                   System.out.print("Enter task ID to delete: ");
                   try {
                       int deleteId = scanner.nextInt();
                       deleteTask(deleteId);
                   } catch (Exception e) {
                       System.out.println("Error: Invalid ID.");
                       scanner.nextLine();
                   }
                   break;
               case 8:
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
               case 9:
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