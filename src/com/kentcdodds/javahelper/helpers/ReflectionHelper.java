package com.kentcdodds.javahelper.helpers;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Kent
 */
public class ReflectionHelper {

  /**
   * Returns the given object and its attributes as a string.
   *
   * @param o
   * @param scanSupers
   * @param limitSupers
   * @param scanInnerObjects
   * @param limitInners
   * @return
   */
  public static String getObjectInString(
          Object o,
          boolean scanSupers,
          int limitSupers,
          boolean scanInnerObjects,
          int limitInners) {
    StringBuilder sb = new StringBuilder();
    appendObjectToStringBuilder(sb, o, scanSupers, limitSupers, scanInnerObjects, limitInners, "");
    return sb.toString();
  }

  /**
   * allows recursive calls for getObjectInString
   *
   * @param sb
   * @param o
   * @param scanSupers
   * @param limitSupers
   * @param scanInnerObjects
   * @param limitInners
   */
  private static void appendObjectToStringBuilder(
          StringBuilder sb,
          Object o,
          boolean scanSupers,
          int limitSupers,
          boolean scanInnerObjects,
          int limitInners,
          String prefix) {
    Class<? extends Object> aClass = o.getClass();
    if (aClass.getSuperclass() != Object.class && scanSupers && limitSupers > 0) {
      appendObjectToStringBuilder(sb, o, scanSupers, (limitSupers - 1), scanInnerObjects, limitInners, prefix);
    }
    sb.append(prefix).append(aClass.getName()).append(":").append(StringHelper.newline);
    for (Field field : aClass.getDeclaredFields()) {
      try {
        field.setAccessible(true);
        Class<? extends Object> fieldClass = field.get(o).getClass();
        if (!isPrimitive(fieldClass) && fieldClass != String.class && scanInnerObjects) {
          appendObjectToStringBuilder(sb, field.get(o), scanSupers, limitSupers, scanInnerObjects, (limitInners - 1), prefix + "\t");
        }
        String name = field.getName();
        Object value = field.get(o);
        sb.append(prefix).append("\t").append(name).append(": ").append(value).append(StringHelper.newline);
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        Logger.getLogger(ReflectionHelper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /**
   * Returns true if any attribute in the item matches the given constraints
   *
   * @param object the object you want to match
   * @param klass the class to get the fields from (in most cases you'll just call object.getClass())
   * @param iterations how many inherited levels you want to check fields for
   * @param match the String to match fields against
   * @param ignoreField fieldNames you wish to ignore, you can give as many as you like, you can also give an array of
   * strings
   * @return whether the given object contained fields which matched the given string
   */
  public static boolean matches(Object object, Class klass, int iterations, String match, String... ignoreField) {
    if (iterations < 0) {
      return false;
    }
    boolean checkMatch = false;
    try {
      checkMatch = matchFields(klass.getDeclaredFields(), object, match, ignoreField);
    } catch (IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(com.kentcdodds.javahelper.helpers.OtherHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (checkMatch) {
      return true;
    } else {
      Class<? extends Object> supersSuperclass = (Class<? extends Object>) klass.getSuperclass();
      if (supersSuperclass != Object.class) {
        boolean matches = matches(object, supersSuperclass, (iterations - 1), match, ignoreField);
        if (matches) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }

  /**
   * Calls matchField(field, bo, match) on each field in the given field array.
   *
   * @param fields the fields array to get the fields from
   * @param object the object to get the field values from
   * @param match the text to match fields to
   * @param ignoreField any number of fieldNames which are to be ignored.
   * @return true on first true field match
   * @throws IllegalArgumentException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  private static boolean matchFields(Field[] fields, Object object, String match, String... ignoreField) throws IllegalArgumentException, IllegalArgumentException, IllegalAccessException {
    List<String> ignoreFieldsList = Arrays.asList(ignoreField);
    for (Field field : fields) {
      if (!ignoreFieldsList.contains(field.getName())) {
        if (matchField(field, object, match)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Gets the value of the field and matches the string version of it with the given match
   *
   * @param field the field to match
   * @param object the object to get the field value from
   * @param match the match to match the field value to.
   * @return
   * @throws IllegalArgumentException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  private static boolean matchField(Field field, Object object, String match) throws IllegalArgumentException, IllegalArgumentException, IllegalAccessException {
    field.setAccessible(true);
    if (field.get(object) == null) {
      return false;
    }
    Class<?> type = field.getType();
    String value = null;
    if (type == Date.class) {
      SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
      Date date = (Date) field.get(object);
      value = sdf.format(date);
    } else if (type == String.class || isPrimitive(type)) {
      value = field.get(object).toString();
    }
    if (value != null
            && Pattern.compile(Pattern.quote(match), Pattern.CASE_INSENSITIVE).matcher(value).find()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks first whether it is primitive and then whether it's wrapper is a primitive wrapper. Returns true if either
   * is true
   *
   * @param c the class to check whether it's primitive
   * @return whether it's a primitive type itself or it's a wrapper for a primitive type
   */
  public static boolean isPrimitive(Class c) {
    if (c.isPrimitive()) {
      return true;
    } else if (c == Byte.class
            || c == Short.class
            || c == Integer.class
            || c == Long.class
            || c == Float.class
            || c == Double.class
            || c == Boolean.class
            || c == Character.class) {
      return true;
    } else {
      return false;
    }
  }
}
