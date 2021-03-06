package Background;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;

public class Sale{
    int fullPass, halfPass, freePass;
    Timestamp departure;
    String sellerName, sellerEnterprise;
    String boatName, boatEnterprise;

    public Sale(ResultSet result) throws SQLException{
        this.fullPass         = result.getInt("fullPass");
        this.halfPass         = result.getInt("halfPass");
        this.freePass         = result.getInt("freePass");
        this.departure        = result.getTimestamp("departure");
        this.sellerName       = result.getString("sellerName");
        this.sellerEnterprise = result.getString("sellerEnterprise");
        this.boatName         = result.getString("boatName");
        this.boatEnterprise   = result.getString("boatEnterprise");
    }
    
    public Sale(int fullPass, int halfPass, int freePass,
                Timestamp departure,
                String sellerName, String sellerEnterprise, String boatName, String boatEnterprise){
        this.fullPass         = fullPass;
        this.halfPass         = halfPass;
        this.freePass         = freePass;
        this.departure        = departure;
        this.sellerName       = sellerName;
        this.sellerEnterprise = sellerEnterprise;
        this.boatName         = boatName;
        this.boatEnterprise   = boatEnterprise;
    }
    
    public String insertParameters(){
      return Integer.toString(this.fullPass)+","+
      Integer.toString(this.halfPass)+","+
      Integer.toString(this.freePass)+","+
      "\""+this.departure       +"\","+
      "\""+this.sellerName      +"\","+
      "\""+this.sellerEnterprise+"\","+
      "\""+this.boatName        +"\","+
      "\""+this.boatEnterprise  +"\"";
    }
    
    public String toString(){
      return this.fullPass+"/"+this.halfPass+"/"+this.freePass+"/"+this.departure+"/"+this.sellerName+"/"+this.sellerEnterprise+"/"+this.boatName+"/"+this.boatEnterprise;
    }
}
