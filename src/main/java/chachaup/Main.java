package chachaup;

import chachaup.dao.Sql2oDepartmentDao;
import chachaup.dao.Sql2oDepartmentNewsDao;
import chachaup.dao.Sql2oNewsDao;
import chachaup.dao.Sql2oUserDao;
import chachaup.domain.Department;
import chachaup.domain.DepartmentNews;
import chachaup.domain.News;
import chachaup.domain.User;
import com.google.gson.Gson;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Spark;

import static spark.Spark.*;
import static spark.route.HttpMethod.after;
import static spark.route.HttpMethod.get;

public class Main {
    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentNewsDao departmentNewsDao;
        Sql2oUserDao userDao;
        Sql2oDepartmentDao departmentDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        con = sql2o.open();


        //users

        //post - process new user form
        post("/users/new","application/json",(request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        //get - return all users
        Spark.get("/users","application/json", (request, response) -> {
            return gson.toJson(userDao.getAll());
        });

        //get - get user whose id has been passed
        Spark.get("users/:id","application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            return gson.toJson(userDao.findById(userId));
        });

        //get - get all users in a department
        Spark.get("/users/departments/:departmentId", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(userDao.findByDepartment(departmentId));
        });

        //delete - delete user by supplied id
        delete("/users/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            userDao.deleteById(userId);
            response.status(201);
            return gson.toJson("The user has been deleted successfully!");
        });

        //departments

        //post - create a new department
        post("/departments/new","application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            return gson.toJson(department);
        });

        //get - return all departments
        Spark.get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentDao.getAll());
        });

        //get - return the department whose id has been supplied
        Spark.get("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentDao.findById(departmentId));
        });

        //delete department by Id
        delete("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            departmentDao.deleteById(departmentId);
            response.status(201);
            return gson.toJson("The department has been deleted successfully!");
        });

        //news

        //post - create general news
        post("news/new", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.status(201);
            return gson.toJson(news);
        });

        //get - retrieve all news
        Spark.get("/news", "application/json", (request, response) -> {
            response.status(201);
            return gson.toJson(newsDao.getAll());
        });

        //get - retrieve news by supplied id
        Spark.get("/news/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            return gson.toJson(newsDao.findById(id));
        });

        //get - find news by the employee who was responsible for them
        Spark.get("/news/employees/:id", "application/json", (request, response) -> {
            int employeeId = Integer.parseInt(request.params("id"));
            return gson.toJson(newsDao.findByAuthor(employeeId));
        });

        //delete - delete news by the id supplied
        delete("/news/:id", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            return gson.toJson("The news article has been deleted successfully!");
        });

        //department news

        //post - create news that are associated with a department
        post("/news/:department/news", "application/json", (request, response) -> {
            DepartmentNews news = gson.fromJson(request.body(), DepartmentNews.class);
            int departmentId = Integer.parseInt(request.params("department"));
            news.setDepartmentId(departmentId);
            departmentNewsDao.add(news);
            return gson.toJson(news);
        });

        //get - return all the departments news
        Spark.get("news/departments/all", "application/json", (request, response) -> {
            return gson.toJson(departmentNewsDao.all());
        });

        //get - return news specific to a department
        Spark.get("/news/departments/all/:id", "application/json", (request,response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentNewsDao.findByDepartment(departmentId));
        });

        //get - return all department news attributed to an author
        Spark.get("/news/department/authors/:id", "application/json", (request, response) -> {
            int authorId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentNewsDao.findByNewsAuthor(authorId));
        });

        //delete - delete news article by a specific id supplied
        delete("/news/departments/all/delete/:id", "application/json", (request, response) -> {
            int newsId = Integer.parseInt(request.params("id"));
            departmentNewsDao.deleteById(newsId);
            response.status(201);
            return gson.toJson("The news article has been deleted successfully!");
        });

        after((request, response) -> {
            response.type("application/json");
        });
    }
}