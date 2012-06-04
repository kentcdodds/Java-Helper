package com.kentcdodds.javahelper.model;

import java.sql.ResultSet;

/**
 * Javabean for the SQLHelper. Used in HelperConnection
 *
 * @author kentcdodds
 */
public class HelperQuery {

  private String query;
  private java.util.List<QueryParameter[]> parametersList;
  private ResultSet resultSet;

  /**
   * Empty Constructor. Be sure to set the query and parameters.
   */
  public HelperQuery() {
    parametersList = new java.util.ArrayList<>();
  }

  /**
   * Partial constructor. Fills the query parameters with one array of parameters.
   *
   * @param connection
   * @param query
   * @param parameters
   */
  public HelperQuery(String query, QueryParameter... parameters) {
    this.query = query;
    parametersList = new java.util.ArrayList<>();
    parametersList.add(parameters);
  }

  /**
   * Full constructor except for the result set. This is set by the HelperConnection which runs it when it is executed.
   *
   * @param connection
   * @param query
   * @param parameters
   */
  public HelperQuery(String query, java.util.List<QueryParameter[]> parametersList) {
    this.query = query;
    this.parametersList = parametersList;
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
  public java.util.List<QueryParameter[]> getParametersList() {
    return parametersList;
  }

  /**
   * @param parameters the parameters to set
   */
  public void setParametersList(java.util.List<QueryParameter[]> parameters) {
    this.parametersList = parameters;
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
