import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;



public class TestConnection {
    public static void  main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;

        Connection conn = null;



        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/automation_practice?" +
                            "user=root&password=myNewPass");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM sales");


            //if (stmt.execute("SELECT * FROM sales")) {
                rs = stmt.getResultSet();

                while(rs.next()) {
                    Integer salesOrderId = rs.getInt("SalesOrderId");
                    Integer unitPrice = rs.getInt("UnitPrice");
                    System.out.printf("The salesOrderId %d is in %d%n", salesOrderId, unitPrice);
                }




        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }


    }
}
