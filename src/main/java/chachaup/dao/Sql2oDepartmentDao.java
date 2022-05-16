package chachaup.dao;

import chachaup.domain.Department;
import chachaup.interfaces.DepartmentDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments(name,description) VALUES(:name, :description)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Department findById(int id) {
        String sql = "SELECT * FROM departments WHERE id = :id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Department.class);

        }
    }

    @Override
    public List<Department> getAll() {
        String sql = "SELECT * FROM departments";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public void updateDetails(int id) {

    }

    @Override
    public void addUserToDepartment(int userId) {

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments where id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }

    }
}
