package chachaup.dao;

import chachaup.domain.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {

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
    void add_setsId_true() {
        Department department = setUpDepartment();
        assertEquals(1,department.getId());

    }

    @Test
    void findById_getDepartmentByIdProvided_true() {
        Department department = setUpDepartment();
        Department department1 = setUpDepartment();
        assertEquals(2,departmentDao.findById(department1.getId()).getId());
    }

    @Test
    void all_getsAllDepartmentsInAnOrganization_true() {
        Department department = setUpDepartment();
        Department department1 = setUpDepartment();
        assertEquals(2,departmentDao.getAll().size());
    }

    @Test
    void deleteById_deletesBySuppliedId_true() {
        Department department = setUpDepartment();
        Department department1 = setUpDepartment();
        assertEquals(2,departmentDao.getAll().size());
        departmentDao.deleteById(department.getId());
        assertEquals(1,departmentDao.getAll().size());
    }

    //helper methods
    public Department setUpDepartment(){
        Department department = new Department("back office","talented friends who work together for the better good");
        departmentDao.add(department);
        return department;
    }
}