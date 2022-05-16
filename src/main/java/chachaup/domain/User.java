package chachaup.domain;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String position;
    private String role;
    private int departmentId;
    private ArrayList<Integer> newsItems;

    public User(String name, String phone, String email, String address, String position, String role, int departmentId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.position = position;
        this.role = role;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public ArrayList<Integer> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(ArrayList<Integer> newsItems) {
        this.newsItems = newsItems;
    }
}
