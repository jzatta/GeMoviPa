package Database;

import Background.*;
import java.sql.*;
import java.util.List;

public interface DatabaseInterface{
  // Database.Sale completely different of background.Sale
  List<Sale> loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName);
  void storeSale(Sale sale);
  // Database.Tour completely different of background.Tour
  List<Tour> loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName);
  void storeTours(Tour tour);
}