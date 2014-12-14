package Background;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seller{
  private int idSeller;
  private String sellerName, sellerEnterprise;
  
  public Seller(ResultSet result) throws SQLException{
    this.idSeller           = result.getInt("idsellers");
    this.sellerName         = result.getString("sellerName");
    this.sellerEnterprise   = result.getString("sellerEnterprise");
  }
  
  public Seller(String sellerName, String sellerEnterprise){
    this.sellerName       = sellerName;
    this.sellerEnterprise = sellerEnterprise;
  }
  
  public String enterpriseName(){
  	return this.sellerEnterprise;
  }
  
  public String insertParameters(){
    return "\""+this.sellerName+"\","+
    "\""+this.sellerEnterprise+"\"";
  }
  
  public String toString(){
    return sellerName;
  }

  public String toDebug(){
    return Integer.toString(idSeller) +"/"+ sellerName +"/"+ sellerEnterprise;
  }
}
