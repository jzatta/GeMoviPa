package Background;

import java.util.List;
import java.util.ArrayList;import java.sql.ResultSet;
import java.sql.SQLException;

public class Enterprise{
    private int id;
    private String name;
    
    public Enterprise(ResultSet result) throws SQLException{
      this.id   = result.getInt("identerprises");
      this.name = result.getString("enterpriseName");
    }
    
    public String insertParameters(){
      return this.name;
    }

}
