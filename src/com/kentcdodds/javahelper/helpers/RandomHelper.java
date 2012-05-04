package com.kentcdodds.javahelper.helpers;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Kent
 */
public class RandomHelper {

  private static String[] firstNames = new String[]{
    //<editor-fold defaultstate="collapsed" desc="Random First Names">
    "Kristina", "Paige", "Sherri", "Gretchen", "Pat", "Michelle",
    "Karen", "Patrick", "Elsie", "Hazel", "Malcolm", "Dolores", "Francis",
    "Sandy", "Marion", "Beth", "Julia", "Jerome", "Neal", "Jean", "Kristine",
    "Crystal", "Alex", "Eric", "Wesley", "Franklin", "Claire", "Marian",
    "Marcia", "Dwight", "Wayne", "Stephanie", "Neal", "Gretchen", "Tim",
    "Jerome", "Shelley", "Priscilla", "Elsie", "Beth", "Erica", "Douglas",
    "Donald", "Katherine", "Paul", "Patricia", "Lois", "Louis", "Christina",
    "Darlene", "Harvey", "William", "Frederick", "Shirley", "Jason", "Judith",
    "Gretchen", "Don", "Glenda", "Scott", "Pat", "Michelle", "Jessica",
    "Evan", "Melinda", "Calvin", "Eugene", "Vickie", "Luis", "Allan",
    "Melanie", "Marianne", "Natalie", "Caroline", "Arlene", "Kyle", "Calvin",
    "Gary", "Samantha", "Sara", "Stacy", "Gladys", "Mike", "Lynne", "Faye",
    "Diana", "Leon", "Ethel", "Steve", "Alison", "Sherri", "Patsy", "Kelly",
    "Stacy", "Curtis", "Dana", "Jennifer", "Brett", "Brandon", "Keith",
    "Joann", "Ronnie", "Scott", "Gene", "Louise", "Geoffrey", "Patricia",
    "Jennifer", "Mary", "Shawn", "Vincent", "Kurt", "Danny", "Charlene",
    "Alice", "Joan", "Betty", "Danny", "Peggy", "Leslie", "Marshall", "Sara",
    "Martha", "Jack", "Gayle", "Benjamin", "Roberta", "Patricia", "Clifford",
    "Joanne", "Martin", "Toni", "Beth", "Jessica", "Samantha", "Jimmy",
    "Vincent", "Dianne", "Rhonda", "Tamara", "Mary", "Kristina", "Paige",
    "Sherri", "Gretchen", "Karen", "Patrick", "Elsie", "Hazel", "Malcolm",
    "Dolores", "Francis", "Sandy", "Marion", "Beth", "Julia", "Jerome",
    "Neal", "Jean", "Kristine", "Crystal", "Alex", "Eric", "Wesley",
    "Franklin", "Claire", "Marian", "Marcia", "Dwight", "Wayne", "Stephanie",
    "Neal", "Gretchen", "Tim", "Jerome", "Shelley", "Priscilla", "Elsie",
    "Beth", "Erica", "Douglas", "Donald", "Katherine", "Paul", "Patricia",
    "Lois", "Louis", "Christina", "Darlene", "Harvey", "William", "Frederick",
    "Shirley", "Jason", "Judith", "Gretchen", "Don", "Glenda", "Scott"};
  //</editor-fold>
  private static String[] lastNames = new String[]{
    //<editor-fold defaultstate="collapsed" desc="Random Last Names">
    "Chung", "Chen", "Melton", "Hill", "Puckett", "Allen", "Rich",
    "Song", "Hamilton", "Bender", "Wagner", "McLaughlin", "McNamara",
    "Raynor", "Moon", "Woodard", "Desai", "Wallace", "Lawrence", "Griffin",
    "Dougherty", "Powers", "May", "Steele", "Teague", "Vick", "Gallagher",
    "Solomon", "Walsh", "Monroe", "Connolly", "Hawkins", "Middleton",
    "Goldstein", "Watts", "Johnston", "Weeks", "Wilkerson", "Barton",
    "Walton", "Hall", "Ross", "Chung", "Bender", "Woods", "Mangum",
    "Joseph", "Rosenthal", "Bowden", "Barton", "Underwood", "Jones", "Baker",
    "Merritt", "Cross", "Cooper", "Holmes", "Sharpe", "Morgan", "Hoyle",
    "Allen", "Rich", "Rich", "Grant", "Proctor", "Diaz", "Graham", "Watkins",
    "Hinton", "Marsh", "Hewitt", "Branch", "Walton", "O'Brien", "Case",
    "Watts", "Christensen", "Parks", "Hardin", "Lucas", "Eason", "Davidson",
    "Whitehead", "Rose", "Sparks", "Moore", "Pearson", "Rodgers", "Graves",
    "Scarborough", "Sutton", "Sinclair", "Bowman", "Olsen", "Love", "McLean",
    "Christian", "Lamb", "James", "Chandler", "Stout", "Cowan", "Golden",
    "Bowling", "Beasley", "Clapp", "Abrams", "Tilley", "Morse", "Boykin",
    "Sumner", "Cassidy", "Davidson", "Heath", "Blanchard", "McAllister",
    "McKenzie", "Byrne", "Schroeder", "Griffin", "Gross", "Perkins",
    "Robertson", "Palmer", "Brady", "Rowe", "Zhang", "Hodge", "Li", "Bowling",
    "Justice", "Glass", "Willis", "Hester", "Floyd", "Graves", "Fischer",
    "Norman", "Chan", "Hunt", "Byrd", "Chung", "Chen", "Melton", "Hill",
    "Puckett", "Song", "Hamilton", "Bender", "Wagner", "McLaughlin",
    "McNamara", "Raynor", "Moon", "Woodard", "Desai", "Wallace", "Lawrence",
    "Griffin", "Dougherty", "Powers", "May", "Steele", "Teague", "Vick",
    "Gallagher", "Solomon", "Walsh", "Monroe", "Connolly", "Hawkins",
    "Middleton", "Goldstein", "Watts", "Johnston", "Weeks", "Wilkerson",
    "Barton", "Walton", "Hall", "Ross", "Chung", "Bender", "Woods", "Mangum",
    "Joseph", "Rosenthal", "Bowden", "Barton", "Underwood", "Jones", "Baker",
    "Merritt", "Cross", "Cooper", "Holmes", "Sharpe", "Morgan", "Hoyle"};
  //</editor-fold>
  private static String[] longStates = new String[]{
    //<editor-fold defaultstate="collapsed" desc="States">
    "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
    "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
    "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
    "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
    "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
    "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
    "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
    "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
    "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming",
    "District of Columbia"};
  //</editor-fold>
  private static String[] shortStates = new String[]{
    //<editor-fold defaultstate="collapsed" desc="State Abbreviations">
    "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "VA", "WA", "WV",
    "WI", "WY", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
    "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
    "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
    "UT", "VT"};
  //</editor-fold>
  private static String[] streetAddresses = new String[]{
    //<editor-fold defaultstate="collapsed" desc="Random Street Addresses">
    "27 N Abbey Court", "576 S Aberdeen Street", "2149 W Adams Street",
    "980 E Adelphi Street", "2306 N Adler Place", "2602 S Agate Court",
    "2124 W Ainslie Street", "2112 E Aitken Place", "1038 N Alabama Avenue",
    "819 S Albany Avenue", "1845 W Albee Square", "2090 E Albemarle Road",
    "193 N Albemarle Terrace", "2422 S Alice Court", "196 W Allen Avenue",
    "199 E Alton Place", "1737 N Amber Street", "963 S Amboy Street",
    "265 W Amersfort Place", "269 E Amherst Street", "2647 N Amity Street",
    "2760 S Anchorage Place", "519 W Anna Court", "1963 E Anthony Street",
    "2418 N Apollo Street", "1687 S Applegate Court", "282 W Argyle Road",
    "2100 E Arion Place", "1461 N Arkansas Drive", "1490 S Arlington Avenue",
    "877 W Arlington Place", "200 E Ash Street", "2465 N Ashford Street",
    "321 S Ashland Place", "1199 W Aster Court", "1675 E Atkins Avenue",
    "2449 N Atlantic Avenue", "2644 S Auburn Place", "2473 W Aurelia Court",
    "2888 E Autumn Avenue", "2003 N Bainbridge Street", "1179 S Balfour Place",
    "2819 W Baltic Street", "2219 E Bancroft Place", "654 N Bank Street",
    "1378 S Banker Street", "2323 W Banner Avenue", "1478 E Banner 3rd Road",
    "2568 N Banner 3rd Terrace", "557 S Barbey Street", "563 W Barlow Drive",
    "1856 E Bartlett Place", "2192 N Bartlett Street", "2742 S Barwell Terrace",
    "2582 W Bassett Avenue", "1482 E Batchelder Street", "846 N Bath Avenue",
    "1360 S Battery Avenue", "1393 W Baughman Place", "430 E Bay Parkway",
    "1656 N Bay Street", "389 S Bay Avenue", "1396 W Bay 10th Street",
    "868 E Bay 11th Street", "2400 N Bay 13th Street", "2110 S Bay 14th Street",
    "2046 W Bay 16th Street", "292 E Bay 17th Street", "843 N Bay 19th Street",
    "1577 S Bay 20th Street", "443 W Bay 22nd Street", "2447 E Bay 23rd Street",
    "935 N Bay 25th Street", "2230 S Bay 26th Street", "1340 W Bay 28th Street",
    "2169 E Bay 29th Street", "1957 N Bay 31st Street",
    "2794 S Bay 32nd Street", "948 W Bay 34th Street", "1275 E Bay 35th Street",
    "940 N Bay 37th Street", "1299 S Bay 38th Street", "1738 W Bay 40th Street",
    "2413 E Bay 41st Street", "1557 N Bay 43rd Street", "560 S Bay 44th Street",
    "678 W Bay 46th Street", "744 E Bay 47th Street", "1190 N Bay 48th Street",
    "247 S Bay 49th Street", "2655 W Bay 50th Street", "777 E Bay 52nd Street",
    "1757 N Bay 53rd Street", "2635 S Bay 54th Street", "1113 W Bay 7th Street",
    "2572 E Bay 8th Street", "716 N Bay Ridge Place",
    "1064 S Bay Ridge Parkway", "2096 W Bay Ridge Avenue",
    "223 E Bay View Court", "1155 N Bayard Street", "2399 S Baycliff Terrace",
    "195 W Bayview Place", "373 E Bayview Avenue", "2641 N Beach Place",
    "1956 S Beach 37th Street", "2877 W Beach 38th Street",
    "295 E Beach 40th Street", "310 N Beach 42nd Street",
    "2768 S Beach 43rd Street", "1964 W Beach 44th Street",
    "2039 E Beach 45th Street", "2469 N Beach 46th Street",
    "825 S Beach 47th Street", "249 W Beach 48th Street",
    "1704 E Beach 49th Street", "1250 N Beach 50th Street",
    "1173 S Beach 51st Street", "994 W Beacon Court", "704 E Beadel Street",
    "304 N Beard Street", "2567 S Beaumont Street", "2166 W Beaver Street",
    "1138 E Beayer Place", "1124 N Bedell Lane", "2087 S Bedford Place",
    "2693 W Bedford Avenue", "1989 E Beekman Place", "2908 N Belmont Avenue",
    "759 S Belvidere Street", "2057 W Bennet Court", "587 E Benson Avenue",
    "2293 N Bergen Street", "852 S Bergen Court", "220 W Bergen Place",
    "106 E Bergen Avenue", "1772 N Bergen Beach Place", "571 S Berkeley Place",
    "280 W Berriman Street", "27 N Abbey Court", "576 S Aberdeen Street",
    "2149 W Adams Street", "980 E Adelphi Street", "2306 N Adler Place",
    "2602 S Agate Court", "2124 W Ainslie Street", "2112 E Aitken Place",
    "1038 N Alabama Avenue", "819 S Albany Avenue", "1845 W Albee Square",
    "2090 E Albemarle Road", "193 N Albemarle Terrace", "2422 S Alice Court",
    "196 W Allen Avenue", "199 E Alton Place", "1737 N Amber Street",
    "963 S Amboy Street", "265 W Amersfort Place", "269 E Amherst Street",
    "2647 N Amity Street", "2760 S Anchorage Place", "519 W Anna Court",
    "1963 E Anthony Street", "2418 N Apollo Street", "1687 S Applegate Court",
    "282 W Argyle Road", "2100 E Arion Place", "1461 N Arkansas Drive",
    "1490 S Arlington Avenue", "877 W Arlington Place", "200 E Ash Street",
    "2465 N Ashford Street", "321 S Ashland Place", "1199 W Aster Court",
    "1675 E Atkins Avenue", "2449 N Atlantic Avenue", "2644 S Auburn Place",
    "2473 W Aurelia Court", "2888 E Autumn Avenue", "2003 N Bainbridge Street",
    "1179 S Balfour Place", "2819 W Baltic Street", "2219 E Bancroft Place",
    "654 N Bank Street", "1378 S Banker Street", "2323 W Banner Avenue",
    "1478 E Banner 3rd Road", "2568 N Banner 3rd Terrace",
    "557 S Barbey Street", "563 W Barlow Drive", "1856 E Bartlett Place",
    "2192 N Bartlett Street", "2742 S Barwell Terrace", "2582 W Bassett Avenue",
    "1482 E Batchelder Street", "846 N Bath Avenue", "1360 S Battery Avenue",
    "1393 W Baughman Place", "430 E Bay Parkway"};
  //</editor-fold>
  private static Map<String, String> longStatesMap;
  private static Map<String, String> shortStatesMap = getShortStatesMap();
  private static Map<String, String> cityStateMap = getCityStateMap();
  private static Map<String, List<String>> stateCityMap = getStateCityMap();

  //<editor-fold defaultstate="collapsed" desc="Array Setters">
  /**
   * @return An array of first names
   */
  public static String[] getFirstNames() {
    if (firstNames == null) {
      firstNames = new String[]{
        //<editor-fold defaultstate="collapsed" desc="Random First Names">
        "Kristina", "Paige", "Sherri", "Gretchen", "Pat", "Michelle",
        "Karen", "Patrick", "Elsie", "Hazel", "Malcolm", "Dolores", "Francis",
        "Sandy", "Marion", "Beth", "Julia", "Jerome", "Neal", "Jean", "Kristine",
        "Crystal", "Alex", "Eric", "Wesley", "Franklin", "Claire", "Marian",
        "Marcia", "Dwight", "Wayne", "Stephanie", "Neal", "Gretchen", "Tim",
        "Jerome", "Shelley", "Priscilla", "Elsie", "Beth", "Erica", "Douglas",
        "Donald", "Katherine", "Paul", "Patricia", "Lois", "Louis", "Christina",
        "Darlene", "Harvey", "William", "Frederick", "Shirley", "Jason", "Judith",
        "Gretchen", "Don", "Glenda", "Scott", "Pat", "Michelle", "Jessica",
        "Evan", "Melinda", "Calvin", "Eugene", "Vickie", "Luis", "Allan",
        "Melanie", "Marianne", "Natalie", "Caroline", "Arlene", "Kyle", "Calvin",
        "Gary", "Samantha", "Sara", "Stacy", "Gladys", "Mike", "Lynne", "Faye",
        "Diana", "Leon", "Ethel", "Steve", "Alison", "Sherri", "Patsy", "Kelly",
        "Stacy", "Curtis", "Dana", "Jennifer", "Brett", "Brandon", "Keith",
        "Joann", "Ronnie", "Scott", "Gene", "Louise", "Geoffrey", "Patricia",
        "Jennifer", "Mary", "Shawn", "Vincent", "Kurt", "Danny", "Charlene",
        "Alice", "Joan", "Betty", "Danny", "Peggy", "Leslie", "Marshall", "Sara",
        "Martha", "Jack", "Gayle", "Benjamin", "Roberta", "Patricia", "Clifford",
        "Joanne", "Martin", "Toni", "Beth", "Jessica", "Samantha", "Jimmy",
        "Vincent", "Dianne", "Rhonda", "Tamara", "Mary", "Kristina", "Paige",
        "Sherri", "Gretchen", "Karen", "Patrick", "Elsie", "Hazel", "Malcolm",
        "Dolores", "Francis", "Sandy", "Marion", "Beth", "Julia", "Jerome",
        "Neal", "Jean", "Kristine", "Crystal", "Alex", "Eric", "Wesley",
        "Franklin", "Claire", "Marian", "Marcia", "Dwight", "Wayne", "Stephanie",
        "Neal", "Gretchen", "Tim", "Jerome", "Shelley", "Priscilla", "Elsie",
        "Beth", "Erica", "Douglas", "Donald", "Katherine", "Paul", "Patricia",
        "Lois", "Louis", "Christina", "Darlene", "Harvey", "William", "Frederick",
        "Shirley", "Jason", "Judith", "Gretchen", "Don", "Glenda", "Scott"};
      //</editor-fold>
    }
    return firstNames;
  }

  /**
   * @return An array of last names
   */
  public static String[] getLastNames() {
    if (lastNames == null) {
      lastNames = new String[]{
        //<editor-fold defaultstate="collapsed" desc="Random Last Names">
        "Chung", "Chen", "Melton", "Hill", "Puckett", "Allen", "Rich",
        "Song", "Hamilton", "Bender", "Wagner", "McLaughlin", "McNamara",
        "Raynor", "Moon", "Woodard", "Desai", "Wallace", "Lawrence", "Griffin",
        "Dougherty", "Powers", "May", "Steele", "Teague", "Vick", "Gallagher",
        "Solomon", "Walsh", "Monroe", "Connolly", "Hawkins", "Middleton",
        "Goldstein", "Watts", "Johnston", "Weeks", "Wilkerson", "Barton",
        "Walton", "Hall", "Ross", "Chung", "Bender", "Woods", "Mangum",
        "Joseph", "Rosenthal", "Bowden", "Barton", "Underwood", "Jones", "Baker",
        "Merritt", "Cross", "Cooper", "Holmes", "Sharpe", "Morgan", "Hoyle",
        "Allen", "Rich", "Rich", "Grant", "Proctor", "Diaz", "Graham", "Watkins",
        "Hinton", "Marsh", "Hewitt", "Branch", "Walton", "O'Brien", "Case",
        "Watts", "Christensen", "Parks", "Hardin", "Lucas", "Eason", "Davidson",
        "Whitehead", "Rose", "Sparks", "Moore", "Pearson", "Rodgers", "Graves",
        "Scarborough", "Sutton", "Sinclair", "Bowman", "Olsen", "Love", "McLean",
        "Christian", "Lamb", "James", "Chandler", "Stout", "Cowan", "Golden",
        "Bowling", "Beasley", "Clapp", "Abrams", "Tilley", "Morse", "Boykin",
        "Sumner", "Cassidy", "Davidson", "Heath", "Blanchard", "McAllister",
        "McKenzie", "Byrne", "Schroeder", "Griffin", "Gross", "Perkins",
        "Robertson", "Palmer", "Brady", "Rowe", "Zhang", "Hodge", "Li", "Bowling",
        "Justice", "Glass", "Willis", "Hester", "Floyd", "Graves", "Fischer",
        "Norman", "Chan", "Hunt", "Byrd", "Chung", "Chen", "Melton", "Hill",
        "Puckett", "Song", "Hamilton", "Bender", "Wagner", "McLaughlin",
        "McNamara", "Raynor", "Moon", "Woodard", "Desai", "Wallace", "Lawrence",
        "Griffin", "Dougherty", "Powers", "May", "Steele", "Teague", "Vick",
        "Gallagher", "Solomon", "Walsh", "Monroe", "Connolly", "Hawkins",
        "Middleton", "Goldstein", "Watts", "Johnston", "Weeks", "Wilkerson",
        "Barton", "Walton", "Hall", "Ross", "Chung", "Bender", "Woods", "Mangum",
        "Joseph", "Rosenthal", "Bowden", "Barton", "Underwood", "Jones", "Baker",
        "Merritt", "Cross", "Cooper", "Holmes", "Sharpe", "Morgan", "Hoyle"};
      //</editor-fold>
    }
    return lastNames;
  }

  /**
   * @return An array of full names of states
   */
  public static String[] getLongStates() {
    if (longStates == null) {
      longStates = new String[]{
        //<editor-fold defaultstate="collapsed" desc="States">
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
        "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
        "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
        "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
        "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
        "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
        "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming",
        "District of Columbia"};
      //</editor-fold>
    }
    return longStates;
  }

  /**
   * @return An array of abbreviations of states
   */
  public static String[] getShortStates() {
    if (shortStates == null) {
      shortStates = new String[]{
        //<editor-fold defaultstate="collapsed" desc="State Abbreviations">
        "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "VA", "WA", "WV",
        "WI", "WY", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
        "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
        "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
        "UT", "VT"};
      //</editor-fold>
    }
    return shortStates;
  }

  /**
   * @return An array of street addresses
   */
  public static String[] getStreetAddresses() {
    if (streetAddresses == null) {
      streetAddresses = new String[]{
        //<editor-fold defaultstate="collapsed" desc="Random Street Addresses">
        "27 N Abbey Court", "576 S Aberdeen Street", "2149 W Adams Street",
        "980 E Adelphi Street", "2306 N Adler Place", "2602 S Agate Court",
        "2124 W Ainslie Street", "2112 E Aitken Place", "1038 N Alabama Avenue",
        "819 S Albany Avenue", "1845 W Albee Square", "2090 E Albemarle Road",
        "193 N Albemarle Terrace", "2422 S Alice Court", "196 W Allen Avenue",
        "199 E Alton Place", "1737 N Amber Street", "963 S Amboy Street",
        "265 W Amersfort Place", "269 E Amherst Street", "2647 N Amity Street",
        "2760 S Anchorage Place", "519 W Anna Court", "1963 E Anthony Street",
        "2418 N Apollo Street", "1687 S Applegate Court", "282 W Argyle Road",
        "2100 E Arion Place", "1461 N Arkansas Drive", "1490 S Arlington Avenue",
        "877 W Arlington Place", "200 E Ash Street", "2465 N Ashford Street",
        "321 S Ashland Place", "1199 W Aster Court", "1675 E Atkins Avenue",
        "2449 N Atlantic Avenue", "2644 S Auburn Place", "2473 W Aurelia Court",
        "2888 E Autumn Avenue", "2003 N Bainbridge Street", "1179 S Balfour Place",
        "2819 W Baltic Street", "2219 E Bancroft Place", "654 N Bank Street",
        "1378 S Banker Street", "2323 W Banner Avenue", "1478 E Banner 3rd Road",
        "2568 N Banner 3rd Terrace", "557 S Barbey Street", "563 W Barlow Drive",
        "1856 E Bartlett Place", "2192 N Bartlett Street", "2742 S Barwell Terrace",
        "2582 W Bassett Avenue", "1482 E Batchelder Street", "846 N Bath Avenue",
        "1360 S Battery Avenue", "1393 W Baughman Place", "430 E Bay Parkway",
        "1656 N Bay Street", "389 S Bay Avenue", "1396 W Bay 10th Street",
        "868 E Bay 11th Street", "2400 N Bay 13th Street", "2110 S Bay 14th Street",
        "2046 W Bay 16th Street", "292 E Bay 17th Street", "843 N Bay 19th Street",
        "1577 S Bay 20th Street", "443 W Bay 22nd Street", "2447 E Bay 23rd Street",
        "935 N Bay 25th Street", "2230 S Bay 26th Street", "1340 W Bay 28th Street",
        "2169 E Bay 29th Street", "1957 N Bay 31st Street",
        "2794 S Bay 32nd Street", "948 W Bay 34th Street", "1275 E Bay 35th Street",
        "940 N Bay 37th Street", "1299 S Bay 38th Street", "1738 W Bay 40th Street",
        "2413 E Bay 41st Street", "1557 N Bay 43rd Street", "560 S Bay 44th Street",
        "678 W Bay 46th Street", "744 E Bay 47th Street", "1190 N Bay 48th Street",
        "247 S Bay 49th Street", "2655 W Bay 50th Street", "777 E Bay 52nd Street",
        "1757 N Bay 53rd Street", "2635 S Bay 54th Street", "1113 W Bay 7th Street",
        "2572 E Bay 8th Street", "716 N Bay Ridge Place",
        "1064 S Bay Ridge Parkway", "2096 W Bay Ridge Avenue",
        "223 E Bay View Court", "1155 N Bayard Street", "2399 S Baycliff Terrace",
        "195 W Bayview Place", "373 E Bayview Avenue", "2641 N Beach Place",
        "1956 S Beach 37th Street", "2877 W Beach 38th Street",
        "295 E Beach 40th Street", "310 N Beach 42nd Street",
        "2768 S Beach 43rd Street", "1964 W Beach 44th Street",
        "2039 E Beach 45th Street", "2469 N Beach 46th Street",
        "825 S Beach 47th Street", "249 W Beach 48th Street",
        "1704 E Beach 49th Street", "1250 N Beach 50th Street",
        "1173 S Beach 51st Street", "994 W Beacon Court", "704 E Beadel Street",
        "304 N Beard Street", "2567 S Beaumont Street", "2166 W Beaver Street",
        "1138 E Beayer Place", "1124 N Bedell Lane", "2087 S Bedford Place",
        "2693 W Bedford Avenue", "1989 E Beekman Place", "2908 N Belmont Avenue",
        "759 S Belvidere Street", "2057 W Bennet Court", "587 E Benson Avenue",
        "2293 N Bergen Street", "852 S Bergen Court", "220 W Bergen Place",
        "106 E Bergen Avenue", "1772 N Bergen Beach Place", "571 S Berkeley Place",
        "280 W Berriman Street", "27 N Abbey Court", "576 S Aberdeen Street",
        "2149 W Adams Street", "980 E Adelphi Street", "2306 N Adler Place",
        "2602 S Agate Court", "2124 W Ainslie Street", "2112 E Aitken Place",
        "1038 N Alabama Avenue", "819 S Albany Avenue", "1845 W Albee Square",
        "2090 E Albemarle Road", "193 N Albemarle Terrace", "2422 S Alice Court",
        "196 W Allen Avenue", "199 E Alton Place", "1737 N Amber Street",
        "963 S Amboy Street", "265 W Amersfort Place", "269 E Amherst Street",
        "2647 N Amity Street", "2760 S Anchorage Place", "519 W Anna Court",
        "1963 E Anthony Street", "2418 N Apollo Street", "1687 S Applegate Court",
        "282 W Argyle Road", "2100 E Arion Place", "1461 N Arkansas Drive",
        "1490 S Arlington Avenue", "877 W Arlington Place", "200 E Ash Street",
        "2465 N Ashford Street", "321 S Ashland Place", "1199 W Aster Court",
        "1675 E Atkins Avenue", "2449 N Atlantic Avenue", "2644 S Auburn Place",
        "2473 W Aurelia Court", "2888 E Autumn Avenue", "2003 N Bainbridge Street",
        "1179 S Balfour Place", "2819 W Baltic Street", "2219 E Bancroft Place",
        "654 N Bank Street", "1378 S Banker Street", "2323 W Banner Avenue",
        "1478 E Banner 3rd Road", "2568 N Banner 3rd Terrace",
        "557 S Barbey Street", "563 W Barlow Drive", "1856 E Bartlett Place",
        "2192 N Bartlett Street", "2742 S Barwell Terrace", "2582 W Bassett Avenue",
        "1482 E Batchelder Street", "846 N Bath Avenue", "1360 S Battery Avenue",
        "1393 W Baughman Place", "430 E Bay Parkway"};
      //</editor-fold>
    }
    return streetAddresses;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Map setters">
  /**
   * @return A map of the full name (key) to the abbreviation (value) of US States
   */
  public static Map<String, String> getLongStatesMap() {
    if (longStatesMap == null) {
      longStatesMap = new TreeMap<>();
      longStatesMap.put("Alabama", "AL");
      longStatesMap.put("Alaska", "AK");
      longStatesMap.put("Arizona", "AZ");
      longStatesMap.put("Arkansas", "AR");
      longStatesMap.put("California", "CA");
      longStatesMap.put("Colorado", "CO");
      longStatesMap.put("Connecticut", "CT");
      longStatesMap.put("District of Columbia", "DC");
      longStatesMap.put("Delaware", "DE");
      longStatesMap.put("Florida", "FL");
      longStatesMap.put("Georgia", "GA");
      longStatesMap.put("Hawaii", "HI");
      longStatesMap.put("Idaho", "ID");
      longStatesMap.put("Illinois", "IL");
      longStatesMap.put("Indiana", "IN");
      longStatesMap.put("Iowa", "IA");
      longStatesMap.put("Kansas", "KS");
      longStatesMap.put("Kentucky", "KY");
      longStatesMap.put("Louisiana", "LA");
      longStatesMap.put("Maine", "ME");
      longStatesMap.put("Maryland", "MD");
      longStatesMap.put("Massachusetts", "MA");
      longStatesMap.put("Michigan", "MI");
      longStatesMap.put("Minnesota", "MN");
      longStatesMap.put("Mississippi", "MS");
      longStatesMap.put("Missouri", "MO");
      longStatesMap.put("Montana", "MT");
      longStatesMap.put("Nebraska", "NE");
      longStatesMap.put("Nevada", "NV");
      longStatesMap.put("New Hampshire", "NH");
      longStatesMap.put("New Jersey", "NJ");
      longStatesMap.put("New Mexico", "NM");
      longStatesMap.put("New York", "NY");
      longStatesMap.put("North Carolina", "NC");
      longStatesMap.put("North Dakota", "ND");
      longStatesMap.put("Ohio", "OH");
      longStatesMap.put("Oklahoma", "OK");
      longStatesMap.put("Oregon", "OR");
      longStatesMap.put("Pennsylvania", "PA");
      longStatesMap.put("Rhode Island", "RI");
      longStatesMap.put("South Carolina", "SC");
      longStatesMap.put("South Dakota", "SD");
      longStatesMap.put("Tennessee", "TN");
      longStatesMap.put("Texas", "TX");
      longStatesMap.put("Utah", "UT");
      longStatesMap.put("Vermont", "VT");
      longStatesMap.put("Virginia", "VA");
      longStatesMap.put("Washington", "WA");
      longStatesMap.put("West Virginia", "WV");
      longStatesMap.put("Wisconsin", "WI");
      longStatesMap.put("Wyoming", "WY");
    }
    return longStatesMap;
  }

  /**
   * @return A map of the abbreviation (key) to the full name (value) of US States
   */
  public static Map<String, String> getShortStatesMap() {
    if (shortStatesMap == null) {
      shortStatesMap = new TreeMap<>();
      shortStatesMap.put("AL", "Alabama");
      shortStatesMap.put("AK", "Alaska");
      shortStatesMap.put("AZ", "Arizona");
      shortStatesMap.put("AR", "Arkansas");
      shortStatesMap.put("CA", "California");
      shortStatesMap.put("CO", "Colorado");
      shortStatesMap.put("CT", "Connecticut");
      shortStatesMap.put("DC", "District of Columbia");
      shortStatesMap.put("DE", "Delaware");
      shortStatesMap.put("FL", "Florida");
      shortStatesMap.put("GA", "Georgia");
      shortStatesMap.put("HI", "Hawaii");
      shortStatesMap.put("ID", "Idaho");
      shortStatesMap.put("IL", "Illinois");
      shortStatesMap.put("IN", "Indiana");
      shortStatesMap.put("IA", "Iowa");
      shortStatesMap.put("KS", "Kansas");
      shortStatesMap.put("KY", "Kentucky");
      shortStatesMap.put("LA", "Louisiana");
      shortStatesMap.put("ME", "Maine");
      shortStatesMap.put("MD", "Maryland");
      shortStatesMap.put("MA", "Massachusetts");
      shortStatesMap.put("MI", "Michigan");
      shortStatesMap.put("MN", "Minnesota");
      shortStatesMap.put("MS", "Mississippi");
      shortStatesMap.put("MO", "Missouri");
      shortStatesMap.put("MT", "Montana");
      shortStatesMap.put("NE", "Nebraska");
      shortStatesMap.put("NV", "Nevada");
      shortStatesMap.put("NH", "New Hampshire");
      shortStatesMap.put("NJ", "New Jersey");
      shortStatesMap.put("NM", "New Mexico");
      shortStatesMap.put("NY", "New York");
      shortStatesMap.put("NC", "North Carolina");
      shortStatesMap.put("ND", "North Dakota");
      shortStatesMap.put("OH", "Ohio");
      shortStatesMap.put("OK", "Oklahoma");
      shortStatesMap.put("OR", "Oregon");
      shortStatesMap.put("PA", "Pennsylvania");
      shortStatesMap.put("RI", "Rhode Island");
      shortStatesMap.put("SC", "South Carolina");
      shortStatesMap.put("SD", "South Dakota");
      shortStatesMap.put("TN", "Tennessee");
      shortStatesMap.put("TX", "Texas");
      shortStatesMap.put("UT", "Utah");
      shortStatesMap.put("VT", "Vermont");
      shortStatesMap.put("VA", "Virginia");
      shortStatesMap.put("WA", "Washington");
      shortStatesMap.put("WV", "West Virginia");
      shortStatesMap.put("WI", "Wisconsin");
      shortStatesMap.put("WY", "Wyoming");
    }
    return shortStatesMap;
  }

  /**
   * @return A map of a city name (key) with the State it is in (value) in the US
   */
  public static Map getCityStateMap() {
    if (cityStateMap == null) {
      cityStateMap = new TreeMap<>();
      cityStateMap.put("New York", "New York");
      cityStateMap.put("Los Angeles", "California");
      cityStateMap.put("Chicago", "Illinois");
      cityStateMap.put("Houston", "Texas");
      cityStateMap.put("Philadelphia", "Pennsylvania");
      cityStateMap.put("Phoenix", "Arizona");
      cityStateMap.put("San Antonio", "Texas");
      cityStateMap.put("San Diego", "California");
      cityStateMap.put("Dallas", "Texas");
      cityStateMap.put("San Jose", "California");
      cityStateMap.put("Jacksonville", "Florida");
      cityStateMap.put("Indianapolis", "Indiana");
      cityStateMap.put("San Francisco", "California");
      cityStateMap.put("Austin", "Texas");
      cityStateMap.put("Columbus", "Ohio");
      cityStateMap.put("Fort Worth", "Texas");
      cityStateMap.put("Charlotte", "North Carolina");
      cityStateMap.put("Detroit", "Michigan");
      cityStateMap.put("El Paso", "Texas");
      cityStateMap.put("Memphis", "Tennessee");
      cityStateMap.put("Baltimore", "Maryland");
      cityStateMap.put("Boston", "Massachusetts");
      cityStateMap.put("Seattle", "Washington");
      cityStateMap.put("Washington", "District of Columbia");
      cityStateMap.put("Nashville ", "Tennessee");
      cityStateMap.put("Denver ", "Colorado");
      cityStateMap.put("Louisville", "Kentucky");
      cityStateMap.put("Milwaukee", "Wisconsin");
      cityStateMap.put("Portland", "Oregon");
      cityStateMap.put("Las Vegas", "Nevada");
      cityStateMap.put("Oklahoma City", "Oklahoma");
      cityStateMap.put("Albuquerque", "New Mexico");
      cityStateMap.put("Tucson", "Arizona");
      cityStateMap.put("Fresno", "California");
      cityStateMap.put("Sacramento", "California");
      cityStateMap.put("Long Beach", "California");
      cityStateMap.put("Kansas City", "Missouri");
      cityStateMap.put("Mesa", "Arizona");
      cityStateMap.put("Virginia Beach ", "Virginia");
      cityStateMap.put("Atlanta", "Georgia");
      cityStateMap.put("Colorado Springs", "Colorado");
      cityStateMap.put("Omaha", "Nebraska");
      cityStateMap.put("Raleigh", "North Carolina");
      cityStateMap.put("Miami", "Florida");
      cityStateMap.put("Cleveland", "Ohio");
      cityStateMap.put("Tulsa", "Oklahoma");
      cityStateMap.put("Oakland", "California");
      cityStateMap.put("Minneapolis", "Minnesota");
      cityStateMap.put("Wichita", "Kansas");
      cityStateMap.put("Arlington", "Texas");
      cityStateMap.put("Bakersfield", "California");
      cityStateMap.put("New Orleans", "Louisiana");
      cityStateMap.put("Honolulu ", "Hawaii");
      cityStateMap.put("Anaheim", "California");
      cityStateMap.put("Tampa", "Florida");
      cityStateMap.put("Aurora", "Colorado");
      cityStateMap.put("Santa Ana", "California");
      cityStateMap.put("Saint Louis ", "Missouri");
      cityStateMap.put("Pittsburgh", "Pennsylvania");
      cityStateMap.put("Corpus Christi", "Texas");
      cityStateMap.put("Riverside", "California");
      cityStateMap.put("Cincinnati", "Ohio");
      cityStateMap.put("Lexington", "Kentucky");
      cityStateMap.put("Anchorage", "Alaska");
      cityStateMap.put("Stockton", "California");
      cityStateMap.put("Toledo", "Ohio");
      cityStateMap.put("Saint Paul", "Minnesota");
      cityStateMap.put("Newark", "New Jersey");
      cityStateMap.put("Greensboro", "North Carolina");
      cityStateMap.put("Buffalo", "New York");
      cityStateMap.put("Plano", "Texas");
      cityStateMap.put("Lincoln", "Nebraska");
      cityStateMap.put("Henderson", "Nevada");
      cityStateMap.put("Fort Wayne", "Indiana");
      cityStateMap.put("Jersey City", "New Jersey");
      cityStateMap.put("Saint Petersburg", "Florida");
      cityStateMap.put("Chula Vista", "California");
      cityStateMap.put("Norfolk ", "Virginia");
      cityStateMap.put("Orlando", "Florida");
      cityStateMap.put("Chandler", "Arizona");
      cityStateMap.put("Laredo", "Texas");
      cityStateMap.put("Madison", "Wisconsin");
      cityStateMap.put("Winston-Salem", "North Carolina");
      cityStateMap.put("Lubbock", "Texas");
      cityStateMap.put("Baton Rouge", "Louisiana");
      cityStateMap.put("Durham", "North Carolina");
      cityStateMap.put("Garland", "Texas");
      cityStateMap.put("Glendale", "Arizona");
      cityStateMap.put("Reno", "Nevada");
      cityStateMap.put("Hialeah", "Florida");
      cityStateMap.put("Chesapeake ", "Virginia");
      cityStateMap.put("Scottsdale", "Arizona");
      cityStateMap.put("North Las Vegas", "Nevada");
      cityStateMap.put("Irving", "Texas");
      cityStateMap.put("Fremont", "California");
      cityStateMap.put("Irvine", "California");
      cityStateMap.put("Birmingham", "Alabama");
      cityStateMap.put("Rochester", "New York");
      cityStateMap.put("San Bernardino", "California");
      cityStateMap.put("Spokane", "Washington");
      cityStateMap.put("Gilbert", "Arizona");
      cityStateMap.put("Arlington ", "Virginia");
      cityStateMap.put("Montgomery", "Alabama");
      cityStateMap.put("Boise", "Idaho");
      cityStateMap.put("Richmond ", "Virginia");
      cityStateMap.put("Des Moines", "Iowa");
      cityStateMap.put("Modesto", "California");
      cityStateMap.put("Fayetteville", "North Carolina");
      cityStateMap.put("Shreveport", "Louisiana");
      cityStateMap.put("Akron", "Ohio");
      cityStateMap.put("Tacoma", "Washington");
      cityStateMap.put("Aurora", "Illinois");
      cityStateMap.put("Oxnard", "California");
      cityStateMap.put("Fontana", "California");
      cityStateMap.put("Yonkers", "New York");
      cityStateMap.put("Augusta ", "Georgia");
      cityStateMap.put("Mobile", "Alabama");
      cityStateMap.put("Little Rock", "Arkansas");
      cityStateMap.put("Moreno Valley", "California");
      cityStateMap.put("Glendale", "California");
      cityStateMap.put("Amarillo", "Texas");
      cityStateMap.put("Huntington Beach", "California");
      cityStateMap.put("Columbus", "Georgia");
      cityStateMap.put("Grand Rapids", "Michigan");
      cityStateMap.put("Salt Lake City", "Utah");
      cityStateMap.put("Tallahassee", "Florida");
      cityStateMap.put("Worcester", "Massachusetts");
      cityStateMap.put("Newport News ", "Virginia");
      cityStateMap.put("Huntsville", "Alabama");
      cityStateMap.put("Knoxville", "Tennessee");
      cityStateMap.put("Providence", "Rhode Island");
      cityStateMap.put("Santa Clarita", "California");
      cityStateMap.put("Grand Prairie", "Texas");
      cityStateMap.put("Brownsville", "Texas");
      cityStateMap.put("Jackson", "Mississippi");
      cityStateMap.put("Overland Park", "Kansas");
      cityStateMap.put("Garden Grove", "California");
      cityStateMap.put("Santa Rosa", "California");
      cityStateMap.put("Chattanooga", "Tennessee");
      cityStateMap.put("Oceanside", "California");
      cityStateMap.put("Fort Lauderdale", "Florida");
      cityStateMap.put("Rancho Cucamonga", "California");
      cityStateMap.put("Port Saint Lucie", "Florida");
      cityStateMap.put("Ontario", "California");
      cityStateMap.put("Vancouver", "Washington");
      cityStateMap.put("Tempe", "Arizona");
      cityStateMap.put("Springfield", "Missouri");
      cityStateMap.put("Lancaster", "California");
      cityStateMap.put("Eugene", "Oregon");
      cityStateMap.put("Pembroke Pines", "Florida");
      cityStateMap.put("Salem", "Oregon");
      cityStateMap.put("Cape Coral", "Florida");
      cityStateMap.put("Peoria", "Arizona");
      cityStateMap.put("Sioux Falls", "South Dakota");
      cityStateMap.put("Springfield", "Massachusetts");
      cityStateMap.put("Elk Grove", "California");
      cityStateMap.put("Rockford", "Illinois");
      cityStateMap.put("Palmdale", "California");
      cityStateMap.put("Corona", "California");
      cityStateMap.put("Salinas", "California");
      cityStateMap.put("Pomona", "California");
      cityStateMap.put("Pasadena", "Texas");
      cityStateMap.put("Joliet", "Illinois");
      cityStateMap.put("Paterson", "New Jersey");
      cityStateMap.put("Kansas City", "Kansas");
      cityStateMap.put("Torrance", "California");
      cityStateMap.put("Syracuse", "New York");
      cityStateMap.put("Bridgeport", "Connecticut");
      cityStateMap.put("Hayward", "California");
      cityStateMap.put("Fort Collins", "Colorado");
      cityStateMap.put("Escondido", "California");
      cityStateMap.put("Lakewood", "Colorado");
      cityStateMap.put("Naperville", "Illinois");
      cityStateMap.put("Dayton", "Ohio");
      cityStateMap.put("Hollywood", "Florida");
      cityStateMap.put("Sunnyvale", "California");
      cityStateMap.put("Alexandria ", "Virginia");
      cityStateMap.put("Mesquite", "Texas");
      cityStateMap.put("Hampton ", "Virginia");
      cityStateMap.put("Pasadena", "California");
      cityStateMap.put("Orange", "California");
      cityStateMap.put("Savannah", "Georgia");
      cityStateMap.put("Cary", "North Carolina");
      cityStateMap.put("Fullerton", "California");
      cityStateMap.put("Warren", "Michigan");
      cityStateMap.put("Clarksville", "Tennessee");
      cityStateMap.put("McKinney", "Texas");
      cityStateMap.put("McAllen", "Texas");
      cityStateMap.put("New Haven", "Connecticut");
      cityStateMap.put("Sterling Heights", "Michigan");
      cityStateMap.put("West Valley City", "Utah");
      cityStateMap.put("Columbia", "South Carolina");
      cityStateMap.put("Killeen", "Texas");
      cityStateMap.put("Topeka", "Kansas");
      cityStateMap.put("Thousand Oaks", "California");
      cityStateMap.put("Cedar Rapids", "Iowa");
      cityStateMap.put("Olathe", "Kansas");
      cityStateMap.put("Elizabeth", "New Jersey");
      cityStateMap.put("Waco", "Texas");
      cityStateMap.put("Hartford", "Connecticut");
      cityStateMap.put("Visalia", "California");
      cityStateMap.put("Gainesville", "Florida");
      cityStateMap.put("Simi Valley", "California");
      cityStateMap.put("Stamford", "Connecticut");
      cityStateMap.put("Bellevue", "Washington");
      cityStateMap.put("Concord", "California");
      cityStateMap.put("Miramar", "Florida");
      cityStateMap.put("Coral Springs", "Florida");
      cityStateMap.put("Lafayette", "Louisiana");
      cityStateMap.put("Charleston", "South Carolina");
      cityStateMap.put("Carrollton", "Texas");
      cityStateMap.put("Roseville", "California");
      cityStateMap.put("Thornton", "Colorado");
      cityStateMap.put("Beaumont", "Texas");
      cityStateMap.put("Allentown", "Pennsylvania");
      cityStateMap.put("Surprise", "Arizona");
      cityStateMap.put("Evansville", "Indiana");
      cityStateMap.put("Abilene", "Texas");
      cityStateMap.put("Frisco", "Texas");
      cityStateMap.put("Independence", "Missouri");
      cityStateMap.put("Santa Clara", "California");
      cityStateMap.put("Springfield", "Illinois");
      cityStateMap.put("Vallejo", "California");
      cityStateMap.put("Victorville", "California");
      cityStateMap.put("Athens ", "Georgia");
      cityStateMap.put("Peoria", "Illinois");
      cityStateMap.put("Lansing", "Michigan");
      cityStateMap.put("Ann Arbor", "Michigan");
      cityStateMap.put("El Monte", "California");
      cityStateMap.put("Denton", "Texas");
      cityStateMap.put("Berkeley", "California");
      cityStateMap.put("Provo", "Utah");
      cityStateMap.put("Downey", "California");
      cityStateMap.put("Midland", "Texas");
      cityStateMap.put("Norman", "Oklahoma");
      cityStateMap.put("Waterbury", "Connecticut");
      cityStateMap.put("Costa Mesa", "California");
      cityStateMap.put("Inglewood", "California");
      cityStateMap.put("Manchester", "New Hampshire");
      cityStateMap.put("Murfreesboro", "Tennessee");
      cityStateMap.put("Columbia", "Missouri");
      cityStateMap.put("Elgin", "Illinois");
      cityStateMap.put("Clearwater", "Florida");
      cityStateMap.put("Miami Gardens", "Florida");
      cityStateMap.put("Rochester", "Minnesota");
      cityStateMap.put("Pueblo", "Colorado");
      cityStateMap.put("Lowell", "Massachusetts");
      cityStateMap.put("Wilmington", "North Carolina");
      cityStateMap.put("Arvada", "Colorado");
      cityStateMap.put("San Buenaventura (Ventura)", "California");
      cityStateMap.put("Westminster", "Colorado");
      cityStateMap.put("West Covina", "California");
      cityStateMap.put("Gresham", "Oregon");
      cityStateMap.put("Fargo", "North Dakota");
      cityStateMap.put("Norwalk", "California");
      cityStateMap.put("Carlsbad", "California");
      cityStateMap.put("Fairfield", "California");
      cityStateMap.put("Cambridge", "Massachusetts");
      cityStateMap.put("Wichita Falls", "Texas");
      cityStateMap.put("High Point", "North Carolina");
      cityStateMap.put("Billings", "Montana");
      cityStateMap.put("Green Bay", "Wisconsin");
      cityStateMap.put("West Jordan", "Utah");
      cityStateMap.put("Richmond", "California");
      cityStateMap.put("Murrieta", "California");
      cityStateMap.put("Burbank", "California");
      cityStateMap.put("Palm Bay", "Florida");
      cityStateMap.put("Everett", "Washington");
      cityStateMap.put("Flint", "Michigan");
      cityStateMap.put("Antioch", "California");
      cityStateMap.put("Erie", "Pennsylvania");
      cityStateMap.put("South Bend", "Indiana");
      cityStateMap.put("Daly City", "California");
      cityStateMap.put("Centennial", "Colorado");
      cityStateMap.put("Temecula", "California");
    }
    return cityStateMap;
  }

  /**
   * @return A map of state names (key) with a list of some cities in the state (value)
   */
  public static Map getStateCityMap() {
    if (stateCityMap == null) {
      stateCityMap = new TreeMap<>();
      //<editor-fold defaultstate="collapsed" desc="States Lists">
      List<String> alabamaList = new ArrayList<>();
      List<String> alaskaList = new ArrayList<>();
      List<String> arizonaList = new ArrayList<>();
      List<String> arkansasList = new ArrayList<>();
      List<String> californiaList = new ArrayList<>();
      List<String> coloradoList = new ArrayList<>();
      List<String> connecticutList = new ArrayList<>();
      List<String> districtofcolumbiaList = new ArrayList<>();
      List<String> floridaList = new ArrayList<>();
      List<String> georgiaList = new ArrayList<>();
      List<String> hawaiiList = new ArrayList<>();
      List<String> idahoList = new ArrayList<>();
      List<String> illinoisList = new ArrayList<>();
      List<String> indianaList = new ArrayList<>();
      List<String> iowaList = new ArrayList<>();
      List<String> kansasList = new ArrayList<>();
      List<String> kentuckyList = new ArrayList<>();
      List<String> louisianaList = new ArrayList<>();
      List<String> marylandList = new ArrayList<>();
      List<String> massachusettsList = new ArrayList<>();
      List<String> michiganList = new ArrayList<>();
      List<String> minnesotaList = new ArrayList<>();
      List<String> mississippiList = new ArrayList<>();
      List<String> missouriList = new ArrayList<>();
      List<String> montanaList = new ArrayList<>();
      List<String> nebraskaList = new ArrayList<>();
      List<String> nevadaList = new ArrayList<>();
      List<String> newhampshireList = new ArrayList<>();
      List<String> newjerseyList = new ArrayList<>();
      List<String> newmexicoList = new ArrayList<>();
      List<String> newyorkList = new ArrayList<>();
      List<String> northcarolinaList = new ArrayList<>();
      List<String> northdakotaList = new ArrayList<>();
      List<String> ohioList = new ArrayList<>();
      List<String> oklahomaList = new ArrayList<>();
      List<String> oregonList = new ArrayList<>();
      List<String> pennsylvaniaList = new ArrayList<>();
      List<String> rhodeislandList = new ArrayList<>();
      List<String> southcarolinaList = new ArrayList<>();
      List<String> southdakotaList = new ArrayList<>();
      List<String> tennesseeList = new ArrayList<>();
      List<String> texasList = new ArrayList<>();
      List<String> utahList = new ArrayList<>();
      List<String> virginiaList = new ArrayList<>();
      List<String> washingtonList = new ArrayList<>();
      List<String> wisconsinList = new ArrayList<>();
      List<String> delawareList = new ArrayList<>();
      List<String> maineList = new ArrayList<>();
      List<String> vermontList = new ArrayList<>();
      List<String> westvirginiaList = new ArrayList<>();
      List<String> wyomingList = new ArrayList<>();
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Cities List">
      delawareList.add("Dover");
      maineList.add("Augusta");
      vermontList.add("Montpelier");
      westvirginiaList.add("Charleston");
      wyomingList.add("Cheyenne");
      alabamaList.add("Birmingham");
      alabamaList.add("Montgomery");
      alabamaList.add("Mobile");
      alabamaList.add("Huntsville");
      alaskaList.add("Anchorage");
      arizonaList.add("Phoenix");
      arizonaList.add("Tucson");
      arizonaList.add("Mesa");
      arizonaList.add("Chandler");
      arizonaList.add("Glendale");
      arizonaList.add("Scottsdale");
      arizonaList.add("Gilbert");
      arizonaList.add("Tempe");
      arizonaList.add("Peoria");
      arizonaList.add("Surprise");
      arkansasList.add("Little Rock");
      californiaList.add("Los Angeles");
      californiaList.add("San Diego");
      californiaList.add("San Jose");
      californiaList.add("San Francisco");
      californiaList.add("Fresno");
      californiaList.add("Sacramento");
      californiaList.add("Long Beach");
      californiaList.add("Oakland");
      californiaList.add("Bakersfield");
      californiaList.add("Anaheim");
      californiaList.add("Santa Ana");
      californiaList.add("Riverside");
      californiaList.add("Stockton");
      californiaList.add("Chula Vista");
      californiaList.add("Fremont");
      californiaList.add("Irvine");
      californiaList.add("San Bernardino");
      californiaList.add("Modesto");
      californiaList.add("Oxnard");
      californiaList.add("Fontana");
      californiaList.add("Moreno Valley");
      californiaList.add("Glendale");
      californiaList.add("Huntington Beach");
      californiaList.add("Santa Clarita");
      californiaList.add("Garden Grove");
      californiaList.add("Santa Rosa");
      californiaList.add("Oceanside");
      californiaList.add("Rancho Cucamonga");
      californiaList.add("Ontario");
      californiaList.add("Lancaster");
      californiaList.add("Elk Grove");
      californiaList.add("Palmdale");
      californiaList.add("Corona");
      californiaList.add("Salinas");
      californiaList.add("Pomona");
      californiaList.add("Torrance");
      californiaList.add("Hayward");
      californiaList.add("Escondido");
      californiaList.add("Sunnyvale");
      californiaList.add("Pasadena");
      californiaList.add("Orange");
      californiaList.add("Fullerton");
      californiaList.add("Thousand Oaks");
      californiaList.add("Visalia");
      californiaList.add("Simi Valley");
      californiaList.add("Concord");
      californiaList.add("Roseville");
      californiaList.add("Santa Clara");
      californiaList.add("Vallejo");
      californiaList.add("Victorville");
      californiaList.add("El Monte");
      californiaList.add("Berkeley");
      californiaList.add("Downey");
      californiaList.add("Costa Mesa");
      californiaList.add("Inglewood");
      californiaList.add("San Buenaventura (Ventura)");
      californiaList.add("West Covina");
      californiaList.add("Norwalk");
      californiaList.add("Carlsbad");
      californiaList.add("Fairfield");
      californiaList.add("Richmond");
      californiaList.add("Murrieta");
      californiaList.add("Burbank");
      californiaList.add("Antioch");
      californiaList.add("Daly City");
      californiaList.add("Temecula");
      coloradoList.add("Denver ");
      coloradoList.add("Colorado Springs");
      coloradoList.add("Aurora");
      coloradoList.add("Fort Collins");
      coloradoList.add("Lakewood");
      coloradoList.add("Thornton");
      coloradoList.add("Pueblo");
      coloradoList.add("Arvada");
      coloradoList.add("Westminster");
      coloradoList.add("Centennial");
      connecticutList.add("Bridgeport");
      connecticutList.add("New Haven");
      connecticutList.add("Hartford");
      connecticutList.add("Stamford");
      connecticutList.add("Waterbury");
      districtofcolumbiaList.add("Washington");
      floridaList.add("Jacksonville");
      floridaList.add("Miami");
      floridaList.add("Tampa");
      floridaList.add("Saint Petersburg");
      floridaList.add("Orlando");
      floridaList.add("Hialeah");
      floridaList.add("Tallahassee");
      floridaList.add("Fort Lauderdale");
      floridaList.add("Port Saint Lucie");
      floridaList.add("Pembroke Pines");
      floridaList.add("Cape Coral");
      floridaList.add("Hollywood");
      floridaList.add("Gainesville");
      floridaList.add("Miramar");
      floridaList.add("Coral Springs");
      floridaList.add("Clearwater");
      floridaList.add("Miami Gardens");
      floridaList.add("Palm Bay");
      georgiaList.add("Atlanta");
      georgiaList.add("Augusta");
      georgiaList.add("Columbus");
      georgiaList.add("Savannah");
      georgiaList.add("Athens");
      hawaiiList.add("Honolulu");
      idahoList.add("Boise");
      illinoisList.add("Chicago");
      illinoisList.add("Aurora");
      illinoisList.add("Rockford");
      illinoisList.add("Joliet");
      illinoisList.add("Naperville");
      illinoisList.add("Springfield");
      illinoisList.add("Peoria");
      illinoisList.add("Elgin");
      indianaList.add("Indianapolis");
      indianaList.add("Fort Wayne");
      indianaList.add("Evansville");
      indianaList.add("South Bend");
      iowaList.add("Des Moines");
      iowaList.add("Cedar Rapids");
      kansasList.add("Wichita");
      kansasList.add("Overland Park");
      kansasList.add("Kansas City");
      kansasList.add("Topeka");
      kansasList.add("Olathe");
      kentuckyList.add("Louisville");
      kentuckyList.add("Lexington");
      louisianaList.add("New Orleans");
      louisianaList.add("Baton Rouge");
      louisianaList.add("Shreveport");
      louisianaList.add("Lafayette");
      marylandList.add("Baltimore");
      massachusettsList.add("Boston");
      massachusettsList.add("Worcester");
      massachusettsList.add("Springfield");
      massachusettsList.add("Lowell");
      massachusettsList.add("Cambridge");
      michiganList.add("Detroit");
      michiganList.add("Grand Rapids");
      michiganList.add("Warren");
      michiganList.add("Sterling Heights");
      michiganList.add("Lansing");
      michiganList.add("Ann Arbor");
      michiganList.add("Flint");
      minnesotaList.add("Minneapolis");
      minnesotaList.add("Saint Paul");
      minnesotaList.add("Rochester");
      mississippiList.add("Jackson");
      missouriList.add("Kansas City");
      missouriList.add("Saint Louis");
      missouriList.add("Springfield");
      missouriList.add("Independence");
      missouriList.add("Columbia");
      montanaList.add("Billings");
      nebraskaList.add("Omaha");
      nebraskaList.add("Lincoln");
      nevadaList.add("Las Vegas");
      nevadaList.add("Henderson");
      nevadaList.add("Reno");
      nevadaList.add("North Las Vegas");
      newhampshireList.add("Manchester");
      newjerseyList.add("Newark");
      newjerseyList.add("Jersey City");
      newjerseyList.add("Paterson");
      newjerseyList.add("Elizabeth");
      newmexicoList.add("Albuquerque");
      newyorkList.add("New York");
      newyorkList.add("Buffalo");
      newyorkList.add("Rochester");
      newyorkList.add("Yonkers");
      newyorkList.add("Syracuse");
      northcarolinaList.add("Charlotte");
      northcarolinaList.add("Raleigh");
      northcarolinaList.add("Greensboro");
      northcarolinaList.add("Winston-Salem");
      northcarolinaList.add("Durham");
      northcarolinaList.add("Fayetteville");
      northcarolinaList.add("Cary");
      northcarolinaList.add("Wilmington");
      northcarolinaList.add("High Point");
      northdakotaList.add("Fargo");
      ohioList.add("Columbus");
      ohioList.add("Cleveland");
      ohioList.add("Cincinnati");
      ohioList.add("Toledo");
      ohioList.add("Akron");
      ohioList.add("Dayton");
      oklahomaList.add("Oklahoma City");
      oklahomaList.add("Tulsa");
      oklahomaList.add("Norman");
      oregonList.add("Portland");
      oregonList.add("Eugene");
      oregonList.add("Salem");
      oregonList.add("Gresham");
      pennsylvaniaList.add("Philadelphia");
      pennsylvaniaList.add("Pittsburgh");
      pennsylvaniaList.add("Allentown");
      pennsylvaniaList.add("Erie");
      rhodeislandList.add("Providence");
      southcarolinaList.add("Columbia");
      southcarolinaList.add("Charleston");
      southdakotaList.add("Sioux Falls");
      tennesseeList.add("Memphis");
      tennesseeList.add("Nashville ");
      tennesseeList.add("Knoxville");
      tennesseeList.add("Chattanooga");
      tennesseeList.add("Clarksville");
      tennesseeList.add("Murfreesboro");
      texasList.add("Houston");
      texasList.add("San Antonio");
      texasList.add("Dallas");
      texasList.add("Austin");
      texasList.add("Fort Worth");
      texasList.add("El Paso");
      texasList.add("Arlington");
      texasList.add("Corpus Christi");
      texasList.add("Plano");
      texasList.add("Laredo");
      texasList.add("Lubbock");
      texasList.add("Garland");
      texasList.add("Irving");
      texasList.add("Amarillo");
      texasList.add("Grand Prairie");
      texasList.add("Brownsville");
      texasList.add("Pasadena");
      texasList.add("Mesquite");
      texasList.add("McKinney");
      texasList.add("McAllen");
      texasList.add("Killeen");
      texasList.add("Waco");
      texasList.add("Carrollton");
      texasList.add("Beaumont");
      texasList.add("Abilene");
      texasList.add("Frisco");
      texasList.add("Denton");
      texasList.add("Midland");
      texasList.add("Wichita Falls");
      utahList.add("Salt Lake City");
      utahList.add("West Valley City");
      utahList.add("Provo");
      utahList.add("West Jordan");
      virginiaList.add("Virginia Beach");
      virginiaList.add("Norfolk");
      virginiaList.add("Chesapeake");
      virginiaList.add("Arlington");
      virginiaList.add("Richmond");
      virginiaList.add("Newport News");
      virginiaList.add("Alexandria");
      virginiaList.add("Hampton");
      washingtonList.add("Seattle");
      washingtonList.add("Spokane");
      washingtonList.add("Tacoma");
      washingtonList.add("Vancouver");
      washingtonList.add("Bellevue");
      washingtonList.add("Everett");
      wisconsinList.add("Milwaukee");
      wisconsinList.add("Madison");
      wisconsinList.add("Green Bay");
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Put lists on Map">
      stateCityMap.put("Alabama", alabamaList);
      stateCityMap.put("Alaska", alaskaList);
      stateCityMap.put("Arizona", arizonaList);
      stateCityMap.put("Arkansas", arkansasList);
      stateCityMap.put("California", californiaList);
      stateCityMap.put("Colorado", coloradoList);
      stateCityMap.put("Connecticut", connecticutList);
      stateCityMap.put("Delaware", delawareList);
      stateCityMap.put("District of Columbia", districtofcolumbiaList);
      stateCityMap.put("Florida", floridaList);
      stateCityMap.put("Georgia", georgiaList);
      stateCityMap.put("Hawaii", hawaiiList);
      stateCityMap.put("Idaho", idahoList);
      stateCityMap.put("Illinois", illinoisList);
      stateCityMap.put("Indiana", indianaList);
      stateCityMap.put("Iowa", iowaList);
      stateCityMap.put("Kansas", kansasList);
      stateCityMap.put("Kentucky", kentuckyList);
      stateCityMap.put("Louisiana", louisianaList);
      stateCityMap.put("Maine", maineList);
      stateCityMap.put("Maryland", marylandList);
      stateCityMap.put("Massachusetts", massachusettsList);
      stateCityMap.put("Michigan", michiganList);
      stateCityMap.put("Minnesota", minnesotaList);
      stateCityMap.put("Mississippi", mississippiList);
      stateCityMap.put("Missouri", missouriList);
      stateCityMap.put("Montana", montanaList);
      stateCityMap.put("Nebraska", nebraskaList);
      stateCityMap.put("Nevada", nevadaList);
      stateCityMap.put("New Hampshire", newhampshireList);
      stateCityMap.put("New Jersey", newjerseyList);
      stateCityMap.put("New Mexico", newmexicoList);
      stateCityMap.put("New York", newyorkList);
      stateCityMap.put("North Carolina", northcarolinaList);
      stateCityMap.put("North Dakota", northdakotaList);
      stateCityMap.put("Ohio", ohioList);
      stateCityMap.put("Oklahoma", oklahomaList);
      stateCityMap.put("Oregon", oregonList);
      stateCityMap.put("Pennsylvania", pennsylvaniaList);
      stateCityMap.put("Rhode Island", rhodeislandList);
      stateCityMap.put("South Carolina", southcarolinaList);
      stateCityMap.put("South Dakota", southdakotaList);
      stateCityMap.put("Tennessee", tennesseeList);
      stateCityMap.put("Texas", texasList);
      stateCityMap.put("Utah", utahList);
      stateCityMap.put("Vermont", vermontList);
      stateCityMap.put("Virginia", virginiaList);
      stateCityMap.put("Washington", washingtonList);
      stateCityMap.put("West Virginia", westvirginiaList);
      stateCityMap.put("Wisconsin", wisconsinList);
      stateCityMap.put("Wyoming", wyomingList);
      //</editor-fold>
    }
    return stateCityMap;
  }
  //</editor-fold>

  /**
   * This randomly generates three numbers and inserts them as the phone number Really simple, and formats
   * like this: NNN-NNN-NNNN
   *
   * @return
   */
  public static String getRandomPhoneNumber() {
    Random rand = new Random();
    int first = rand.nextInt(999);
    int second = rand.nextInt(999);
    int third = rand.nextInt(9999);
    return getFullNumber(Integer.toString(first), 3) + "-" + getFullNumber(Integer.toString(second), 3) + "-" + getFullNumber(Integer.toString(third), 4);
  }

  /**
   * This ensures that all digits of the phone number are filled (leading zeros)
   *
   * @param num
   * @param digits
   * @return
   */
  public static String getFullNumber(String num, int digits) {
    if (num.length() == digits) {
      return num;
    } else {
      String fullNumber = "";
      for (int i = 0; i < digits - num.length(); i++) {
        fullNumber += "0";
      }
      return fullNumber + num;
    }
  }

  /**
   * Returns the opposite of what's given (if you give an abbreviation, it will return it's long name and vice
   * versa
   *
   * @param string
   * @return
   */
  public static String getState(String string) {
    return (string.length() > 2) ? getLongStatesMap().get(string) : getShortStatesMap().get(string);
  }

  /**
   * Returns the given city's state (if it exists in the map)
   *
   * @param city
   * @return
   */
  public static String getCitysState(String city) {
    return cityStateMap.get(city);
  }

  /**
   * Returns a
   *
   * @param state
   * @return
   */
  public static String getRandomCity(String state) {
    return (state.length() > 2) ? getRandomFromList(stateCityMap.get(state)) : getRandomCity(getState(state));
  }

  /**
   * This returns a random name from a list of 200 names
   *
   * @return
   */
  public static String getRandomFirstName() {
    return getRandomFromArray(firstNames);
  }

  /**
   * This returns a random name from a list of 200 names
   *
   * @return
   */
  public static String getRandomLastName() {
    return getRandomFromArray(lastNames);
  }

  /**
   * Returns random state abbreviation
   *
   * @return
   */
  public static String getRandomStateAbb() {
    return getRandomFromArray(shortStates);
  }

  /**
   * Returns random state abbreviation
   *
   * @return
   */
  public static String getRandomState() {
    return getRandomFromArray(longStates);
  }

  /**
   * Returns a random street address from 200 random street addresses
   *
   * @return
   */
  public static String getRandomStreetAddress() {
    return getRandomFromArray(streetAddresses);
  }

  /**
   * Returns a random item in the given array.
   *
   * @param arry
   * @return
   */
  public static String getRandomFromArray(String[] arry) {
    int randomInt = 0;
    try {
      randomInt = new Random().nextInt(arry.length);
      return arry[randomInt];
    } catch (Exception e) {
      System.out.println("Random Int: " + randomInt);
      System.out.println("List Size: " + arry.length);
      return null;
    }
  }

  /**
   * Returns a random item in the given array.
   *
   * @param list
   * @return
   */
  public static String getRandomFromList(List<String> list) {
    int randomInt = 0;
    try {
      randomInt = new Random().nextInt((list.size()));
      return list.get(randomInt);
    } catch (Exception e) {
      System.out.println("Random Int: " + randomInt);
      System.out.println("List Size: " + list.size());
      return null;
    }
  }

  /**
   * This returns a random date between the two given days. This is more powerful than getRandomDate(int
   * year1, int year2), but requires a few more lines to setup the Calendar objects
   *
   * @param day1
   * @param day2
   * @return
   */
  public static Date getRandomDate(Calendar day1, Calendar day2) {
    long val1 = day1.getTimeInMillis();
    long val2 = day2.getTimeInMillis();
    Random r = new Random();
    long randomTS = (long) (r.nextDouble() * (val2 - val1)) + val1;
    return new Date(randomTS);
  }

  /**
   * This will return a random date (generated by getRandomDate(Calendar day1, Calendar day2)) in a string
   * formatted with the specified format
   *
   * @param day1
   * @param day2
   * @param format
   * @return
   */
  public static String getRandomDate(Calendar day1, Calendar day2, String format) {
    Date date = getRandomDate(day1, day2);
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.format(date);
  }

  /**
   * This will return a random date between the first day of year1 and the last day of year2
   *
   * @param year1
   * @param year2
   * @return
   */
  public static Date getRandomDate(int year1, int year2) {
    Calendar day1 = Calendar.getInstance();
    day1.set(Calendar.YEAR, (year1));
    day1.set(Calendar.DAY_OF_YEAR, day1.getActualMinimum(Calendar.DAY_OF_YEAR));
    long val1 = day1.getTimeInMillis();

    Calendar day2 = Calendar.getInstance();
    day2.set(Calendar.YEAR, (year2));
    day2.set(Calendar.DAY_OF_YEAR, day2.getActualMaximum(Calendar.DAY_OF_YEAR));
    long val2 = day2.getTimeInMillis();

    Random r = new Random();
    long randomTS = (long) (r.nextDouble() * (val2 - val1)) + val1;
    return new Date(randomTS);
  }

  /**
   * This will return a random date (generated by getRandomDate(int year1, int year2)) in a string formatted
   * with the specified format
   *
   * @param year1
   * @param year2
   * @param format
   * @return
   */
  public static String getRandomDate(int year1, int year2, String format) {
    Date date = getRandomDate(year1, year2);
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.format(date);
  }

  /**
   * Just takes the first name's first character and the last name and the company and makes a simple string
   * for the given person's e-mail address
   *
   * @param firstName
   * @param lastName
   * @param company
   * @return
   */
  public static String getEmail(String firstName, String lastName, String company) {
    return (firstName.substring(0, 1) + lastName + "@" + company + ".com").toLowerCase();
  }

  /**
   * Returns a random zip 5 digit number from 0 and adds the leading 0s
   *
   * @return
   */
  public static String getRandomZipCode() {
    String randomNumber = Integer.toString(new Random().nextInt(100000));
    int lessThan5 = 5 - randomNumber.length();
    String leadingZeros = "";
    for (int i = 0; i < lessThan5; i++) {
      leadingZeros += "0";
    }
    String ret = leadingZeros + randomNumber;
    return ret;
  }

  /**
   * Returns a valid random MAC address in all caps
   *
   * @return
   */
  public static String getRandomMacAddress() {
    return randomHex() + "-"
            + randomHex() + "-"
            + randomHex() + "-"
            + randomHex() + "-"
            + randomHex() + "-"
            + randomHex();
  }

  /**
   * Returns a random two digit hex value (helper for the getRandomMacAddress() method)
   *
   * @return
   */
  public static String randomHex() {
    Random rand = new Random();
    String hex = Integer.toHexString(rand.nextInt(256));
    if (hex.length() < 2) {
      hex = "0" + hex;
    }
    return hex.toUpperCase();
  }
}
