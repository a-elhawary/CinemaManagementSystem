package Cinema.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Model {
    protected String table_name;
    public int id;

    public Model(String table_name,int id){
       this.table_name = table_name;
       this.id = id;
    }

    public void delete() {
        try {
            Connection c = Database.getCon();
            PreparedStatement delete = c.prepareStatement("delete from " + table_name + " where id = ?");
            delete.setInt(1, this.id);
            delete.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
