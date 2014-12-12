package Database;

interface DatabaseInterface{
  // Database.Sale completely different of background.Sale
  Sale[] loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName);
  void storeSale(Sale sale);
  // Database.Tour completely different of background.Tour
  Tour[] loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName);
  void storeTours(Tour tour);
}