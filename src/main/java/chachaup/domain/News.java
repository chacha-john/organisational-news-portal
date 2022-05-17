package chachaup.domain;

import java.sql.Timestamp;
import java.util.Date;

public class News {
    private int id;
    private String content;

    private Timestamp dateCreated;
    private int employeeId;
    private boolean published;

    public News(String content, int employeeId, boolean published) {
        this.content = content;
        this.employeeId = employeeId;
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEmployee() {
        return employeeId;
    }

    public void setEmployee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
