package test;

import Background.*;
import Database.*;
import java.sql.*;


public class JulianoTester{
	public static void main(String[] args) throws Exception{
    SQLDatabase q = new SQLDatabase("","root","root");
    try {
      q.createDatabaseIfNoExist();
      q.storeSale(new Sale(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"Juliano","qwerty","barcoza","empresabarcoza"));
      q.storeTour(new Tour(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"barcao","empresabarco"));
      q.storeBoat(new Boat("barcao1","empresabarco1",8001,8.09));
      q.storeEnterprise(new Enterprise("empresabarcoza1"));
      q.storeSeller(new Seller("nomevendedor","empresavendedor"));
      for (Sale t: q.loadSales(null, null, null, null, null, null)){
        System.out.println(t.toString());
      }
      System.out.println();
      for (Tour t: q.loadTours(null, null, null, null)){
        System.out.println(t.toString());
      }
      System.out.println();
      for (Boat t: q.loadBoats(null, null)){
        System.out.println(t.toString());
      }
      System.out.println();
      for (Enterprise t: q.loadEnterprises(null)){
        System.out.println(t.toString());
      }
      System.out.println();
      for (Seller t: q.loadSellers(null,null)){
        System.out.println(t.toString());
      }
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("Deu ruim");
    }
	}
	
}