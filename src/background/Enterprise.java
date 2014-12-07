import java.util.List;
import java.util.ArrayList;

public class Enterprise implements Billable{
    private int id;
    private String name;
    private List<Seller> sellers;
    private List<Boat> boats;

    public Enterprise(String name, int id){
        this.name = name;
        this.id = id;
        this.sellers = new ArrayList<Seller>();
        this.boats = new ArrayList<Boat>();
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
        return 0.0;
    }

    public double apportionmentPercent(){ 
        double apportionmentPercent = 0.0;
        for(Boat b : boats) apportionmentPercent += b.apportionmentPercent();
        return  apportionmentPercent / boats.size();
    }

}
