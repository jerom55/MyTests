package Topiframe.AppManager;

import org.testng.annotations.Test;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;

public class DateBase {

    @Test

    public void testDbConnection() throws ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection conn = null;
    try{
        conn = DriverManager.getConnection("jdbc:sqlserver://192.168.20.237;databaseName=MAINDB;user=s.ivanov@ruru.ru;password=AB652@77dc52");

        Statement stmt = conn.createStatement();
    } catch (SQLException ex){
        System.out.println("SQLException " + ex.getMessage());
        System.out.println("SqLState " + ex.getSQLState());
        System.out.println("VendorError " + ex.getErrorCode());
         }
    }
}
