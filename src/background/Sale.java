public class Sale{
    private Tour tour;
    private PassengerAmount passengerAmount;

    public int tourID(){
        return this.tour.getID();
    }

    public PassengerAmount passengerAmount(){
        return this.passengerAmount;
    }
}
