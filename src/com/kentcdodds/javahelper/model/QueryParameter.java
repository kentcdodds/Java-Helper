package com.kentcdodds.javahelper.model;

/**
 * Javabean for the OracleHelper. Used in HelperQuery.
 *
 * @author kentcdodds
 */
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