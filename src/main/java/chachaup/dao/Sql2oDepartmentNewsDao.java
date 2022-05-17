package chachaup.dao;

import chachaup.domain.DepartmentNews;
import chachaup.domain.News;
import chachaup.interfaces.DepartmentNewsDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentNewsDao extends Sql2oNewsDao implements DepartmentNewsDao{
    private final Sql2o sql2o;
    public Sql2oDepartmentNewsDao(Sql2o sql2o) {
        super(sql2o);
        this.sql2o = sql2o;
    }

    @Override
    public void add(DepartmentNews news) {
        String sql = "INSERT INTO news (content, employeeId, published,departmentId) VALUES (:content,:employee, :published,:departmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(news)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE departmentId !=0 AND id = :id";
        try(Connection con = sql2o.open()){
            News news = con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(News.class);
            return news;
        }
    }

    @Override
    public List<DepartmentNews> all() {
        String sql = "SELECT * FROM news WHERE departmentId != 0";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public List<DepartmentNews> findByNewsAuthor(int employeeId) {
        String sql = "SELECT * FROM news WHERE departmentId != 0 AND employeeId = :employeeId";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("employeeId",employeeId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public List<DepartmentNews> findByDepartment(int departmentId) {
        try (Connection con = sql2o.open()) {
            String query = "SELECT * FROM news WHERE departmentid = :departmentId";
            return con.createQuery(query)
                    .addParameter("departmentId",departmentId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);

        } catch (Exception ex){
            throw new RuntimeException("Error encountered", ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE departmentId != 0 AND id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }
}
