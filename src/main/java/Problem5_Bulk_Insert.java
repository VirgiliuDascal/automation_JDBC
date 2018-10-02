import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class Problem5_Bulk_Insert {
    public static void  main(String[] args) throws IOException, ClassNotFoundException, SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        Integer ur = 0;
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/automation_practice?" +
                            "user=root&password=myNewPass");
            stmt = conn.createStatement();
            String query = "Insert into Sales values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement();
            st.executeUpdate(query);


            try {
                BufferedReader bReader = new BufferedReader(new FileReader("/Users/vdascal/Documents/DataForInsert.csv"));
                String line = "";
                while ((line = bReader.readLine()) != null) {
                    try {

                        if (line != null)
                        {
                            String[] array = line.split(",+");
                            for(String result:array)
                            {
                                System.out.println(result);
                                //Create preparedStatement here and set them and excute them
                                PreparedStatement ps = yourConnecionObject.createPreparedStatement(sql);
                                ps.setString(1,str[0]);
                                ps.setString(2,str[1]);
                                ps.setString(3,str[2]);
                                ps.setString(4,strp[3])
                                ps.excuteUpdate();
                                ps. close()
                                //Assuming that your line from file after split will folllow that sequence

                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        if (bReader == null)
                        {
                            bReader.close();
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
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
