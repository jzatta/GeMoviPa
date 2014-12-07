import java.util.List;
import java.util.ArrayList;

public class Tour implements Billable{
    private int id;
    private Boat boat;
    private List<Seller> sellers;
    private int departureTime;
    private PassengerAmount passengerAmount;
    private double value;
    private int date;

    public Tour(Boat boat, int departureTime){
        this.boat = boat;
        this.departureTime = departureTime;
        this.sellers = new ArrayList<Seller>();
    }

    public Tour(Boat boat, double value, PassengerAmount passengerAmount){
        this.boat = boat;
        this.value = value;
        this.passengerAmount = passengerAmount;
        this.sellers = new ArrayList<Seller>();
    }

    public PassengerAmount passengerAmount(){
        return this.passengerAmount;
    }

    public boolean incrementPassengers(PassengerAmount passengerAmount){
        if(this.passengerAmount.sum(passengerAmount) > boat.capacity())
            return false;
        this.passengerAmount.increment(passengerAmount);
        return true;
    }

    public boolean addSeller(Seller seller){
        return this.sellers.add(seller);
    }

    public int getID(){
        return this.id;
    }

    public double totalGross(){
        return passengerAmount.payingTotal() * value;
    }
    
    public double totalNet(){
        double totalCost = 0.0;
        for(Seller s : sellers) totalCost += s.commissionCost(this);
        return this.totalGross() - totalCost;
    }
}
