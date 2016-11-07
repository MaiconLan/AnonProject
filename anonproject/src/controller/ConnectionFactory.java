/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.postgresql.Driver
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://192.168.101.168/anonproject";
        DriverManager.registerDriver((java.sql.Driver)new Driver());
        return DriverManager.getConnection(url, "postgres", "postgres");
    }
}
