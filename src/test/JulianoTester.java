package test;

import Background.*;
import Database.*;
import java.sql.*;


public class JulianoTester{
	public static void main(String[] args) throws Exception{
    SQLDatabase q = new SQLDatabase("127.0.0.1","root","root");
    try {
      q.createDatabaseIfNoExist();
      q.storeSale(new Sale(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"Juliano","qwerty","barcoza","empresabarcoza"));
      q.storeTour(new Tour(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"barcao","empresabarco"));
      for (Sale t: q.loadSales(null, Timestamp.valueOf("2014-01-01 01:01:01"), null, null, null, null)){
        System.out.println(t.toString());
      }
      System.out.println();
      for (Tour t: q.loadTours(null, null, null, null)){
        System.out.println(t.toString());
      }
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("Deu ruim");
    }
	}
	
}