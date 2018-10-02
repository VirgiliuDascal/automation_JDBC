import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Problem3_Delete {
    public static void  main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;
        Integer updatedRows = 0;
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/automation_practice?" +
                            "user=root&password=myNewPass");
            stmt = conn.createStatement();
            updatedRows = stmt.executeUpdate("DELETE from Sales where UnitPrice < 160.00 ");


            if (stmt.execute("SELECT * FROM sales")) {
                rs = stmt.getResultSet();

                System.out.println("SalesOrderID, SalesOrderDetailID, OrderQty, ProductID, UnitPrice, UnitPriceDiscount, rowguid, ModifiedDate");
                while(rs.next()) {
                    Integer salesOrderID = rs.getInt("SalesOrderId");
                    Integer salesOrderDetailID = rs.getInt("SalesOrderDetailID");
                    Integer orderQty = rs.getInt("OrderQty");
                    Integer productID = rs.getInt("ProductID");
                    Double unitPrice = rs.getDouble("UnitPrice");
                    Integer unitPriceDiscount = rs.getInt("UnitPriceDiscount");
                    Integer rowguID = rs.getInt("rowguid");
                    Timestamp modifiedDate = rs.getTimestamp("ModifiedDate");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                    System.out.printf("%d - %d - %d - %d - %-10.2f - %d - %n",
                            salesOrderID,salesOrderDetailID,orderQty,productID,unitPrice,unitPriceDiscount,rowguID,dateFormat.format(modifiedDate));
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
