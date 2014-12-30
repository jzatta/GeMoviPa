package Background;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;

public class Tour{
    private int idTour;
    private int fullPass, halfPass, freePass;
    private Timestamp departure;
    private String boatName, boatEnterprise;
    private double discountValueTotal;

    public Tour(ResultSet result) throws SQLException{
      this.idTour             = result.getInt("idtours");
      this.fullPass           = result.getInt("fullPass");
      this.halfPass           = result.getInt("halfPass");
      this.freePass           = result.getInt("freePass");
      this.departure          = result.getTimestamp("departure");
      this.boatName           = result.getString("boatName");
      this.boatEnterprise     = result.getString("boatEnterprise");
      this.discountValueTotal = result.getDouble("discountValueTotal");
    }
    
    public String boatName(){
    	return this.boatName;
    }
    
    public double payingPassengers(){
    	return fullPass + halfPass * 0.5;
    }
    
    public int fullPassengers(){
    	return this.fullPass;
    }
    
    public int freePassengers(){
    	return this.freePass;
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
      this.discountValueTotal = 0.0;
    }
    
    public Tour(int fullPass, int halfPass, int freePass,
                Timestamp departure,
                String boatName, String boatEnterprise,
                double discountValueTotal){
      this.fullPass         = fullPass;
      this.halfPass         = halfPass;
      this.freePass         = freePass;
      this.departure        = departure;
      this.boatName         = boatName;
      this.boatEnterprise   = boatEnterprise;
      this.discountValueTotal = discountValueTotal;
    }
    
    public String insertParameters(){
      return Integer.toString(this.fullPass)+","+
      Integer.toString(this.halfPass)+","+
      Integer.toString(this.freePass)+","+
      "\""+this.departure       +"\","+
      "\""+this.boatName        +"\","+
      "\""+this.boatEnterprise  +"\","+
      "\""+this.discountValueTotal+"\"";
    }

    public String toDebug(){
      return this.fullPass+"/"+this.halfPass+"/"+this.freePass+"/"+this.departure+"/"+this.boatName+"/"+this.boatEnterprise;
    }
}
