package Background;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import Database.SQLDatabase;

public class CalculatorDefault{
	
	private Boat getBoatByName(List<Boat> boats, String name){
		for(Boat b : boats) if(b.toString().equals(name)) return b;
		return null;
	}
	
	private List<Tour> getToursByBoat(List<Tour> tours, Boat boat){
		List<Tour> toursRet = new ArrayList<Tour>();
		for(Tour tour : tours) if(tour.boatName().equals(boat.toString())) toursRet.add(tour);
		return toursRet;
	}
	
	private List<Sale> getSalesByBoat(List<Sale> sales, Boat boat){
		List<Sale> salesRet = new ArrayList<Sale>();
		for(Sale sale : sales) if(sale.boatName().equals(boat.toString())) salesRet.add(sale);
		return salesRet;
	}
	
    public void calculateApportionment(SQLDatabase dataBaseConnection, Timestamp timeFrom, Timestamp timeTo){
        double avgCargoPercent = 0.0;
        int boatsTotal = 0;
        double totalGross = 0.0;
        double totalNet = 0.0;
        double totalGrossApportion = 0.0;
        double totalNetApportion = 0.0;
        double totalCommisionCost = 0.0;
        double totalCommisionCostApportion = 0.0;
        double totalCapacityApportion = 0.0;
        
        List<Boat> boatsEnvolved = new ArrayList<Boat>();
        List<Boat> boatsApportion = new ArrayList<Boat>();
        
        List<Boat> boats = dataBaseConnection.loadBoats(null, null);
        
        List<Tour> tours = dataBaseConnection.loadTours(timeFrom, timeTo, null, null);

        List<Sale> sales = dataBaseConnection.loadSales(timeFrom, timeTo,null,null, null, null);
        /*List<Enterprise> enterIncluded = new ArrayList<Enterprise>();
        for(Enterprise e : enterprises) {
            totalGross += e.totalGross();
            totalNet += e.totalNet();
            avgCargoPercent += e.cargoPercent();
        }*/
        /*avgCargoPercent /= enterprises.size();
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
        }*/
        
        for(Tour tour : tours){
        	Boat b = getBoatByName(boats, tour.boatName());
        	totalGross += tour.payingPassengers() * b.tourCost();
        	avgCargoPercent += tour.payingPassengers() / b.capacity();
        	boatsTotal++;
        	if(!boatsEnvolved.contains(b)) boatsEnvolved.add(b);
        }
        
        for(Sale sale : sales){
        	totalCommisionCost += sale.payingPassengers() * 10;
        }
        
        avgCargoPercent /= boatsTotal;
        
        totalNet = totalGross - totalCommisionCost;
        
        System.out.println("\n\nTotal bruto: R$ " + totalGross + " Total liq.: R$ " + totalNet + " Média perc. carga: " + avgCargoPercent);
        System.out.println("\n\nTrababalharam: ");
        System.out.println("\tBarco  \tIndice \tBruto \tLiq.");
        System.out.println("----------------------------------------");
        for(Boat b : boatsEnvolved){
        	double eCargoPercent = 0.0;
        	double totalCommisionCostBoat = 0.0;
        	double totalGrossBoat = 0.0;
        	List<Tour> toursBoat = getToursByBoat(tours, b);
        	for(Tour t : toursBoat) {
        		eCargoPercent += t.payingPassengers();
        		totalGrossBoat += t.payingPassengers() * b.tourCost();
        	}
        	eCargoPercent /= b.capacity() * toursBoat.size();
        	
        	List<Sale> salesBoat = getSalesByBoat(sales, b);
    		for(Sale sale : salesBoat) totalCommisionCostBoat += sale.payingPassengers() * 10;
        	
        	if(eCargoPercent >= avgCargoPercent){
        		totalCapacityApportion += b.capacity();
        		totalGrossApportion += totalGrossBoat;
        		totalCommisionCostApportion += totalCommisionCostBoat;
                boatsApportion.add(b);
        	}
        	
        	System.out.println("\t" + b.toString() +"\t" + eCargoPercent + "\t" + totalGrossBoat + "\t" + (totalGrossBoat - totalCommisionCostBoat));
        }
        totalNetApportion = totalGrossApportion - totalCommisionCostApportion;
        if(!boatsApportion.isEmpty()){
        	System.out.println("\n\nTotal bruto rateio: R$ " + totalGrossApportion + " Total liq. rateio: R$ " + totalNetApportion);
            System.out.println("\n\nEntraram no rateio: ");
            System.out.println("\tEmpresa  \tPerc. Carga \tPerc. rateio \tGanho");
            System.out.println("----------------------------------------");
            for(Boat b : boatsApportion){
            	double eCargoPercent = 0.0;
            	List<Tour> toursBoat = getToursByBoat(tours, b);
            	for(Tour t : toursBoat) eCargoPercent += t.payingPassengers();
            	eCargoPercent /= b.capacity() * toursBoat.size();
                double perApportionment = b.capacity()/totalCapacityApportion;
                System.out.println("\t" + b.toString()+"\t" + eCargoPercent + "\t" + perApportionment + "\t" + perApportionment * totalNetApportion);
            }
        }
        else{
            System.out.println("Nao houve rateio");
        }
        

    }
    public void calculateCommission(List<Seller> sellers){

    }
}
