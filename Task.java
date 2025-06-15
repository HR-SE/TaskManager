public  class Task {
    private int id;
    private String title;
    private String status;
    public Task(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public String getDetails() {
        return "ID: " + id + ", Title: " + title + ", Status: " + status ;
    }
}