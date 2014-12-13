package Background;

import java.util.List;
import java.util.ArrayList;

public class Boat implements Billable{
    private int idBoat, boatCapacity;
    private String boatName, boatEnterprise;
    private double tourCost;
    
    public Boat(ResultSet result){
      this.idBoat           = result.getInt("idBoat");
      this.boatName         = result.getString("boatName");
      this.boatEnterprise   = result.getString("boatEnterprise");
      this.boatCapacity     = result.getInt("boatCapacity");
      this.tourCost         = result.getDouble("tourCost");
    }

    public Boat(String name, int id, int capacity){
        this.name = name;
        this.id = id;
        this.capacity = capacity;
        this.tours = new ArrayList<Tour>();
    }

    public double totalGross(){
        double totalGross = 0.0;
       // for(Tour t : tours) totalGross += t.totalGross();
        return totalGross;
    }

    public double totalNet(){
        double totalNet = 0.0;
        //for(Tour t : tours) totalNet += t.totalNet();
        return totalNet;
    }

    public boolean addTour(Tour t){
        return this.tours.add(t);
    }

    public int getID(){
        return this.id;
    }

    public int capacity(){
        return this.capacity;
    }

    public double cargoPercent(){
        double cargoPercent = 0.0;
       // for(Tour t : tours) cargoPercent += t.passengerAmount().payingTotal() / this.capacity;
        return cargoPercent / tours.size();
    }

}
