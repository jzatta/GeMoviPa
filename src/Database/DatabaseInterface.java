package Database;

import Background.*;
import java.sql.*;
import java.util.List;

public interface DatabaseInterface{
  List<Sale> loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName);
  void storeSale(Sale sale);
  List<Tour> loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName);
  void storeTour(Tour tour);
  List<Boat> loadBoats(String boatName, String boatEnterprise);
  void storeBoat(Boat boat);
  List<Enterprise> loadEnterprises(String enterpriseName);
  void storeEnterprise(Enterprise enterprise);
}