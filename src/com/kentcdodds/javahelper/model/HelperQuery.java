package com.kentcdodds.javahelper.model;

import java.sql.ResultSet;

/**
 * Javabean for the OracleHelper. Used in HelperConnection
 * @author kentcdodds
 */
public class HelperQuery {

  private String query;
  private QueryParameter[] parameters;
  private ResultSet resultSet;

  /**
   * Empty Constructor. Be sure to set the query and parameters.
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
  public HelperQuery(String query, QueryParameter... parameters) {
    this.query = query;
    this.parameters = parameters;
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
  public ResultSet getResultSet() {
    return resultSet;
  }

  /**
   * @param resultSet the ResultSet to set
   */
  public void setResultSet(ResultSet resultSet) {
    this.resultSet = resultSet;
  }
}
