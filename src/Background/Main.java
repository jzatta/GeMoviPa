package Background;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{

    public static void main(String[] args){
        List<Enterprise> enterprises;
        enterprises = new ArrayList<Enterprise>();
        initEnterprises(enterprises);

        CalculatorDefault calc = new CalculatorDefault();
        calc.calculateApportionment(enterprises);
    }

    public static void initEnterprises(List<Enterprise> enterprises){
        Enterprise enterprise = new Enterprise("Barba Negra",0);
        enterprises.add(enterprise);
        Boat boat = new Boat("Barba Negra I",0,140);
        enterprise.addBoat(boat);
        Tour tour = new Tour(boat,50.0,new PassengerAmount(140));
        boat.addTour(tour);

        Sale sale = new Sale(tour,new PassengerAmount(10));
        Seller seller = new Seller();
        seller.toSale(sale);

        enterprise = new Enterprise("Perola Negra",1);
        enterprises.add(enterprise);
        boat = new Boat("Perola Negra I",0,200);
        enterprise.addBoat(boat);
        tour = new Tour(boat,50.0,new PassengerAmount(160));
        boat.addTour(tour);

        enterprise = new Enterprise("Golfinho",1);
        enterprises.add(enterprise);
        boat = new Boat("Pirata da Ilha",0,100);
        enterprise.addBoat(boat);
        tour = new Tour(boat,50.0,new PassengerAmount(100));
        boat.addTour(tour);

        enterprise = new Enterprise("Scuna Sul",1);
        enterprises.add(enterprise);
        boat = new Boat("Pirata do Caribe",0,200);
        enterprise.addBoat(boat);
        tour = new Tour(boat,50.0,new PassengerAmount(100));
        boat.addTour(tour);
    }
}
