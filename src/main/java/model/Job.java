package model;

public class Job {
    private String position;
    private String company;
    private String location;

    public Job(String position, String company, String location) {
        this.position = position;
        this.company = company;
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }
}
