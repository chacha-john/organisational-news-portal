package chachaup.dao;

import chachaup.domain.News;
import chachaup.interfaces.NewsDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (content, employeeId, dateCreated, published) VALUES (:content,:employee,:dateCreated, :published)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id";
        try(Connection con = sql2o.open()){
            News news = con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(News.class);
            return news;
        }
    }

    @Override
    public List<News> getAll() {
        String sql = "SELECT * FROM news";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> findByAuthor(int employeeId) {
        String sql = "SELECT * FROM news WHERE employeeid = :employeeId";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("employeeId",employeeId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }
}
