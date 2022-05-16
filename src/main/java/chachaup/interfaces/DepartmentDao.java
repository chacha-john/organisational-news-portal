package chachaup.interfaces;

import chachaup.domain.Department;
import chachaup.domain.News;

import java.util.List;

public interface DepartmentDao {
    //create
    void add(Department department);


    //read
    Department findById(int id);
    List<Department> getAll();
//    void addNews(int departmentId, News news);


    //update
    void updateDetails(int id);
    void addUserToDepartment(int userId);

    //delete
    void deleteById(int id);
}
