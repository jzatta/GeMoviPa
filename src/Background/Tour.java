package Background;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;

public class Tour{
    int fullPass, halfPass, freePass;
    Timestamp departure;
    String boatName, boatEnterprise;

    public Tour(ResultSet result) throws SQLException{
      this.fullPass         = result.getInt("fullPass");
      this.halfPass         = result.getInt("halfPass");
      this.freePass         = result.getInt("freePass");
      this.departure        = result.getTimestamp("departure");
      this.boatName         = result.getString("boatName");
      this.boatEnterprise   = result.getString("boatEnterprise");
    }
    
    public Tour(int fullPass, int halfPass, int freePass,
                Timestamp departure,
                String boatName, String boatEnterprise){
      this.fullPass         = fullPass;
      this.halfPass         = halfPass;
      this.freePass         = freePass;
      this.departure        = departure;
      this.boatName         = boatName;
      this.boatEnterprise   = boatEnterprise;
    }
    
    public String insertParameters(){
      return Integer.toString(this.fullPass)+","+
      Integer.toString(this.halfPass)+","+
      Integer.toString(this.freePass)+","+
      "\""+this.departure       +"\","+
      "\""+this.boatName        +"\","+
      "\""+this.boatEnterprise  +"\"";
    }
    
    public String toString(){
      return this.fullPass+"/"+this.halfPass+"/"+this.freePass+"/"+this.departure+"/"+this.boatName+"/"+this.boatEnterprise;
    }
}
