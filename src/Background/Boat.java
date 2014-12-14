package Background;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Boat{
    private int idBoat, boatCapacity;
    private String boatName, boatEnterprise;
    private double tourCost;
    
    public Boat(ResultSet result) throws SQLException{
      this.idBoat           = result.getInt("idboats");
      this.boatName         = result.getString("boatName");
      this.boatEnterprise   = result.getString("boatEnterprise");
      this.boatCapacity     = result.getInt("boatCapacity");
      this.tourCost         = result.getDouble("tourCost");
    }
    
    public Boat(String boatName, String boatEnterprise, int boatCapacity, double tourCost){
      this.boatName       = boatName;
      this.boatEnterprise = boatEnterprise;
      this.boatCapacity   = boatCapacity;
      this.tourCost       = tourCost;
    }
    
    public String enterpriseName(){
    	return this.boatEnterprise;
    }
    
    public String insertParameters(){
      return "\""+this.boatName+"\","+
      "\""+this.boatEnterprise+"\","+
      Integer.toString(this.boatCapacity)+","+
      Double.toString(this.tourCost);
    }

    public String toString(){
      return Integer.toString(idBoat) +"/"+ Integer.toString(boatCapacity) +"/"+ boatName +"/"+ boatEnterprise +"/"+ Double.toString(tourCost);
    }
}
