package Background;

import java.util.List;
import java.util.ArrayList;

public class Seller{
    private int id;
    private String name;
    private List<Sale> sales;

    public Seller(){
        sales = new ArrayList<Sale>();
    }
    public void toSale(Sale s){
        s.validateSale(this);
        sales.add(s);
    }

    public double commissionCost(Tour tour){
        double totalCommission = 0.0;
        for(Sale s : sales) totalCommission += s.commissionCost(tour);
        return totalCommission;
    }
}
