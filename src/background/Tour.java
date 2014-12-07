public class Tour implements Billable{
    private Boat boat;
    private int departureTime;
    private PassengerAmount passengerAmount;
    private double value;
    
    public Tour(Boat boat, int departureTime){
        this.boat = boat;
        this.departureTime = departureTime;
    }

    public PassengerAmount passengerAmount(){
        return this.passengerAmount;
    }

    public double totalGross(){
        return passengerAmount.payingTotal() * value;
    }
    
    public double totalNet(){
        return 0.0;
    }
}
