package chachaup.dao;

import chachaup.domain.News;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Sql2oNewsDaoTest {
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
        con = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        con.close();
    }

    @Test
    void add_addingNewsSetsAUniqueId_true() {
        News news = setUpNews();
        assertEquals(1,news.getId());

    }

    @Test
    void findAll_getsAllNewsInTheCompany_true() {
        News news = setUpNews();
        News news1 = setUpNews();
        News news2 = setUpNews();
        assertEquals(3,newsDao.getAll().size());
    }

    @Test
    void findById_getSpecificNewsByIdSpecified_true() {
        News news = setUpNews();
        News news1 = setUpNews();
        assertEquals(2,newsDao.findById(news1.getId()).getId());
    }

    @Test
    void findByAuthor_getAllNewsArticlesByTheEmployeeResponsible_true() {
        News news = setUpNews();
        News news1 = setUpNews();
        News news2 = setUpNews();
        News news3 = new News("small improvements",69, true);
        newsDao.add(news3);
        assertEquals(3,newsDao.findByAuthor(2).size());
        assertEquals(1,newsDao.findByAuthor(69).size());
    }

    @Test
    void delete_checkForDeletingAnArticleByItsId_true() {
        News news = setUpNews();
        News news1 = setUpNews();
        News news2 = setUpNews();
        News news3 = setUpNews();
        assertEquals(4,newsDao.getAll().size());
        newsDao.deleteById(news2.getId());
        assertEquals(3,newsDao.getAll().size());

    }

    //helper methods
    public News setUpNews(){
        News news = new News("small improvements",2, true);
        newsDao.add(news);
        return news;
    }
}