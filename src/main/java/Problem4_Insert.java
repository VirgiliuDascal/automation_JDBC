import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Problem4_Insert {
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
            ur = stmt.executeUpdate("INSERT INTO " +
                    "Sales (SalesOrderID,SalesOrderDetailID,OrderQty,ProductID,UnitPrice,UnitPriceDiscount,rowguid,ModifiedDate) " +
                    "values (26280,110567,1,905,140.90,0.00,'3','2008-06-01 00:00:00.000')");


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
