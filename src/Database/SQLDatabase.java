package Database;

import Background.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SQLDatabase implements DatabaseInterface{
  private String hostname;
  private String username;
  private String password;
  private String schemaName = "qqw";
  
  public SQLDatabase(String hostname, String username, String password) {
    this.hostname = hostname;
    this.username = username;
    this.password = password;
  }
  
  public SQLDatabase(String hostname, String username, String password, String schemaName) {
    this.hostname   = hostname;
    this.username   = username;
    this.password   = password;
    this.schemaName = schemaName;
  }
  
  private Connection connectServer() throws SQLException{
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e){
      System.out.println("jdbc:mysql Driver not founded");
      System.exit(0);
    }
    //return DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.schemaName,this.username,this.password);
    return DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/",this.username,this.password);
  }
  
  private ResultSet getData(String rule) throws SQLException{
    return connectServer().createStatement().executeQuery(rule);
  }
  
  private int insertData(String rule) throws SQLException {
    Connection c = connectServer();
    int i = c.createStatement().executeUpdate(rule);
    c.close();
    return i;
  }
  
  public void createDatabaseIfNoExist() throws SQLException{
    String[] creationCommands = {
      "SET sql_notes = 0;",
      "CREATE DATABASE IF NOT EXISTS `"+this.schemaName+"`;",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`sales` (`idsales` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `sellerName` VARCHAR(100) NOT NULL, `sellerEnterprise` VARCHAR(100) NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, `discountValueTotal` DOUBLE NOT NULL, PRIMARY KEY (`idsales`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`tours` (`idtours` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idtours`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`enterprises` (`identerprises` INT NOT NULL AUTO_INCREMENT,`enterpriseName` VARCHAR(100) NOT NULL, PRIMARY KEY (`identerprises`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`boats` (`idboats` INT NOT NULL AUTO_INCREMENT,`boatName` VARCHAR(100) NOT NULL,`boatEnterprise` VARCHAR(100) NOT NULL,`boatCapacity` INT NOT NULL,`tourCost` DOUBLE NOT NULL,PRIMARY KEY (`idboats`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`sellers` (`idsellers` INT NOT NULL AUTO_INCREMENT, `sellerName` VARCHAR(100) NOT NULL,`sellerEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idsellers`));",
      "SET sql_notes = 1;"
    };
    for (String command : creationCommands){
      insertData(command);
    }
  }
  
  public void addAccess(String ip) throws SQLException{
    this.insertData("GRANT ALL PRIVILEGES ON *.* TO \"root\"@\""+ip+"\" IDENTIFIED BY \"root\";");
  }
  
  private void store(String insertRule) throws SQLException{
    insertData(insertRule);
  }
  
  public String storeSale(Sale sale){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`sales` (`idsales`,`fullPass`,`halfPass`,`freePass`,`departure`,`sellerName`,`sellerEnterprise`,`boatName`,`boatEnterprise`) VALUES ";
    insertRule += "("+sale.insertParameters()+");";
    try{
      this.store(insertRule);
    } catch (SQLException e){
      if (e.getMessage().startsWith("Duplicate entry '") && e.getMessage().endsWith("' for key 'PRIMARY'")){
        return "ID da venda Duplicado";
      }
      e.printStackTrace();
    }
    return "OK";
  }
  
  public String storeTour(Tour tour){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`tours` (`fullPass`,`halfPass`,`freePass`,`departure`,`boatName`,`boatEnterprise`,`discountValueTotal`) VALUES ";
    insertRule += "("+tour.insertParameters()+");";
    try{
      this.store(insertRule);
    } catch (SQLException e){
      e.printStackTrace();
    }
    return "OK";
  }
  
  public String storeBoat(Boat boat){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`boats`(`idboats`,`boatName`,`boatEnterprise`,`boatCapacity`,`tourCost`) VALUES ";
    insertRule += "("+boat.insertParameters()+");";
    try{
      this.store(insertRule);
    } catch (SQLException e){
      if (e.getMessage().startsWith("Duplicate entry '") && e.getMessage().endsWith("' for key 'PRIMARY'")){
        return "ID do barco Duplicado";
      }
      e.printStackTrace();
    }
    return "OK";
  }
  
  public String storeEnterprise(Enterprise enterprise){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`enterprises`(`identerprises`,`enterpriseName`) VALUES ";
    insertRule += "("+enterprise.insertParameters()+");";
    try{
      this.store(insertRule);
    } catch (SQLException e){
      if (e.getMessage().startsWith("Duplicate entry '") && e.getMessage().endsWith("' for key 'PRIMARY'")){
        return "ID da empresa Duplicado";
      }
      e.printStackTrace();
    }
    return "OK";
  }
  
  public String storeSeller(Seller seller){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`sellers`(`idsellers`,`sellerName`,`sellerEnterprise`) VALUES ";
    insertRule += "("+seller.insertParameters()+");";
    try{
      this.store(insertRule);
    } catch (SQLException e){
      if (e.getMessage().startsWith("Duplicate entry '") && e.getMessage().endsWith("' for key 'PRIMARY'")){
        return "ID do vendedor Duplicado";
      }
      e.printStackTrace();
    }
    return "OK";
  }
  
  public List<Sale> loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName){
    // select all(colums) from (table) where
    String rule = "SELECT * FROM `"+this.schemaName+"`.`sales` WHERE";
    boolean runCommand = false;
    if (from != null){
      rule += " departure>="+"\""+from+"\"";
      runCommand = true;
    }
    if (to != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " departure<="+"\""+to+"\"";
      runCommand = true;
    }
    if (sellerName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " sellerName='"+sellerName+"'";
      runCommand = true;
    }
    if (enterpriseName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " enterpriseName='"+enterpriseName+"'";
      runCommand = true;
    }
    if (boatName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatName='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterpriseName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterpriseName='"+boatEnterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`sales`";
    }
    rule += ";";
    ResultSet result;
    List<Sale> sales = new ArrayList<Sale>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return sales;
      }
      do{
        result.next();
        sales.add(new Sale(result));
      }while(!result.isLast());
      result.getStatement().getConnection().close();
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
    return sales;
  }
  
  public List<Tour> loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName){
    // select all(colums) from (table) where
    String rule = "SELECT * FROM `"+this.schemaName+"`.`tours` WHERE";
    boolean runCommand = false;
    if (from != null){
      rule += " departure>="+"\""+from+"\"";
      runCommand = true;
    }
    if (to != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " departure<="+"\""+to+"\"";
      runCommand = true;
    }
    if (boatName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatName='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterpriseName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterpriseName='"+boatEnterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`tours`";
    }
    rule += ";";
    ResultSet result;
    List<Tour> tours = new ArrayList<Tour>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return tours;
      }
      do{
        result.next();
        tours.add(new Tour(result));
      }while(!result.isLast());
      result.getStatement().getConnection().close();
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
    return tours;
  }
  
  public List<Boat> loadBoats(String boatName, String boatEnterprise){
    String rule = "SELECT * FROM `"+this.schemaName+"`.`boats` WHERE";
    boolean runCommand = false;
    if (boatName != null){
      rule += " boatName='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterprise != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterprise='"+boatEnterprise+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`boats`";
    }
    rule += ";";
    ResultSet result;
    List<Boat> boats = new ArrayList<Boat>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return boats;
      }
      do{
        result.next();
        boats.add(new Boat(result));
      }while(!result.isLast());
      result.getStatement().getConnection().close();
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
    return boats;
  }
  
  public Boat loadBoats(int id){
    String rule = "SELECT * FROM `"+this.schemaName+"`.`boats` WHERE idboats="+Integer.toString(id)+";";
    ResultSet result;
    try{
      result = getData(rule);
      if (!result.isBeforeFirst()){
        return null;
      }
      Boat boat = null;
      result.next();
      boat = new Boat(result);
      result.getStatement().getConnection().close();
      return boat;
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
  }
  
  public List<Enterprise> loadEnterprises(String enterpriseName){
    String rule = "SELECT * FROM `"+this.schemaName+"`.`enterprises` WHERE";
    boolean runCommand = false;
    if (enterpriseName != null){
      rule += " enterpriseName='"+enterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`enterprises`";
    }
    rule += ";";
    ResultSet result;
    List<Enterprise> enterprises = new ArrayList<Enterprise>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return enterprises;
      }
      do{
        result.next();
        enterprises.add(new Enterprise(result));
      }while(!result.isLast());
      result.getStatement().getConnection().close();
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
    return enterprises;
  }
  
  public List<Seller> loadSellers(String sellerName, String sellerEnterprise){
    String rule = "SELECT * FROM `"+this.schemaName+"`.`sellers` WHERE";
    boolean runCommand = false;
    if (sellerName != null){
      rule += " sellerName='"+sellerName+"'";
      runCommand = true;
    }
    if (sellerEnterprise != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " sellerEnterprise='"+sellerEnterprise+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`sellers`";
    }
    rule += ";";
    ResultSet result;
    List<Seller> sellers = new ArrayList<Seller>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return sellers;
      }
      do{
        result.next();
        sellers.add(new Seller(result));
      }while(!result.isLast());
      result.getStatement().getConnection().close();
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
    return sellers;
  }
  
  public Seller loadSellers(int id){
    String rule = "SELECT * FROM `"+this.schemaName+"`.`sellers` WHERE `idsellers`="+Integer.toString(id)+";";
    ResultSet result;
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return null;
      }
      Seller seller;
      result.next();
      seller = new Seller(result);
      result.getStatement().getConnection().close();
      return seller;
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
      return null;
    }
  }
  
  public boolean deleteTour(Tour toDelete){
    String deleteRule = "DELETE FROM `"+this.schemaName+"`.`tours` WHERE `idtours`="+Integer.toString(toDelete.id())+";";
    try{
      this.store(deleteRule);
    } catch (SQLException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public boolean deleteSale(Sale toDelete){
	    String deleteRule = "DELETE FROM `"+this.schemaName+"`.`sales` WHERE `idsales`="+Integer.toString(toDelete.id())+";";
	    try{
	      this.store(deleteRule);
	    } catch (SQLException e){
	      e.printStackTrace();
	      return false;
	    }
	    return true;
  }
  
  public boolean deleteBoat(Boat toDelete){
    String deleteRule = "DELETE FROM `"+this.schemaName+"`.`boats` WHERE `idboats`="+Integer.toString(toDelete.id())+";";
    try{
      this.store(deleteRule);
    } catch (SQLException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public boolean deleteEnterprise(Enterprise toDelete){
    String deleteRule = "DELETE FROM `"+this.schemaName+"`.`enterprises` WHERE `identerprises`="+Integer.toString(toDelete.id())+";";
    try{
      this.store(deleteRule);
    } catch (SQLException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public boolean deleteSeller(Seller toDelete){
    String deleteRule = "DELETE FROM `"+this.schemaName+"`.`sellers` WHERE `idsellers`="+Integer.toString(toDelete.id())+";";
    try{
      this.store(deleteRule);
    } catch (SQLException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
}