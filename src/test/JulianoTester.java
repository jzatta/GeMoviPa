package test;

import Background.*;
import Database.*;
import java.sql.*;


public class JulianoTester{
	public static void main(String[] args) throws Exception{
    SQLDatabase q = new SQLDatabase("127.0.0.1","root","root");
    try {
      q.createDatabaseIfNoExist();
      System.out.println(q.storeSeller(new Seller(1,"Samara","Barba Negra")));
//       q.storeSale(new Sale(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"Juliano","qwerty","barcoza","empresabarcoza"));
//       q.storeTour(new Tour(10, 100, 1000,new Timestamp(System.currentTimeMillis()),"barcao","empresabarco"));
//       q.storeBoat(new Boat("barcao1","empresabarco1",8001,8.09));
//       c
//       q.storeSeller(new Seller("nomevendedor","empresavendedor"));
      /*
      	q.storeEnterprise(new Enterprise("Barba Negra"));
      	q.storeEnterprise(new Enterprise("Pérola Negra"));
      	q.storeEnterprise(new Enterprise("Barba Negra"));
      	q.storeBoat(new Boat("Barba Negra I","Barba Negra",120,50.0));
      	q.storeBoat(new Boat("Barba Negra II","Barba Negra",100,50.0));
      	q.storeBoat(new Boat("Perola Negra I","Perola Negra",200,50.0));
      	q.storeSeller(new Seller("Serjio","Barba Negra"));*/
      
      /*q.storeBoat(new Boat("Holandês Voador","Scuna Sul",150,50.0));
      q.storeBoat(new Boat("Corsário Negro","Scuna Sul",150,50.0));
      q.storeBoat(new Boat("Pirata do Caribe I","Scuna Sul",150,50.0));
      q.storeBoat(new Boat("Pirata do Caribe II","Scuna Sul",150,50.0));
      q.storeBoat(new Boat("Barba Negra I","Barba Negra",85,50.0));
      q.storeBoat(new Boat("Barba Negra II","Barba Negra",150,50.0));
      q.storeBoat(new Boat("Galeão Dourado","Kallmaria",150,50.0));
      q.storeBoat(new Boat("Fantasia","Kallmaria",80,50.0));
      q.storeBoat(new Boat("Pérola Negra","Pérola Negra",200,50.0));
      q.storeBoat(new Boat("Velas Negras","Pérola Negra",120,50.0));
      q.storeBoat(new Boat("Aventura Pirata","Martin",150,50.0));
      q.storeBoat(new Boat("Capitão Gancho","Martin",140,50.0));
      q.storeBoat(new Boat("Pirata da Ilha","Pirata da Ilha",100,50.0));
      
      q.storeSeller(new Seller("Samara","Barba Negra"));
      q.storeSeller(new Seller("Márcia","Barba Negra"));
      q.storeSeller(new Seller("Eduardo","Barba Negra"));
      q.storeSeller(new Seller("Bia","Barba Negra"));
      q.storeSeller(new Seller("Ana","Barba Negra"));
      q.storeSeller(new Seller("Daniele","Barba Negra"));
      q.storeSeller(new Seller("Barba Negra 1","Barba Negra"));
      
      q.storeSeller(new Seller("Fernando","Pirata da Ilha"));
      q.storeSeller(new Seller("Gilson","Pirata da Ilha"));
      q.storeSeller(new Seller("Mila","Pirata da Ilha"));
      q.storeSeller(new Seller("Esteban","Pirata da Ilha"));
      
      q.storeSeller(new Seller("Rocio","Scuna Sul"));
      q.storeSeller(new Seller("Silveira","Scuna Sul"));
      q.storeSeller(new Seller("Elaine","Scuna Sul"));
      q.storeSeller(new Seller("Natália","Scuna Sul"));
      q.storeSeller(new Seller("Cláudio","Scuna Sul"));
      q.storeSeller(new Seller("Alex","Scuna Sul"));
      q.storeSeller(new Seller("Noel","Scuna Sul"));
      q.storeSeller(new Seller("Guilherme","Scuna Sul"));
      q.storeSeller(new Seller("Nô","Scuna Sul"));
      q.storeSeller(new Seller("Scuna Sul 1","Scuna Sul"));
      q.storeSeller(new Seller("Scuna Sul 2","Scuna Sul"));
      
      q.storeSeller(new Seller("Bete","Pérola Negra"));
      q.storeSeller(new Seller("Wilson","Pérola Negra"));
      q.storeSeller(new Seller("Deise","Pérola Negra"));
      q.storeSeller(new Seller("Kleber","Pérola Negra"));
      q.storeSeller(new Seller("Carneiro","Pérola Negra"));
      q.storeSeller(new Seller("Denize","Pérola Negra"));
      q.storeSeller(new Seller("Pérola Negra 1","Pérola Negra"));
      
      q.storeSeller(new Seller("Osni","Kallmaria"));
      q.storeSeller(new Seller("Gisele","Kallmaria"));
      q.storeSeller(new Seller("Gilberto","Kallmaria"));
      q.storeSeller(new Seller("Everaldo","Kallmaria"));
      q.storeSeller(new Seller("Cris","Kallmaria"));
      q.storeSeller(new Seller("Kallmaria 1","Kallmaria"));
      q.storeSeller(new Seller("Kallmaria 2","Kallmaria"));
      
      q.storeSeller(new Seller("Gaston","Martin"));
      q.storeSeller(new Seller("Sofia","Martin"));
      q.storeSeller(new Seller("Esmeralda","Martin"));
      q.storeSeller(new Seller("Neri","Martin"));
      q.storeSeller(new Seller("Flor","Martin"));
      q.storeSeller(new Seller("Jean","Martin"));
      q.storeSeller(new Seller("Tiago","Martin"));
      q.storeSeller(new Seller("Bruna","Martin"));
      q.storeSeller(new Seller("Martin 1","Martin"));
      
      q.storeSeller(new Seller("DIRETO",""));
      
      q.storeEnterprise(new Enterprise("Barba Negra"));
      q.storeEnterprise(new Enterprise("Pirata da Ilha"));
      q.storeEnterprise(new Enterprise("Scuna Sul"));
      q.storeEnterprise(new Enterprise("Pérola Negra"));
      q.storeEnterprise(new Enterprise("Kallmaria"));
      q.storeEnterprise(new Enterprise("Martin"));*/
      
      
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