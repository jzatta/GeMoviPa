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
    
    public Enterprise(String enterpriseName){
      this.id   = 0;
      this.name = enterpriseName;
    }
    
    public int id(){
      return this.id;
    }
    
    public String insertParameters(){
      return Integer.toString(this.id)+","+
      "\""+this.name+"\"";
    }
    
    public String name(){
    	return this.name;
    }
    
    public String toString(){
      return name;
    }
    
    public String toDebug(){
      return name;
    }

}
