package com.kentcdodds.javahelper.model;

import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.sql.CLOB;

/**
 * Javabean for the OracleHelper. Used in HelperConnection
 * @author kentcdodds
 */
public class HelperQuery {

  private HelperConnection connection;
  private String query;
  private QueryParameter[] parameters;
  private ResultSet resultSet;

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
   * Executes the given statement and returns the result set
   *
   * @param pstmt
   * @return the result set of the execution
   * @throws SQLException when executing the statement
   */
  public ResultSet executeStatement(PreparedStatement pstmt) throws SQLException {
    ResultSet rs = pstmt.executeQuery();
    connection.getConnection().commit();
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
          Clob clob = CLOB.createTemporary(connection.getConnection(), false, CLOB.DURATION_SESSION);
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
   * Calls the connection's executeQuery method on this.
   *
   * @return
   * @throws SQLException
   */
  public ResultSet execute() throws SQLException {
    //Prepare query
    PreparedStatement pstmt = connection.prepareStatement(query);
    fillStatement(pstmt, parameters);
    resultSet = executeStatement(pstmt);
    return resultSet;
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
