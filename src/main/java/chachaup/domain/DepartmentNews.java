package chachaup.domain;


public class DepartmentNews extends News{
    private int departmentId;

    public DepartmentNews(String content, int employee, boolean published, int departmentId) {
        super(content, employee, published);
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
