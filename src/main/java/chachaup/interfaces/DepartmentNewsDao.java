package chachaup.interfaces;

import chachaup.domain.DepartmentNews;
import chachaup.domain.News;

import java.util.List;

public interface DepartmentNewsDao {
    //create
    void add(DepartmentNews news);

    //read
    News findById(int id);
    List<DepartmentNews> all();
    List<DepartmentNews> findByNewsAuthor(int employeeId);


    //update

    //delete
    void deleteById(int id);
}
