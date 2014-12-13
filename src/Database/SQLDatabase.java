package Database;

import Background.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SQLDatabase implements DatabaseInterface{
  private String hostname;
  private String username;
  private String password;
  private String schemaName = "huehueBRBR";
  
  public SQLDatabase(String hostname, String username, String password) {
    this.hostname = hostname;
    this.username = username;
    this.password = password;
  }
  
  private Connection connectServer() throws SQLException{
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e){
      System.out.println("jdbc:mysql Driver not founded");
      System.exit(0);
    }
    return DriverManager.getConnection("jdbc:mysql://"+this.hostname,this.username,this.password);
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
  
  // not working
  public void createDatabaseIfNoExist() throws SQLException{
    String[] creationCommands = {
      "SET sql_notes = 0;",
      "CREATE DATABASE IF NOT EXISTS `"+this.schemaName+"`;",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`sales` (`idsales` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `sellerName` VARCHAR(100) NOT NULL, `sellerEnterprise` VARCHAR(100) NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idsales`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`tour` (`idtour` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idtour`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`enterprises` (`identerprises` INT NOT NULL,`enterpriseName` VARCHAR(100) NOT NULL, PRIMARY KEY (`identerprises`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`boat` (`idboat` INT NOT NULL,`boatName` VARCHAR(100) NOT NULL,`boatEnterprise` VARCHAR(100) NOT NULL,`boatCapacity` INT NOT NULL,`tourCost` DOUBLE NOT NULL,PRIMARY KEY (`idboat`));",
      "SET sql_notes = 1;"
    };
    for (String command : creationCommands){
      insertData(command);
    }
  }
  
  public void storeSale(Sale sale){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`sales` (`fullPass`,`halfPass`,`freePass`,`departure`,`sellerName`,`sellerEnterprise`,`boatName`,`boatEnterprise`) VALUES ";
    insertRule += "("+sale.insertParameters()+");";
    try{
      insertData(insertRule);
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
    }
  }
  
  public void storeTour(Tour tour){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`tour` (`fullPass`,`halfPass`,`freePass`,`departure`,`boatName`,`boatEnterprise`) VALUES ";
    insertRule += "("+tour.insertParameters()+");";
    try{
      insertData(insertRule);
    } catch (SQLException e){
      e.printStackTrace();
      System.out.println("Error access Databank");
    }
  }
  
  public void storeBoat(Boat boat);
  public void storeEnterprise(Enterprise enterprise);
  
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
    String rule = "SELECT * FROM `"+this.schemaName+"`.`tour` WHERE";
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
      rule = "SELECT * FROM `"+this.schemaName+"`.`tour`";
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
    String rule = "SELECT * FROM `"+this.schemaName+"`.`boat` WHERE";
    boolean runCommand = false;
    if (boatName != null){
      rule += " boatName='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterprise != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterpriseName='"+boatEnterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM `"+this.schemaName+"`.`boat`";
    }
    rule += ";";
    ResultSet result;
    List<Boat> boats = new ArrayList<Boat>();
    try{
      result = getData(rule);
      if (!result.isBeforeFirst() ){
        return tours;
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
  
  public List<Enterprise> loadBoats(String enterpriseName);
}