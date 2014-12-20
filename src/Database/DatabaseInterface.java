package Database;

import Background.*;
import java.sql.*;
import java.util.List;

public interface DatabaseInterface{
  List<Sale> loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName);
  String storeSale(Sale sale);
  List<Tour> loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName);
  String storeTour(Tour tour);
  List<Boat> loadBoats(String boatName, String boatEnterprise);
  String storeBoat(Boat boat);
  List<Enterprise> loadEnterprises(String enterpriseName);
  String storeEnterprise(Enterprise enterprise);
  List<Seller> loadSellers(String sellerName, String sellerEnterprise);
  String storeSeller(Seller seller);
}