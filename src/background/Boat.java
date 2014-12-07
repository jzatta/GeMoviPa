import java.util.List;
import java.util.ArrayList;

public class Boat implements Billable{
    private int id;
    private String name;
    private int capacity;
    private List<Tour> tours;

    public Boat(String name, int id, int capacity){
        this.name = name;
        this.id = id;
        this.capacity = capacity;
        this.tours = new ArrayList<Tour>();
    }

    public double totalGross(){
        double totalGross = 0.0;
        for(Tour t : tours) totalGross += t.totalGross();
        return totalGross;
    }

    public double totalNet(){
        return 0.0;
    }

    public boolean addTour(Tour t){
        return this.tours.add(t);
    }

    public double apportionmentPercent(){
        double apportionmentPercent = 0.0;
        for(Tour t : tours) apportionmentPercent += t.passengerAmount().payingTotal() / this.capacity;
        return apportionmentPercent / tours.size();
    }

}
