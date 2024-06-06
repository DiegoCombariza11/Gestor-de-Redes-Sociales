package co.edu.uptc.SocialMediaManager.model;

public class Interaction {
    private String date;
    private String user;

    public Interaction(String date, String user) {
        this.date = date;
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
