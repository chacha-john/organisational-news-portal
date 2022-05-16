package chachaup.interfaces;

import chachaup.domain.User;

import java.util.List;

public interface UserDao {

    //create
    void add(User user);

    //read
    User findById(int id);

    List<User> getAll();

    List<User> findByDepartment(int departmentId);

    //update
//    void updateDetails();

    //delete
    void deleteById(int id);
}
