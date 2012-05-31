package com.kentcdodds.javahelper.helpers;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import oracle.sql.CLOB;

/**
 *
 * @author kentcdodds
 */
public class OracleHelper {

  private static HelperConnection defaultConnection = null;

  /**
   * @return the defaultConnection
   */
  public static HelperConnection getDefaultConnection() throws Exception {
    if (defaultConnection == null) {
      throw new Exception("The default connection hasn't been set yet.");
    }
    return defaultConnection;
  }

  /**
   * @param aDefaultConnection the defaultConnection to set
   */
  public static void setDefaultConnection(HelperConnection aDefaultConnection) {
    defaultConnection = aDefaultConnection;
  }

  /**
   * Executes the given query. Very simple, doesn't check anything, so be careful using this!
   *
   * @param query
   * @return
   * @throws DataException
   * @throws SQLException
   */
  public ResultSet executeQuery(String connectionUrl, Map<String, String> properties, String query, QueryParameter... params) throws SQLException, Exception {
    setDefaultConnection(new HelperConnection(connectionUrl, properties));
    return executeQuery(getDefaultConnection(), query, params);
  }

  /**
   * Executes the given query. Very simple, doesn't check anything, so be careful using this!
   *
   * @param query
   * @return
   * @throws DataException
   * @throws SQLException
   */
  public ResultSet executeQuery(String query, QueryParameter... params) throws SQLException, Exception {
    return executeQuery(getDefaultConnection(), query, params);
  }

  /**
   * Executes the given query. Very simple, doesn't check anything, so be careful using this!
   *
   * @param query
   * @return
   * @throws DataException
   * @throws SQLException
   */
  public ResultSet executeQuery(HelperConnection helperConnection, String query, QueryParameter... params) throws SQLException {
    PrinterHelper.print("The connection is: " + helperConnection.getJdbcURL());
    PrinterHelper.print("The query is: " + query);
    if (params.length > 0) {
      PrinterHelper.print("The QueryParameters are:");
      PrinterHelper.print("\t" + StringHelper.splitBy(StringHelper.newline + "\t", params));
    }
    Connection conn = helperConnection.createOracleConnection();
    PreparedStatement pstmt = conn.prepareStatement(query);
    for (int i = 1; i <= params.length; i++) {
      QueryParameter param = params[i - 1];
      switch (param.getType()) {
        case QueryParameter.CLOB:
          Clob clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);
          clob.setString(i, param.getValue());
          pstmt.setClob(i, clob);
          break;
        case QueryParameter.STRING:
          pstmt.setString(i, param.getValue());
          break;
      }
    }

    ResultSet rs = pstmt.executeQuery();
    conn.commit();
    return rs;
  }

  //<editor-fold defaultstate="collapsed" desc="HelperConnection">
  public class HelperConnection {

    private String jdbcURL = null;
    private Map<String, String> properties = null;

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
    public Connection createOracleConnection() throws SQLException {
      Properties connectionProps = new Properties();
      for (Map.Entry<String, String> entry : properties.entrySet()) {
        connectionProps.put(entry.getKey(), entry.getValue());
      }
      Connection conn = DriverManager.getConnection(jdbcURL, connectionProps);
      conn.setAutoCommit(false);
      return conn;
    }//createConnection

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
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Query Parameter">
  public class QueryParameter {

    public static final int STRING = 100;
    public static final int CLOB = 101;
    private int type = -1;
    private String value = null;

    /**
     * Constructor for QueryParameter
     */
    public QueryParameter(int type, String value) {
      this.type = type;
      this.value = value;
    }

    /**
     * @return the type
     */
    public int getType() {
      return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
      this.type = type;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
      this.value = value;
    }

    public String getTypeString() {
      switch (type) {
        case STRING:
          return "String";
        case CLOB:
          return "Clob";
      }
      return "Unknown (" + type + ")";
    }

    @Override
    public String toString() {
      return getTypeString() + " - " + value;
    }
  }
  //</editor-fold>
}
