package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.OracleHelper;
import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import oracle.sql.CLOB;

public class HelperConnection {

  private String jdbcURL = null;
  private Map<String, String> properties = null;
  private Connection connection = null;
  private java.util.List<HelperQuery> queuedQueries = new java.util.ArrayList<>();
  private java.util.List<HelperQuery> batchQueries = new java.util.ArrayList<>();
  private java.util.List<HelperQuery> executedQueries = new java.util.ArrayList<>();

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

  public ResultSet executeQuery(HelperQuery helperQuery) throws SQLException {
    //Prepare query
    PreparedStatement pstmt = getOrCreateConnection().prepareStatement(helperQuery.getQuery());
    for (int i = 1; i <= helperQuery.getParameters().length; i++) {
      QueryParameter param = helperQuery.getParameters()[i - 1];
      switch (param.getType()) {
        case QueryParameter.CLOB:
          Clob clob = CLOB.createTemporary(connection, false, CLOB.DURATION_SESSION);
          clob.setString(i, param.getValue());
          pstmt.setClob(i, clob);
          break;
        case QueryParameter.STRING:
          pstmt.setString(i, param.getValue());
          break;
      }
    }

    //Execute query
    ResultSet rs = pstmt.executeQuery();
    connection.commit();

    //Allocate query and result set
    queuedQueries.remove(helperQuery);
    executedQueries.add(helperQuery);
    return rs;
  }

  /**
   * Adds the given queries to the batchQueries list and calls executeBatch()
   *
   * @param helperQuery
   */
  public void executeBatch(HelperQuery... helperQuery) {
    batchQueries.addAll(Arrays.asList(helperQuery));
    executeBatch();
  }

  public void executeBatch() {
    batchQueries.clear();
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

  /**
   * @return the queuedQueries
   */
  public java.util.List<HelperQuery> getQueuedQueries() {
    return queuedQueries;
  }

  /**
   * @param queuedQueries the queuedQueries to set
   */
  public void setQueuedQueries(java.util.List<HelperQuery> queuedQueries) {
    this.queuedQueries = queuedQueries;
  }

  /**
   * @return the executedQueries
   */
  public java.util.List<HelperQuery> getExecutedQueries() {
    return executedQueries;
  }

  /**
   * @param executedQueries the executedQueries to set
   */
  public void setExecutedQueries(java.util.List<HelperQuery> executedQueries) {
    this.executedQueries = executedQueries;
  }
}
