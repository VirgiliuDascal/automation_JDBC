import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Problem2_Update {
    public static void  main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;
        Integer ur = 0;
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/automation_practice?" +
                            "user=root&password=myNewPass");
            stmt = conn.createStatement();
            ur = stmt.executeUpdate("UPDATE sales set OrderQty = 2 * OrderQty");


            if (stmt.execute("SELECT * FROM sales")) {
            rs = stmt.getResultSet();

                while(rs.next()) {
                    Integer salesOrderId = rs.getInt("SalesOrderId");
                    Integer unitPrice = rs.getInt("UnitPrice");
                    Integer orderQty = rs.getInt("OrderQty");
                    System.out.printf("The salesOrderId %d is in %d and has OrderQty - %d%n", salesOrderId, unitPrice,orderQty);
                }
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
