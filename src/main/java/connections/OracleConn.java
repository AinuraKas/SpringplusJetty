/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Locale;

/**
 *
 * @author Ainura_Andr
 */
public class OracleConn {

    public Connection getConn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver failed");
            e.printStackTrace();
            return null;
        }
        Connection conn = null;
        try {

            Locale.setDefault(Locale.ENGLISH);

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ai", "xl");
            DatabaseMetaData dbmd = conn.getMetaData();
            String driverName = dbmd.getDriverName();
            System.out.println("Database Driver Name is " + driverName);
        } catch (Exception ex) {
            System.out.println("Conn failed");
            ex.printStackTrace();
            return null;
        }

        return conn;
    }
}
