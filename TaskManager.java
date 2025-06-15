//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;

public class TaskManager {
   private ArrayList<Task> tasks = new ArrayList<>();
   public void addTask(int id, String title, String status) {
       tasks.add(new Task(id, title, status));
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
   public static void main(String[] args) {
       TaskManager manager = new TaskManager();
       manager.addTask(1, "Learn Java", "In Progress");
       manager.addTask(2, "Solve HackerRank", "Done");
       manager.viewTasks();
       manager.deleteTask(1);
       manager.viewTasks();
   }
}