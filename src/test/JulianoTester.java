package test;

import Background.Sale;
import Database.*;
import java.sql.*;


public class JulianoTester{
	public static void main(String[] args) throws Exception{
    SQLDatabase q = new SQLDatabase("127.0.0.1","root","root");
    q.createDatabaseIfNoExist();
	}
	
}