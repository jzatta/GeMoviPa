package Background;

import java.sql.ResultSet;
import java.sql.Timestamp;

public class Sale{
    int fullPass, halfPass, freePass;
    String sellerName, sellerEnterprise;
    Timestamp departure;
    String boatName, boatEnterprise;

    public Sale(ResultSet rs){
        
    }
    
    public String insertParameters(){
      return Integer.toString(this.fullPass)+","+
      Integer.toString(this.halfPass)+","+
      Integer.toString(this.freePass)+","+
      "\""+this.sellerName      +"\","+
      "\""+this.sellerEnterprise+"\","+
      "\""+this.departure       +"\","+
      "\""+this.boatName        +"\","+
      "\""+this.boatEnterprise  +"\"";
    }
}
