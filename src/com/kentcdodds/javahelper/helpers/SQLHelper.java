package com.kentcdodds.javahelper.helpers;

import au.com.bytecode.opencsv.CSVWriter;
import com.kentcdodds.javahelper.model.HelperConnection;
import com.kentcdodds.javahelper.model.HelperQuery;
import com.kentcdodds.javahelper.model.QueryParameter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author kentcdodds
 */
public class SQLHelper {

  /**
   * Creates a new HelperConnection and executes a new HelperQuery. Useful for when you don't care to keep the
   * HelperConnection or HelperQuery for processing later.
   *
   * @param connectionUrl the url to use to create the connection
   * @param properties the properties to apply to the connection
   * @param query the query to execute
   * @param params the parameters to add to the prepared statement created by connection and query
   * @return the results of helperQuery.execute();
   * @throws SQLException
   */
  public static ResultSet executeQuery(String connectionUrl, Map<String, String> properties, String query, QueryParameter... params) throws SQLException {
    return new HelperConnection(connectionUrl, properties).executeQuery(new HelperQuery(query, params));
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
    HelperQuery helperQuery = new HelperQuery(query, params);
    return helperConnection.executeQuery(helperQuery);
  }

  /**
   * Prints the result set to the consol.
   * @param rs
   * @throws SQLException 
   */
  public static void printResultSet(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    String[] header = new String[columnCount];
    for (int i = 0; i < columnCount; i++) {
      header[i] = metaData.getColumnName(i + 1);
    }
    PrinterHelper.println(StringHelper.splitBy("\t", header));
    while (rs.next()) {
      String[] row = new String[columnCount];
      for (int i = 0; i < columnCount; i++) {
        String string = rs.getString(i + 1);
        if (string == null) {
          string = "";
        }
        row[i] = string;
      }
      PrinterHelper.println(StringHelper.splitBy("\t", row));
    }
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
}
