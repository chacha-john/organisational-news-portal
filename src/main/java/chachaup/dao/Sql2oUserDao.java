package chachaup.dao;

import chachaup.domain.User;
import chachaup.interfaces.UserDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name, phone, email, address, position, role, departmentId) VALUES(:name, :phone, :email, :address, :position, :role, :departmentId)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try (Connection con = sql2o.open()){
            User user = con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(User.class);
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public List<User> findByDepartment(int departmentId) {
        String sql = "SELECT * FROM users WHERE departmentId = :departmentId";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("departmentId",departmentId)
                    .executeAndFetch(User.class);

        }

    }


    @Override
    //functionality for admin deleting user
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = :id";
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
