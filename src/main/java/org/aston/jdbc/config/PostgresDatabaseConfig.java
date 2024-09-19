package org.aston.jdbc.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDatabaseConfig implements JdbcConnection {
    Configurations configs = new Configurations();
    Configuration config = configs.properties(new File("src/java/resources/application.properties"));
    String dbHost = config.getString("db.host");
    int dbPort = config.getInt("server.port");
    String dbUser = config.getString("db.username");
    String dbPassword = config.getString("db.password");
    String dbDriver = config.getString("db.driver");

    public PostgresDatabaseConfig() throws ConfigurationException {
    }

    @Override
    public Connection getConnection()  {
        try {
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbHost, dbUser, dbPassword);
        }
        catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
