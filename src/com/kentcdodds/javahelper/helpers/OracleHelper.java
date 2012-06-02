package com.kentcdodds.javahelper.helpers;

import au.com.bytecode.opencsv.CSVWriter;
import com.kentcdodds.javahelper.model.HelperConnection;
import com.kentcdodds.javahelper.model.HelperQuery;
import com.kentcdodds.javahelper.model.QueryParameter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.CLOB;

/**
 *
 * @author kentcdodds
 */
public class OracleHelper {

  private static HelperConnection defaultConnection = null;
  private static java.util.Map<String, HelperConnection> allConnectionsMap;

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
   * Note, the given default connection is not put to the allConnectionsMap.
   *
   * @param aDefaultConnection the defaultConnection to set
   */
  public static void setDefaultConnection(HelperConnection aDefaultConnection) {
    defaultConnection = aDefaultConnection;
  }

  /**
   * Sets the default connection to a new HelperConnection with the connectionURL and the properties, then creates a
   * HelperQuery out of the default connection, query, and params. Then calls execute on the helperQuery
   *
   * @param connectionUrl the url to use to create the connection
   * @param properties the properties to apply to the connection
   * @param query the query to execute
   * @param params the parameters to add to the prepared statement created by connection and query
   * @return the results of helperQuery.execute();
   * @throws SQLException
   */
  public static ResultSet executeQuery(String connectionUrl, Map<String, String> properties, String query, QueryParameter... params) throws SQLException {
    setDefaultConnection(new HelperConnection(connectionUrl, properties));
    HelperQuery helperQuery = null;
    try {
      helperQuery = new HelperQuery(getDefaultConnection(), query, params);
    } catch (Exception ex) { //This is caught here instead of thrown because this should never be a problem.
      //The only reason an exception is thrown here is because getDefaultConnection() throws an error if the default connection is not set.
      //However, it is set in this method, so it is caught so it doesn't have to be hadled later.
      Logger.getLogger(OracleHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (helperQuery == null) {
      return null;
    }
    return helperQuery.execute();
  }

  /**
   * Creates a new HelperQuery out of the default connection, query, and params and calls helperQuery.execute();
   *
   * @param query the query to be run
   * @param params parameters to apply to the prepared statement created by the query and default connection
   * @return the results of helperQuery.execute();
   * @throws SQLException when executing the query
   * @throws Exception when getting the default connection if it is not set.
   */
  public static ResultSet executeQuery(String query, QueryParameter... params) throws SQLException, Exception {
    HelperQuery helperQuery = new HelperQuery(getDefaultConnection(), query, params);
    return helperQuery.execute();
  }

  /**
   * Creates a HelperQuery out of the given HelperConnection, query, and params and calls helperQuery.execute();
   *
   * @param helperConnection the HelperConnection to run the query with
   * @param query the query to be run
   * @param params the parameters to apply to the statement created by the query and the connection
   * @return the results of helperQuery.execute();
   * @throws DataException
   * @throws SQLException
   */
  public static ResultSet executeQuery(HelperConnection helperConnection, String query, QueryParameter... params) throws SQLException {
    HelperQuery helperQuery = new HelperQuery(helperConnection, query, params);
    return helperQuery.execute();
  }

  /**
   * Gets the helperConnection out of the allConnectionsMap based on the given helperConnectionName. Then creates a
   * HelperQuery out of the HelperConnection and the given query and params and calls helperQuery.execute(); Returns
   * null if the allConnectionsMap does not return a connection by the given name.
   *
   * @param helperConnectionName the HelperConnection to run the query with
   * @param query the query to be run
   * @param params the parameters to apply to the statement created by the query and the connection
   * @return the results of helperQuery.execute();
   * @throws DataException
   * @throws SQLException
   */
  public static ResultSet executeQuery(String helperConnectionName, String query, QueryParameter... params) throws SQLException {
    HelperConnection helperConnection = getAllConnectionsMap().get(helperConnectionName);
    if (helperConnection == null) {
      return null;
    }
    HelperQuery helperQuery = new HelperQuery(helperConnection, query, params);
    return helperQuery.execute();
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

  /**
   * Gets the helper connection out of the map by the given name.
   *
   * @param name
   * @return
   */
  public static HelperConnection getHelperConnection(String name) {
    return getAllConnectionsMap().get(name);
  }

  /**
   * Puts the given helperConnection into the allConnectionsMap mapped by the given name.
   *
   * @param name
   * @param helperConnection
   * @return
   */
  public static HelperConnection putHelperConnection(String name, HelperConnection helperConnection) {
    return getAllConnectionsMap().put(name, helperConnection);
  }

  /**
   * Adds all the given helperConnections to the allConnectionsMap
   *
   * @param helperConnectionMap
   */
  public static void putAllHelperConnections(java.util.Map<String, HelperConnection> helperConnectionMap) {
    getAllConnectionsMap().putAll(helperConnectionMap);
  }

  /**
   * @return the allConnectionsMap
   */
  public static java.util.Map<String, HelperConnection> getAllConnectionsMap() {
    if (allConnectionsMap == null) {
      allConnectionsMap = new java.util.TreeMap<>();
    }
    return allConnectionsMap;
  }

  /**
   * @param aAllConnectionsMap the allConnectionsMap to set
   */
  public static void setAllConnectionsMap(java.util.Map<String, HelperConnection> aAllConnectionsMap) {
    allConnectionsMap = aAllConnectionsMap;
  }

}
