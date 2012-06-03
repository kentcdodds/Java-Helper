package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.OtherHelper;
import java.sql.*;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.CLOB;

/**
 * Helper object for the OracleHelper. Has several helpful methods of its own.
 *
 * @author kentcdodds
 */
public class HelperConnection {

  private String jdbcURL = null;
  private Map<String, String> properties = null;
  private Connection connection = null;
  private java.util.List<HelperQuery> queuedQueries = new java.util.ArrayList<>();
  private java.util.List<HelperQuery> errorQueries = new java.util.ArrayList<>();
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

  /**
   * Executes the given HelperQuery on this connection based on the HelperQuery's query and QueryParameters
   * @param helperQuery
   * @return
   * @throws SQLException 
   */
  public ResultSet executeQuery(HelperQuery helperQuery) throws SQLException {
    //Prepare query
    PreparedStatement pstmt = prepareStatement(helperQuery.getQuery());
    fillStatement(pstmt, helperQuery.getParameters());

    ResultSet rs = executeStatement(pstmt);
    queuedQueries.remove(helperQuery);
    executedQueries.add(helperQuery);
    helperQuery.setResultSet(rs);
    return rs;
  }

  /**
   * Executes the given statement and returns the result set
   *
   * @param pstmt
   * @return the result set of the execution
   * @throws SQLException when executing the statement
   */
  public ResultSet executeStatement(PreparedStatement pstmt) throws SQLException {
    ResultSet rs = pstmt.executeQuery();
    connection.commit();
    return rs;
  }

  /**
   * Applies the parameters to the statement
   *
   * @param pstmt the PreparedStatement to fill
   * @param params the parameters to fill the pstmt with
   * @throws SQLException when applying the parameters
   */
  public void fillStatement(PreparedStatement pstmt, QueryParameter... params) throws SQLException {
    for (int i = 1; i <= params.length; i++) {
      QueryParameter param = params[i - 1];
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

  public java.util.List<HelperQuery> executeQueue() {
    java.util.List<HelperQuery> returnList = new java.util.ArrayList<>(queuedQueries.size());
    for (HelperQuery helperQuery : queuedQueries) {
      try {
        executeQuery(helperQuery);
        returnList.add(helperQuery);
      } catch (SQLException ex) {
        Logger.getLogger(HelperConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    OtherHelper.removeThisFromThat(returnList, queuedQueries);
    return returnList;
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

  /**
   * @return the errorQueries
   */
  public java.util.List<HelperQuery> getErrorQueries() {
    return errorQueries;
  }

  /**
   * @param errorQueries the errorQueries to set
   */
  public void setErrorQueries(java.util.List<HelperQuery> errorQueries) {
    this.errorQueries = errorQueries;
  }
}
