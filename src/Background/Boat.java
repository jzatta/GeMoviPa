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
      this.idBoat = 0;
      this.boatName       = boatName;
      this.boatEnterprise = boatEnterprise;
      this.boatCapacity   = boatCapacity;
      this.tourCost       = tourCost;
    }
    
    public Boat(int idBoat, String boatName, String boatEnterprise, int boatCapacity, double tourCost){
        this.idBoat         = idBoat;
        this.boatName       = boatName;
        this.boatEnterprise = boatEnterprise;
        this.boatCapacity   = boatCapacity;
        this.tourCost       = tourCost;
      }
    
    public String enterpriseName(){
    	return this.boatEnterprise;
    }
    
    public int id(){
    	return this.idBoat;
    }
    
    public double tourCost(){
    	return this.tourCost;
    }
    
    public int capacity(){
    	return this.boatCapacity;
    }
    
    public String name(){
    	return this.boatName;
    }
    
    public String insertParameters(){
      return Integer.toString(this.idBoat)+","+
      "\""+this.boatName+"\","+
      "\""+this.boatEnterprise+"\","+
      Integer.toString(this.boatCapacity)+","+
      Double.toString(this.tourCost);
    }
    
    public String toString(){
      return boatName;
    }

    public String toDebug(){
      return Integer.toString(idBoat) +"/"+ Integer.toString(boatCapacity) +"/"+ boatName +"/"+ boatEnterprise +"/"+ Double.toString(tourCost);
    }
    
    public boolean equals(Boat b){
    	if((this.idBoat == b.idBoat) &&
    	   (this.boatCapacity == b.boatCapacity) &&
    	   (this.boatName.equals(b.boatName)) &&
    	   (this.boatEnterprise.equals(b.boatEnterprise)) &&
    	   (this.tourCost == b.tourCost))
    		return true;
    	return false;
    }
}
