package com.kentcdodds.javahelper.model;

/**
 * Javabean for the OracleHelper. Used in HelperQuery.
 *
 * @author kentcdodds
 */
public class QueryParameter {

  public static final int ARRAY = 100, ASCIISTREAM = 101, BIGDECIMAL = 102, BINARYSTREAM = 103,
          BLOB = 104, BOOLEAN = 105, BYTE = 106, BYTES = 107, CHARACTERSTREAM = 108, CLOB = 109,
          DATE = 110, DOUBLE = 111, FLOAT = 112, INT = 113, LONG = 114, NCHARACTERSTREAM = 115,
          NCLOB = 116, NSTRING = 117, OBJECT = 118, REF = 119, ROWID = 120, SHORT = 121,
          SQLXML = 122, STRING = 123, TIME = 124, TIMESTAMP = 125, URL = 126, UNSET = -1;
  private int type = UNSET; //not initialized
  private Object value = null;

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
  public Object getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * Returns a string version of the type of this query parameter
   *
   * @return
   */
  public String getTypeString() {
    switch (type) {
      case ARRAY:
        return "Array";
      case ASCIISTREAM:
        return "Ascii Stream";
      case BIGDECIMAL:
        return "Big Decimal";
      case BINARYSTREAM:
        return "Binary Stream";
      case BLOB:
        return "Blob";
      case BOOLEAN:
        return "Boolean";
      case BYTE:
        return "Byte";
      case BYTES:
        return "Bytes";
      case CHARACTERSTREAM:
        return "Character Stream";
      case CLOB:
        return "Clob";
      case DATE:
        return "Date";
      case DOUBLE:
        return "Double";
      case FLOAT:
        return "Float";
      case INT:
        return "Int";
      case LONG:
        return "Long";
      case NCHARACTERSTREAM:
        return "N Character Stream";
      case NCLOB:
        return "N Clob";
      case NSTRING:
        return "N String";
      case OBJECT:
        return "Object";
      case REF:
        return "Ref";
      case ROWID:
        return "RowId";
      case SHORT:
        return "Short";
      case SQLXML:
        return "SQL XML";
      case STRING:
        return "String";
      case TIME:
        return "Time";
      case TIMESTAMP:
        return "Timestamp";
      case URL:
        return "URL";
      case UNSET:
        return "Not defined (-1)";
    }
    return "Unknown (" + type + ")";
  }

  @Override
  public String toString() {
    return getTypeString() + " ==> " + value;
  }
}