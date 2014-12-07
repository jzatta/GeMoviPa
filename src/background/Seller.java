import java.util.List;

public class Seller{
    private int id;
    private String name;
    private List<Sale> sales;

    public void toSale(Sale s){
        sales.add(s);
    }

}
