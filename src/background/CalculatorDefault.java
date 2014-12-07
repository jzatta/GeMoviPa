import java.util.List;
import java.util.ArrayList;

public class CalculatorDefault implements Calculator{

    public void calculateApportionment(Enterprise[] enterprises){
        double avgApportionPercent = 0.0;
        double totalGross = 0.0;
        List<Enterprise> enterIncluded = new ArrayList<Enterprise>();

        for(Enterprise e : enterprises) {
            totalGross += e.totalGross();
            avgApportionPercent += e.apportionmentPercent();
        }

    }
    public void calculateCommission(Seller[] sellers){

    }
}
