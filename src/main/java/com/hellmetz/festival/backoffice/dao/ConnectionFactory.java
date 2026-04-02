package com.hellmetz.festival.backoffice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String URL_BDD;
    private static final String UTILISATEUR_BDD;
    private static final String MOT_DE_PASSE_BDD;
    private static final String DRIVER;

    // 1. L'instance unique de la connexion
    private static Connection connection = null;

    static {
        try {
            Properties proprietes = new Properties();
            proprietes.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            URL_BDD          = proprietes.getProperty("db.url");
            UTILISATEUR_BDD  = proprietes.getProperty("db.user");
            MOT_DE_PASSE_BDD = proprietes.getProperty("db.password");
            DRIVER           = proprietes.getProperty("db.driver");

            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL introuvable", e);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger le fichier db.properties", e);
        }
    }

    // 2. Constructeur privé pour empêcher toute instanciation externe
    private ConnectionFactory() {
    }

    // 3. Méthode d'accès modifiée pour le Singleton
    public static Connection getConnection() throws SQLException {
        // On vérifie si la connexion est nulle ou fermée avant de la (re)créer
        if (connection == null || connection.isClosed()) {
            synchronized (ConnectionFactory.class) { // Thread-safe
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(URL_BDD, UTILISATEUR_BDD, MOT_DE_PASSE_BDD);
                }
            }
        }
        return connection;
    }
}