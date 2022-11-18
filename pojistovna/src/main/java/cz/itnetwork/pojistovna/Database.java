/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.pojistovna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tato třída reprezentuje připojení a uzavření spojení mezi aplikací a sql
 * databází. Využívá metodu třídy Connection.
 *
 * @author Marketa Vokacova
 */
public class Database implements AutoCloseable {

    /**
     * Proměnná třídy Connection reprezentujicí spojení s sql databází.
     */
    private Connection connection;

    /**
     * Konstruktor iniciaizující připojení k sql databázi po spuštění aplikace.
     *
     * @param urlDatabase Odkaz pro připojení k sql databázi.
     * @throws SQLException
     */
    public Database(String urlDatabase) throws SQLException {
        connection = DriverManager.getConnection(urlDatabase);
    }

    /**
     * Metoda zavírajicí spojení mezi aplikací a databází.
     *
     * @throws java.sql.SQLException
     */
    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

}
