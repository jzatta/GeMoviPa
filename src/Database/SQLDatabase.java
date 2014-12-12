package Database;

import Background.*;
import java.sql.*;
import java.util.List;

public class SQLDatabase implements DatabaseInterface{
  private String hostname;
  private String username;
  private String password;
  private String schemaName = "\"+this.schemaName+\"";
  
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
    return DriverManager.getConnection("jdbc:mysql://"+this.hostname+"/"+this.schemaName,this.username,this.password);
  }
  
  private ResultSet getData(String rule) throws SQLException{
    return connectServer().createStatement().executeQuery(rule);
  }
  
  public void createDatabaseIfNoExist() throws SQLException{
    String[] creationCommands = {
      "CREATE SCHEMA IF NOT EXISTS `"+this.schemaName+"`;",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`sales` (`idsales` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `sellerName` VARCHAR(100) NOT NULL, `sellerEnterprise` VARCHAR(100) NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idsales`));",
      "CREATE TABLE IF NOT EXISTS `"+this.schemaName+"`.`tour` (`idtour` INT NOT NULL AUTO_INCREMENT, `fullPass` INT NOT NULL, `halfPass` INT NOT NULL, `freePass` INT NOT NULL, `departure` TIMESTAMP NOT NULL, `boatName` VARCHAR(100) NOT NULL, `boatEnterprise` VARCHAR(100) NOT NULL, PRIMARY KEY (`idtour`));"
    };
    for (String command : creationCommands){
      System.out.println(getData(command).toString());
    }
  }
  
  public void storeSale(Sale sale){
    String insertRule = "INSERT INTO `"+this.schemaName+"`.`tour` (`fullPass`,`halfPass`,`freePass`,`departure`,`boatName`,`boatEnterprise`) VALUES ";
    insertRule += "("+sale.insertParameters()+");";
  }
  
  public void storeTours(Tour tour){
    
  }
  
  public List<Sale> loadSales(Timestamp from, Timestamp to, String sellerName, String enterpriseName, String boatName, String boatEnterpriseName){
    // select all(colums) from (table) where
    String rule = "SELECT * FROM `"+this.schemaName+"`.`sales` WHERE";
    boolean runCommand = false;
    if (from != null){
      rule += " departure>="+from;
      runCommand = true;
    }
    if (to != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " departure<="+to;
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
      rule += " enterpriseName<='"+enterpriseName+"'";
      runCommand = true;
    }
    if (boatName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatName<='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterpriseName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterpriseName<='"+boatEnterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM Sales";
    }
    rule += ";";
    ResultSet result;
    try{
      result = getData(rule);
    } catch (SQLException e){
      System.out.println("Error access Databank");
    }
    
    // process data
    
    return null;
  }
  
  public List<Tour> loadTours(Timestamp from, Timestamp to, String boatName, String boatEnterpriseName){
    // select all(colums) from (table) where
    String rule = "SELECT * FROM `"+this.schemaName+"`.`tour` WHERE";
    boolean runCommand = false;
    if (from != null){
      rule += " departure>="+from;
      runCommand = true;
    }
    if (to != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " departure<="+to;
      runCommand = true;
    }
    if (boatName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatName<='"+boatName+"'";
      runCommand = true;
    }
    if (boatEnterpriseName != null){
      if (runCommand){
        rule += " AND";
      }
      rule += " boatEnterpriseName<='"+boatEnterpriseName+"'";
      runCommand = true;
    }
    if (!runCommand){
      rule = "SELECT * FROM Tour";
    }
    rule += ";";
    ResultSet result;
    try{
      result = getData(rule);
    } catch (SQLException e){
      System.out.println("Error access Databank");
    }
    
    // process data
    
    return null;
  }
}