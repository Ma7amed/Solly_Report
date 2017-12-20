package sample.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by m80028770 on 7/25/2017.
 */
public class ModelDB {


    private Connection con;

    public static void main(String[] args) {

     //   ModelDB modelDB = new ModelDB();
     //   modelDB.connect();

        Runtime rt = Runtime.getRuntime();


        try {
            Process pr = rt.exec("route print");
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));

            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void connect() {

        Statement stmt= null;


        try {
            // Connect
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
            con = DriverManager.getConnection(
                    "jdbc:sybase:Tds:10.76.2.55:4100", "sa", "emsems");

            System.out.println("ModelDB.connect: " + con);

            // Query

           stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery( "select count(*) as test from logdb..tbl_SysLog");
            while ( rs.next( ) ) {
                System.out.println( rs.getString("test") );
            }
           // ResultSet rs = stmt.executeQuery(sql_select_solly_tt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
