package test;

import Background.*;
import Database.*;
import java.sql.*;


public class JulianoTester{
	public static void main(String[] args) throws Exception{
    SQLDatabase q = new SQLDatabase("10.42.0.54","root","root");
    try {
      q.createDatabaseIfNoExist();
//       q.storeSale(new Sale(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"Juliano","qwerty","barcoza","empresabarcoza"));
//       q.storeTour(new Tour(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"barcao","empresabarco"));
//       q.storeBoat(new Boat("barcao1","empresabarco1",8001,8.09));
//       q.storeEnterprise(new Enterprise("empresabarcoza1"));
//       q.storeSeller(new Seller("nomevendedor","empresavendedor"));
      /*
      	q.storeEnterprise(new Enterprise("Barba Negra"));
      	q.storeEnterprise(new Enterprise("Pérola Negra"));
      	q.storeEnterprise(new Enterprise("Barba Negra"));
      	q.storeBoat(new Boat("Barba Negra I","Barba Negra",120,50.0));
      	q.storeBoat(new Boat("Barba Negra II","Barba Negra",100,50.0));
      	q.storeBoat(new Boat("Perola Negra I","Perola Negra",200,50.0));
      	q.storeSeller(new Seller("Serjio","Barba Negra"));*/
      for (Sale t: q.loadSales(null, null, null, null, null, null)){
        System.out.println(t.toDebug());
      }
      System.out.println();
      for (Tour t: q.loadTours(null, null, null, null)){
        System.out.println(t.toDebug());
      }
      System.out.println();
      for (Boat t: q.loadBoats(null, null)){
        System.out.println(t.toDebug());
      }
      System.out.println();
      for (Enterprise t: q.loadEnterprises(null)){
        System.out.println(t.toDebug());
      }
      System.out.println();
      for (Seller t: q.loadSellers(null,null)){
        System.out.println(t.toDebug());
      }
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("Deu ruim");
    }
	}
	
}