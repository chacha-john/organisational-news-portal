package chachaup.dao;

import chachaup.domain.DepartmentNews;
import chachaup.domain.News;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentNewsDaoTest {
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
        newsDao = new Sql2oNewsDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        con = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        con.close();
    }

    @Test
    void add_setsIdCorrectly_true() {
//        DepartmentNews departmentNews = setUpDepartmentNews();
        DepartmentNews departmentNews1 = new DepartmentNews("small improvements",2,new Date(),true,2);
        departmentNewsDao.add(departmentNews1);
        assertEquals(1, departmentNews1.getId());

    }

    @Test
    void getsAll_returnsAllDepartmentNews_true() {
        News news = setUpNews();
        DepartmentNews departmentNews = new DepartmentNews("small improvements",2,new Date(),true,2);
        departmentNewsDao.add(departmentNews);
        assertEquals(1,departmentNewsDao.all().size());
    }

    @Test
    void findById_returnsDepartmentNewsWithIdProvided_true() {
        DepartmentNews news = setUpDepartmentNews();
        DepartmentNews news1 = setUpDepartmentNews();
        assertEquals(2,departmentNewsDao.findById(news1.getId()).getId());
    }

    @Test
    void findByNewsAuthor_returnsDepartmentNewsWithSpecifiedNewsAuthor() {
        DepartmentNews news = setUpDepartmentNews();
        DepartmentNews news1 = setUpDepartmentNews();
        assertEquals(2,departmentNewsDao.findByNewsAuthor(news1.getEmployee()).size());
    }

    @Test
    void deleteById_deletesDepartmentNewsForSuppliedId_true() {
        DepartmentNews news = setUpDepartmentNews();
        DepartmentNews news1 = setUpDepartmentNews();
        assertEquals(2,departmentNewsDao.all().size());
        departmentNewsDao.deleteById(news.getId());
        assertEquals(1,departmentNewsDao.all().size());
    }

    //helper methods
    public DepartmentNews setUpDepartmentNews(){
        DepartmentNews departmentNews = new DepartmentNews("small improvements",2,new Date(),true,2);
        departmentNewsDao.add(departmentNews);
        return departmentNews;
    }

    public News setUpNews(){
        News news = new News("small improvements",2,new Date(),true);
        newsDao.add(news);
        return news;
    }
}