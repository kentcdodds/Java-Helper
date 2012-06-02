package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.OracleHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperQuery {

  private HelperConnection connection;
  private String query;
  private QueryParameter[] parameters;
  private ResultSet rs;

  /**
   * Empty Constructor. Be sure to set the connection, query and parameters.
   */
  public HelperQuery() {
  }

  /**
   * Full constructor except for the result set. This is set by the HelperConnection which runs it when it is executed.
   *
   * @param connection
   * @param query
   * @param parameters
   */
  public HelperQuery(HelperConnection connection, String query, QueryParameter... parameters) {
    this.connection = connection;
    this.query = query;
    this.parameters = parameters;
  }

  /**
   * Calls the connection's executeQuery method on this.
   *
   * @return
   * @throws SQLException
   */
  public ResultSet execute() throws SQLException {
    return connection.executeQuery(this);
  }

  /**
   * @return the connection
   */
  public HelperConnection getConnection() {
    return connection;
  }

  /**
   * @param connection the connection to set
   */
  public void setConnection(HelperConnection connection) {
    this.connection = connection;
  }

  /**
   * @return the query
   */
  public String getQuery() {
    return query;
  }

  /**
   * @param query the query to set
   */
  public void setQuery(String query) {
    this.query = query;
  }

  /**
   * @return the parameters
   */
  public QueryParameter[] getParameters() {
    return parameters;
  }

  /**
   * @param parameters the parameters to set
   */
  public void setParameters(QueryParameter[] parameters) {
    this.parameters = parameters;
  }

  /**
   * @return the rs
   */
  public ResultSet getRs() {
    return rs;
  }

  /**
   * @param rs the rs to set
   */
  public void setRs(ResultSet rs) {
    this.rs = rs;
  }
}
