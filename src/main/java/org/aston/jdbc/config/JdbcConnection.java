package org.aston.jdbc.config;

import java.sql.Connection;

public interface JdbcConnection {
    Connection getConnection();
}
