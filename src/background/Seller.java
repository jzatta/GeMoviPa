import java.util.List;

public class Seller{
    private int id;
    private String name;
    private List<Sale> sales;

    public void toSale(Sale s){
        sales.add(s);
    }

    public double commissionCost(Tour tour){
        double totalCommission = 0.0;
        for(Sale s : sales) if(s.tourID() == tour.getID()) totalCommission += s.passengerAmount().payingTotal() * 10;
        return totalCommission;
    }
}
