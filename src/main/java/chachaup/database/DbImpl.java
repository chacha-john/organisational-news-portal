package chachaup.database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DbImpl extends Db{
    @Override
    public Connection connect() {
        try{
            String connectionString = "jdbc:postgresql://localhost:5432/rinews";
            return new Sql2o(connectionString,"riko","nzfu5321").open();
        } catch (Exception ex){
            throw new RuntimeException("Error encountered", ex);
        }
    }

    @Override
    public void disconnect(Connection connection) {
        try{
            connection.close();
        } catch (Exception ex){
            throw new RuntimeException("Error encountered", ex);
        }

    }
}
