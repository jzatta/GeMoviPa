package Background;

import java.util.List;
import java.util.ArrayList;

public class Enterprise implements Billable{
    private int id;
    private String name;
    
    public Enterprise(ResultSet result){
      this.id   = result.getInt("identerprise");
      this.name = result.getString("enterpriseName");
    }

    public Enterprise(String name, int id){
        this.name = name;
        this.id = id;
        this.sellers = new ArrayList<Seller>();
        this.boats = new ArrayList<Boat>();
    }
    
    public String getName(){
        return this.name;
    }

    public boolean addBoat(Boat b){
        return this.boats.add(b);
    }

    public double totalGross(){
        double totalGross = 0.0;
        for(Boat b : boats) totalGross += b.totalGross();
        return totalGross;
    }
    public double totalNet(){
        double totalNet = 0.0;
        for(Boat b : boats) totalNet += b.totalNet();
        return totalNet; 
    }

    public double cargoPercent(){ 
        double cargoPercent = 0.0;
        for(Boat b : boats) cargoPercent += b.cargoPercent();
        return  cargoPercent / boats.size();
    }

    public double totalCapacity(){
        double totalCapacity = 0.0;
        for(Boat b : boats) totalCapacity += b.capacity();
        return totalCapacity;
    }

}
