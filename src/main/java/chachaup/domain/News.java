package chachaup.domain;

import java.util.Date;

public class News {
    private int id;
    private String content;
    private String employee;
    private Date dateCreated;
    private boolean published;

    public News(String content, String employee, Date dateCreated, boolean published) {
        this.content = content;
        this.employee = employee;
        this.dateCreated = dateCreated;
        this.published = published;
    }
}
