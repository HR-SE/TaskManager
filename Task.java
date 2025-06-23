public class Task implements Comparable<Task> {
    private int id;
    private String title;
    private String status;
    private String category;
    private String priority; //new field
    public Task(int id, String title, String status, String category, String priority) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.category = category;
        this.priority = priority;
    }
    public int getId() {
        return id;
    }
    public String getCategory() { return category; }

    public String getPriority() { return priority; }

    public String getDetails() {
        return "ID: " + id + ", Title: " + title + ", Status: " + status + ", Category: " + category + ", Priority: " + priority;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Task other) {
        String[] priorityOrder = {"High", "Medium", "Low"};
        int thisIndex = indexOf(priorityOrder, this.priority);
        int otherIndex = indexOf(priorityOrder, other.priority);
        return Integer.compare(thisIndex, otherIndex);
    }

    private int indexOf(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(value)) return i;
        }
        return array.length; //Default to lowest if not found
    }
}