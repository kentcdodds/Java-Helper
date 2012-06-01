package com.kentcdodds.javahelper.helpers;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
  public static ResultSet executeQuery(String connectionUrl, Map<String, String> properties, String query, QueryParameter... params) throws SQLException, Exception {
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
  public static ResultSet executeQuery(String query, QueryParameter... params) throws SQLException, Exception {
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
  public static ResultSet executeQuery(HelperConnection helperConnection, String query, QueryParameter... params) throws SQLException {
    PrinterHelper.println("The connection is: " + helperConnection.getJdbcURL());
    PrinterHelper.println("The query is: " + query);
    if (params.length > 0) {
      PrinterHelper.println("The QueryParameters are:");
      PrinterHelper.println("\t" + StringHelper.splitBy(StringHelper.newline + "\t", params));
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

  /**
   * Prints the given ResultSet to a comma separated file (the destination)
   *
   * @param rs
   * @param destination
   * @throws SQLException
   * @throws FileNotFoundException
   */
  public static void resultSetToCSVFile(ResultSet rs, String destination) throws SQLException, FileNotFoundException, IOException {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    String[] header = new String[columnCount];
    for (int i = 0; i < columnCount; i++) {
      header[i] = metaData.getColumnName(i + 1);
    }
    File file = new File(destination);
    IOHelper.checkDirectory(file);
    try (PrintWriter pw = new PrintWriter(file); CSVWriter writer = new CSVWriter(pw)) {
      writer.writeNext(header);
      while (rs.next()) {
        String[] row = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
          String string = rs.getString(i + 1);
          if (string == null) {
            string = "";
          }
          row[i] = string;
        }
        writer.writeNext(row);
      }
    }
  }

  //<editor-fold defaultstate="collapsed" desc="HelperConnection">
  public static class HelperConnection {

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
    public Connection createOracleConnection() throws SQLException {
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
  public static class QueryParameter {

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
