package Background;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import Database.SQLDatabase;

public class CalculatorDefault{
	
	private String getDatePortuguese(Timestamp t){
		String ret = t.toString();
		String year = ret.substring(0,ret.indexOf('-'));
		ret = ret.substring(ret.indexOf('-') + 1);
		String month = ret.substring(0,ret.indexOf('-'));
		ret = ret.substring(ret.indexOf('-') + 1);
		String day = ret.substring(0,ret.indexOf(' '));
		return day + "/" + month + "/" + year;
	}
	
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
        
        final String formatPattern = "%.4f";
        
        List<Boat> boatsEnvolved = new ArrayList<Boat>();
        List<Boat> boatsApportion = new ArrayList<Boat>();
        
        List<Boat> boats = dataBaseConnection.loadBoats(null, null);
        
        List<Tour> tours = dataBaseConnection.loadTours(timeFrom, timeTo, null, null);

        List<Sale> sales = dataBaseConnection.loadSales(timeFrom, timeTo,null,null, null, null);
        
        File outFile = new File("ResultadoRateio.csv");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWritter = null;
        try {
			fileWriter = new FileWriter(outFile);
			bufferedWritter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
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
        
        try{
       /* 	bufferedWritter.write("De\t"+"\""+timeFrom.toString()+"\""+"\tAté\t"+"\""+timeTo.toString()+"\""+"\n\n");
        	bufferedWritter.write("\"Total bruto\"\t" + totalGross + "\t\"Total liq.\"\t" + totalNet + "\t\"Média perc. carga\"\t" + avgCargoPercent+ "\n");
        	bufferedWritter.write("\n\nTrabalharam"+ "\n\n");
        	bufferedWritter.write("Barco\tIndice\tBruto\tLiq."+ "\n");*/
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
	        	
	    //    	bufferedWritter.write("\""+b.toString() +"\"\t" + eCargoPercent + "\t" + totalGrossBoat + "\t" + (totalGrossBoat - totalCommisionCostBoat) + "\n");
	        }
	        totalNetApportion = totalGrossApportion - totalCommisionCostApportion;
	        if(!boatsApportion.isEmpty()){
	/*        	bufferedWritter.write("\n\n\"Total bruto rateio\"\t" + totalGrossApportion + "\t\"Total liq. rateio\"\t" + totalNetApportion + "\n");
	        	bufferedWritter.write("\n\n\"Entraram no rateio\"\n\n");
	        	bufferedWritter.write("Barco\tPerc. Carga\tPerc. rateio\tLiq.\n");
	*/            for(Boat b : boatsApportion){
	            	double eCargoPercent = 0.0;
	            	List<Tour> toursBoat = getToursByBoat(tours, b);
	            	for(Tour t : toursBoat) eCargoPercent += t.payingPassengers();
	            	eCargoPercent /= b.capacity() * toursBoat.size();
	                double perApportionment = b.capacity()/totalCapacityApportion;
	                bufferedWritter.write(b.toString()+"\t" +getDatePortuguese(timeTo) + "\t"+ String.format(formatPattern, avgCargoPercent) + "\t" + 
	                		String.format(formatPattern, eCargoPercent) + "\t" + String.format(formatPattern,perApportionment) + "\t" + String.format("%.2f", perApportionment * totalNetApportion) + "\n");
	                
	                boatsEnvolved.remove(b); //remove to calculate who not on apportionment
	            }
	        }
	        else{
//	        	bufferedWritter.write("\"Nao houve rateio\""+ "\n");
	        }
	/*        bufferedWritter.write("\n\n\"Não entraram no rateio\""+ "\n\n");
	        bufferedWritter.write("Barco\tLiq."+ "\n");
	 */       for(Boat b : boatsEnvolved){
	        	double totalCommisionCostBoat = 0.0;
	        	double totalGrossBoat = 0.0;
	        	double eCargoPercent = 0.0;
	        	
	        	List<Tour> toursBoat = getToursByBoat(tours, b);
	        	for(Tour t : toursBoat) {
	        		totalGrossBoat += t.payingPassengers() * b.tourCost();
	        		eCargoPercent += t.payingPassengers();
	        	}
	        	eCargoPercent /= b.capacity() * toursBoat.size();
	        	
	        	List<Sale> salesBoat = getSalesByBoat(sales, b);
	    		for(Sale sale : salesBoat) totalCommisionCostBoat += sale.payingPassengers() * 10;
	    		
	    		bufferedWritter.write(b.toString()+"\t" +getDatePortuguese(timeTo) + "\t"+ String.format(formatPattern, avgCargoPercent) + "\t" + 
                		String.format(formatPattern, eCargoPercent) + "\t" + String.format(formatPattern,0.0) + "\t" + String.format("%.2f", totalGrossBoat - totalCommisionCostBoat) + "\n");
	    		
	//    		bufferedWritter.write("\""+b.toString()+"\"\t" + (totalGrossBoat - totalCommisionCostBoat) + "\n");
	        	
	        }
	        bufferedWritter.close();
	        Reporter.generateReport();
        }catch(IOException e){
        	e.printStackTrace();
        }
        

    }
    public void calculateCommission(SQLDatabase dataBaseConnection, Timestamp timeFrom, Timestamp timeTo){
    	List<Seller> sellers = dataBaseConnection.loadSellers(null, null);
    	List<Sale> sales = dataBaseConnection.loadSales(timeFrom, timeTo,null,null, null, null);
    	List<Boat> boats = dataBaseConnection.loadBoats(null, null);
    	
    	 File outFile = new File("ResultadoComissões.csv");
         FileWriter fileWriter = null;
         BufferedWriter bufferedWritter = null;
         try {
 			fileWriter = new FileWriter(outFile);
 			bufferedWritter = new BufferedWriter(fileWriter);
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
         try{
         	bufferedWritter.write("De\t"+timeFrom.toString()+"\tAté\t"+timeTo.toString()+"\n\n");
         	bufferedWritter.write("Vendedor\t\"Barco da venda\"\tComissão\n");
         
	         for(Seller seller : sellers){
	        	 double totalCommission = 0.0;	        	
	        	 bufferedWritter.write(seller.toString() + "\t \t \n");
	        	 for(Boat b : boats){
	        		 double commissionBoat = 0.0;
		        	 for(Sale sale : sales) {
		        		 if((sale.sellerName().equals(seller.toString())) && (sale.boatName().equals(b.toString()))) commissionBoat += sale.payingPassengers() * 10;
		        	 }
		        	 totalCommission += commissionBoat;
		        	 if(commissionBoat > 0.0) bufferedWritter.write("\t\""+b.toString()+"\"\t"+commissionBoat+"\n");	 
	        	 }	        	 
	        	 bufferedWritter.write("\tTotal\t" + totalCommission+"\n\n");
	         }
	         bufferedWritter.close();
         }catch(IOException e){
        	 e.printStackTrace();
         }

    }
    
    public void calculateSalesByBoat(SQLDatabase dataBaseConnection, Timestamp timeFrom, Timestamp timeTo){
    	
    	List<Seller> sellers = dataBaseConnection.loadSellers(null, null);
    	List<Sale> sales = dataBaseConnection.loadSales(timeFrom, timeTo,null,null, null, null);
    	List<Boat> boats = dataBaseConnection.loadBoats(null, null);
    	
		File outFile = new File("ResultadoVendas.csv");
		FileWriter fileWriter = null;
		BufferedWriter bufferedWritter = null;
		try {
			fileWriter = new FileWriter(outFile);
			bufferedWritter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
