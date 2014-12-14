package Background;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorDefault implements Calculator{

    public void calculateApportionment(List<Enterprise> enterprises){
        /*double avgCargoPercent = 0.0;
        double totalGross = 0.0;
        double totalNet = 0.0;
        double totalCapacityApportion = 0.0;
        List<Enterprise> enterIncluded = new ArrayList<Enterprise>();

        for(Enterprise e : enterprises) {
            totalGross += e.totalGross();
            totalNet += e.totalNet();
            avgCargoPercent += e.cargoPercent();
        }
        avgCargoPercent /= enterprises.size();
        System.out.println("\n\nTotal bruto: R$ " + totalGross + " Total liq.: R$ " + totalNet + " Média perc. carga: " + avgCargoPercent);
        System.out.println("\n\nTrababalharam: ");
        System.out.println("\tEmpresa  \tIndice \tLiq.");
        System.out.println("----------------------------------------");
        for(Enterprise e : enterprises){
            double eCargoPercent = e.cargoPercent();
            if(eCargoPercent >= avgCargoPercent) {
                totalCapacityApportion += e.totalCapacity();
                enterIncluded.add(e);
            }
            System.out.println("\t" + e.getName()+"\t" + e.cargoPercent() + "\t" + e.totalNet());
        }

        if(!enterIncluded.isEmpty()){
            System.out.println("\n\nEntraram no rateio: ");
            System.out.println("\tEmpresa  \tPerc. Carga \tPerc. rateio \tGanho");
            System.out.println("----------------------------------------");
            for(Enterprise e : enterIncluded){
                double perApportionment = e.totalCapacity()/totalCapacityApportion;
                System.out.println("\t" + e.getName()+"\t" + e.cargoPercent() + "\t" + perApportionment + "\t" + perApportionment * totalNet);
            }
        }
        else{
            System.out.println("Nao houve rateio");
        }
*/
    }
    public void calculateCommission(List<Seller> sellers){

    }
}
