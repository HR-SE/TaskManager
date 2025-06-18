public  class Task {
    private int id;
    private String title;
    private String status;
    private String category; //new field
    public Task(int id, String title, String status, String category) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getDetails() {
        return "ID: " + id + ", Title: " + title + ", Status: " + status + ", Category: " + category;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}