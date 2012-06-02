package com.kentcdodds.javahelper.model;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 * Helper object for the OracleHelper. Has several helpful methods of its own.
 *
 * @author kentcdodds
 */
public class HelperConnection {

  private String jdbcURL = null;
  private Map<String, String> properties = null;
  private Connection connection = null;

  /**
   * Empty constructor, be sure to set jdbcURL and properties map
   */
  public HelperConnection() {
  }

  /**
   * Full constructor
   *
   * @param jdbcURL
   * @param properties
   */
  public HelperConnection(String jdbcURL, Map<String, String> properties) {
    this.jdbcURL = jdbcURL;
    this.properties = properties;
  }

  /**
   * Creates a connection on the url with the given props
   *
   * @param url
   * @param props
   * @return
   * @throws Exception
   */
  public Connection getOrCreateConnection() throws SQLException {
    if (connection == null
            || connection.isClosed()) {
      Properties connectionProps = new Properties();
      for (Map.Entry<String, String> entry : properties.entrySet()) {
        connectionProps.put(entry.getKey(), entry.getValue());
      }
      connection = DriverManager.getConnection(jdbcURL, connectionProps);
      connection.setAutoCommit(false);
    }
    return connection;
  }

  /**
   * Gets or creates a connection and prepares a statement on it with the given query.
   *
   * @param query
   * @return
   * @throws SQLException
   */
  public PreparedStatement prepareStatement(String query) throws SQLException {
    return getOrCreateConnection().prepareStatement(query);
  }

  /**
   * @return the jdbcURL
   */
  public String getJdbcURL() {
    return jdbcURL;
  }

  /**
   * @param jdbcURL the jdbcURL to set
   */
  public void setJdbcURL(String jdbcURL) {
    this.jdbcURL = jdbcURL;
  }

  /**
   * @return the properties
   */
  public Map<String, String> getProperties() {
    return properties;
  }

  /**
   * @param properties the properties to set
   */
  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }

  /**
   * @return the connection
   */
  public Connection getConnection() {
    return connection;
  }

  /**
   * @param connection the connection to set
   */
  public void setConnection(Connection connection) {
    this.connection = connection;
  }
}
