public class Sale{
    private Tour tour;
    private PassengerAmount passengerAmount;

    public Sale(Tour tour, PassengerAmount passengerAmount){
        this.tour = tour;
        this.passengerAmount = passengerAmount;
    }

    public void validateSale(Seller seller){
        this.tour.addSeller(seller);
        this.tour.incrementPassengers(this.passengerAmount);
    }

    public double commissionCost(Tour tour){
        if(this.tour.getID() == tour.getID()) return this.passengerAmount.payingTotal() * 10;
        return 0.0;
    }
}
