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
    this.idSeller         = 0;
    this.sellerName       = sellerName;
    this.sellerEnterprise = sellerEnterprise;
  }
  
  public Seller(int idSeller, String sellerName, String sellerEnterprise){
    this.idSeller         = idSeller;
    this.sellerName       = sellerName;
    this.sellerEnterprise = sellerEnterprise;
  }
  
  public String enterpriseName(){
  	return this.sellerEnterprise;
  }
  
  public int id(){
	  return this.idSeller;
  }
  
  public String insertParameters(){
    return Integer.toString(this.idSeller)+",\""+this.sellerName+"\","+
    "\""+this.sellerEnterprise+"\"";
  }
  
  public String toString(){
    return sellerName;
  }

  public String toDebug(){
    return Integer.toString(idSeller) +"/"+ sellerName +"/"+ sellerEnterprise;
  }
}
