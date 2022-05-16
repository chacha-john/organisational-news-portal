package chachaup.dao;

import chachaup.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oUserDaoTest {

    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oDepartmentNewsDao departmentNewsDao;
    private Sql2oNewsDao newsDao;
    private Connection con;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        con = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        con.close();
    }

    @Test
    void add_addingUserSetsTheirId_true() {
        User user = setUpUser();
        assertEquals(1,user.getId());
    }

    @Test
    void findById_getUserWithSuppliedId_true() {
        User user = setUpUser();
        User user1 = setUpUser();
        assertEquals(2,userDao.findById(user1.getId()).getId());
    }

    @Test
    void findByDepartment_findUserByDepartmentId_true() {
        User user = setUpUser();
        User user1 = setUpUser();
        assertEquals(2,userDao.findByDepartment(user1.getDepartmentId()).size());
    }

    @Test
    void all_getAllGetsAllUsers_true() {
        User user = setUpUser();
        User user1 = setUpUser();
        User user2 = setUpUser();
        User user3 = setUpUser();
        assertEquals(4,userDao.getAll().size());
    }

    @Test
    void delete_adminCanDeleteUser_true() {
        User user = setUpUser();
        User user1 = setUpUser();
        assertEquals(2,userDao.getAll().size());
        userDao.deleteById(user.getId());
        assertEquals(1,userDao.getAll().size());
    }

    //helper methods
    public User setUpUser(){
        User user = new User("chacha","0708560003","test@riko.com","23 Mondlane Street","clerk","editing",2);
        userDao.add(user);
        return user;
    }

}