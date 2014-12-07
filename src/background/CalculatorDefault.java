import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorDefault implements Calculator{

    public void calculateApportionment(List<Enterprise> enterprises){
        double avgApportionPercent = 0.0;
        double totalGross = 0.0;
        double totalNet = 0.0;
        List<Enterprise> enterIncluded = new ArrayList<Enterprise>();

        for(Enterprise e : enterprises) {
            totalGross += e.totalGross();
            totalNet += e.totalNet();
            avgApportionPercent += e.apportionmentPercent();
        }
        avgApportionPercent /= enterprises.size();
        System.out.println("\n\nTotal bruto: R$ " + totalGross + " Total liq.: R$ " + totalNet + " Média perc.: " + avgApportionPercent);
        System.out.println("\n\nTrababalharam: ");
        System.out.println("\tEmpresa  \tIndice \tLiq.");
        System.out.println("----------------------------------------");
        for(Enterprise e : enterprises){
            double eApportionmentPercent = e.apportionmentPercent();
            if(eApportionmentPercent >= avgApportionPercent) enterIncluded.add(e);
            System.out.println("\t" + e.getName()+"\t" + e.apportionmentPercent() + "\t" + e.totalNet());
        }

        if(!enterIncluded.isEmpty()){
            System.out.println("\n\nEntraram no rateio: ");
            System.out.println("\tEmpresa  \tIndice");
            System.out.println("----------------------------------------");
            for(Enterprise e : enterIncluded) System.out.println("\t" + e.getName()+"\t" + e.apportionmentPercent());
        }
        else{
            System.out.println("Nao houve rateio");
        }

    }
    public void calculateCommission(List<Seller> sellers){

    }
}
