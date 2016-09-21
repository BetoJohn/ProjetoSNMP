/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.snmp.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author carlos.macedo
 */
public class ConnectionDB {
    
    public final static String DATASOURCE_PROPERTIES_FILE = "datasource.properties";
    private final static String CONFIG_NAME = "ProjetoSnmp";
    private final static String DRIVER_CLASS = "org.postgresql.Driver";
    private final static String JDBC_URL = "jdbc:postgresql://localhost:5432/projetosnmpdb";
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "postgres";
    private final static int ACQUIRE_INCREMENT = 1;
    private final static int INITIAL_POOL_SIZE = 1;
    private final static int MAX_POOL_SIZE = 20;
    private final static int MIN_POOL_SIZE = 1;
    private final static int MAX_STATEMENTS = 200;
    private final static int TIME_OUT = 5000;
    private final static String AUTO_COMMIT = "true";
    private final static int IDLE_CONNECTION_TEST = 60;
    private final static int MAX_CONNECTION_AGE = 300;
    private final static int MAX_IDLE_TIME = 300;
    private final static int MAX_IDLE_TIME_EXECESS_CONNECTIONS = 300;

    private static ConnectionDB connection;
    private ComboPooledDataSource cpds;

    private ConnectionDB() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(DRIVER_CLASS); //loads the jdbc driver
        cpds.setJdbcUrl(JDBC_URL);
        cpds.setUser(USER_NAME);
        cpds.setPassword(PASSWORD);

        // the settings below are optional -- c3p0 can work with defaults
        try {
            cpds.setMinPoolSize(MIN_POOL_SIZE);
        } catch (Exception e) {
        }
        try {
            cpds.setAcquireIncrement(ACQUIRE_INCREMENT);
        } catch (Exception e) {
        }
        try {
            cpds.setMaxPoolSize(MAX_POOL_SIZE);
        } catch (Exception e) {
        }
        try {
            cpds.setMaxStatements(MAX_STATEMENTS);

        } catch (Exception e) {
        }
        try {
            cpds.setAutoCommitOnClose("true".equalsIgnoreCase(AUTO_COMMIT));

        } catch (Exception e) {
        }
        try {
            cpds.setIdleConnectionTestPeriod(IDLE_CONNECTION_TEST);

        } catch (Exception e) {
        }
        try {
            cpds.setMaxConnectionAge(MAX_CONNECTION_AGE);

        } catch (Exception e) {
        }
        try {
            cpds.setMaxIdleTime(MAX_IDLE_TIME);

        } catch (Exception e) {
        }
        try {
            cpds.setMaxIdleTimeExcessConnections(MAX_IDLE_TIME_EXECESS_CONNECTIONS);

        } catch (Exception e) {
        }
    }

  

    public static ConnectionDB getInstance() throws Exception {
        if (connection == null) {
            connection = new ConnectionDB();
        }
        return connection;
    }

    public java.sql.Connection getConnection() throws SQLException {
        return this.getCpds().getConnection();
    }

    /**
     * @return the cpds
     */
    public ComboPooledDataSource getCpds() {
        return cpds;
    }
}
