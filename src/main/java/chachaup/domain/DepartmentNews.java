package chachaup.domain;

import java.util.Date;

public class DepartmentNews extends News{

    private int id;
    private int departmentId;

    public DepartmentNews(String content, int employee, Date dateCreated, boolean published, int departmentId) {
        super(content, employee, dateCreated, published);
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
