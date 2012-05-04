package com.kentcdodds.javahelper.helpers;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Kent
 */
public class RandomHelper {

  public static final String[] firstNames = new String[]{
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
  public static final String[] lastNames = new String[]{
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
  public static final String[] longStates = new String[]{
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
  public static final String[] shortStates = new String[]{
    //<editor-fold defaultstate="collapsed" desc="State Abbreviations">
    "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "VA", "WA", "WV",
    "WI", "WY", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
    "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
    "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
    "UT", "VT"};
  //</editor-fold>
  public static final String[] streetAddresses = new String[]{
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
  public static final Map<String, String> longStatesMap = getLongStatesMap();
  public static final Map<String, String> shortStatesMap = getShortStatesMap();
  public static final Map<String, String> cityStateMap = getCityStateMap();
  public static final Map<String, List<String>> stateCityMap = getStateCityMap();

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

  //<editor-fold defaultstate="collapsed" desc="Map setters">
  public static Map getLongStatesMap() {
    Map map = new TreeMap<String, String>();
    map.put("Alabama", "AL");
    map.put("Alaska", "AK");
    map.put("Arizona", "AZ");
    map.put("Arkansas", "AR");
    map.put("California", "CA");
    map.put("Colorado", "CO");
    map.put("Connecticut", "CT");
    map.put("District of Columbia", "DC");
    map.put("Delaware", "DE");
    map.put("Florida", "FL");
    map.put("Georgia", "GA");
    map.put("Hawaii", "HI");
    map.put("Idaho", "ID");
    map.put("Illinois", "IL");
    map.put("Indiana", "IN");
    map.put("Iowa", "IA");
    map.put("Kansas", "KS");
    map.put("Kentucky", "KY");
    map.put("Louisiana", "LA");
    map.put("Maine", "ME");
    map.put("Maryland", "MD");
    map.put("Massachusetts", "MA");
    map.put("Michigan", "MI");
    map.put("Minnesota", "MN");
    map.put("Mississippi", "MS");
    map.put("Missouri", "MO");
    map.put("Montana", "MT");
    map.put("Nebraska", "NE");
    map.put("Nevada", "NV");
    map.put("New Hampshire", "NH");
    map.put("New Jersey", "NJ");
    map.put("New Mexico", "NM");
    map.put("New York", "NY");
    map.put("North Carolina", "NC");
    map.put("North Dakota", "ND");
    map.put("Ohio", "OH");
    map.put("Oklahoma", "OK");
    map.put("Oregon", "OR");
    map.put("Pennsylvania", "PA");
    map.put("Rhode Island", "RI");
    map.put("South Carolina", "SC");
    map.put("South Dakota", "SD");
    map.put("Tennessee", "TN");
    map.put("Texas", "TX");
    map.put("Utah", "UT");
    map.put("Vermont", "VT");
    map.put("Virginia", "VA");
    map.put("Washington", "WA");
    map.put("West Virginia", "WV");
    map.put("Wisconsin", "WI");
    map.put("Wyoming", "WY");
    return map;
  }

  public static Map getShortStatesMap() {
    Map map = new TreeMap<String, String>();
    map.put("AL", "Alabama");
    map.put("AK", "Alaska");
    map.put("AZ", "Arizona");
    map.put("AR", "Arkansas");
    map.put("CA", "California");
    map.put("CO", "Colorado");
    map.put("CT", "Connecticut");
    map.put("DC", "District of Columbia");
    map.put("DE", "Delaware");
    map.put("FL", "Florida");
    map.put("GA", "Georgia");
    map.put("HI", "Hawaii");
    map.put("ID", "Idaho");
    map.put("IL", "Illinois");
    map.put("IN", "Indiana");
    map.put("IA", "Iowa");
    map.put("KS", "Kansas");
    map.put("KY", "Kentucky");
    map.put("LA", "Louisiana");
    map.put("ME", "Maine");
    map.put("MD", "Maryland");
    map.put("MA", "Massachusetts");
    map.put("MI", "Michigan");
    map.put("MN", "Minnesota");
    map.put("MS", "Mississippi");
    map.put("MO", "Missouri");
    map.put("MT", "Montana");
    map.put("NE", "Nebraska");
    map.put("NV", "Nevada");
    map.put("NH", "New Hampshire");
    map.put("NJ", "New Jersey");
    map.put("NM", "New Mexico");
    map.put("NY", "New York");
    map.put("NC", "North Carolina");
    map.put("ND", "North Dakota");
    map.put("OH", "Ohio");
    map.put("OK", "Oklahoma");
    map.put("OR", "Oregon");
    map.put("PA", "Pennsylvania");
    map.put("RI", "Rhode Island");
    map.put("SC", "South Carolina");
    map.put("SD", "South Dakota");
    map.put("TN", "Tennessee");
    map.put("TX", "Texas");
    map.put("UT", "Utah");
    map.put("VT", "Vermont");
    map.put("VA", "Virginia");
    map.put("WA", "Washington");
    map.put("WV", "West Virginia");
    map.put("WI", "Wisconsin");
    map.put("WY", "Wyoming");
    return map;
  }

  public static Map getCityStateMap() {
    Map<String, String> map = new TreeMap<String, String>();
    map.put("New York", "New York");
    map.put("Los Angeles", "California");
    map.put("Chicago", "Illinois");
    map.put("Houston", "Texas");
    map.put("Philadelphia", "Pennsylvania");
    map.put("Phoenix", "Arizona");
    map.put("San Antonio", "Texas");
    map.put("San Diego", "California");
    map.put("Dallas", "Texas");
    map.put("San Jose", "California");
    map.put("Jacksonville", "Florida");
    map.put("Indianapolis", "Indiana");
    map.put("San Francisco", "California");
    map.put("Austin", "Texas");
    map.put("Columbus", "Ohio");
    map.put("Fort Worth", "Texas");
    map.put("Charlotte", "North Carolina");
    map.put("Detroit", "Michigan");
    map.put("El Paso", "Texas");
    map.put("Memphis", "Tennessee");
    map.put("Baltimore", "Maryland");
    map.put("Boston", "Massachusetts");
    map.put("Seattle", "Washington");
    map.put("Washington", "District of Columbia");
    map.put("Nashville ", "Tennessee");
    map.put("Denver ", "Colorado");
    map.put("Louisville", "Kentucky");
    map.put("Milwaukee", "Wisconsin");
    map.put("Portland", "Oregon");
    map.put("Las Vegas", "Nevada");
    map.put("Oklahoma City", "Oklahoma");
    map.put("Albuquerque", "New Mexico");
    map.put("Tucson", "Arizona");
    map.put("Fresno", "California");
    map.put("Sacramento", "California");
    map.put("Long Beach", "California");
    map.put("Kansas City", "Missouri");
    map.put("Mesa", "Arizona");
    map.put("Virginia Beach ", "Virginia");
    map.put("Atlanta", "Georgia");
    map.put("Colorado Springs", "Colorado");
    map.put("Omaha", "Nebraska");
    map.put("Raleigh", "North Carolina");
    map.put("Miami", "Florida");
    map.put("Cleveland", "Ohio");
    map.put("Tulsa", "Oklahoma");
    map.put("Oakland", "California");
    map.put("Minneapolis", "Minnesota");
    map.put("Wichita", "Kansas");
    map.put("Arlington", "Texas");
    map.put("Bakersfield", "California");
    map.put("New Orleans", "Louisiana");
    map.put("Honolulu ", "Hawaii");
    map.put("Anaheim", "California");
    map.put("Tampa", "Florida");
    map.put("Aurora", "Colorado");
    map.put("Santa Ana", "California");
    map.put("Saint Louis ", "Missouri");
    map.put("Pittsburgh", "Pennsylvania");
    map.put("Corpus Christi", "Texas");
    map.put("Riverside", "California");
    map.put("Cincinnati", "Ohio");
    map.put("Lexington", "Kentucky");
    map.put("Anchorage", "Alaska");
    map.put("Stockton", "California");
    map.put("Toledo", "Ohio");
    map.put("Saint Paul", "Minnesota");
    map.put("Newark", "New Jersey");
    map.put("Greensboro", "North Carolina");
    map.put("Buffalo", "New York");
    map.put("Plano", "Texas");
    map.put("Lincoln", "Nebraska");
    map.put("Henderson", "Nevada");
    map.put("Fort Wayne", "Indiana");
    map.put("Jersey City", "New Jersey");
    map.put("Saint Petersburg", "Florida");
    map.put("Chula Vista", "California");
    map.put("Norfolk ", "Virginia");
    map.put("Orlando", "Florida");
    map.put("Chandler", "Arizona");
    map.put("Laredo", "Texas");
    map.put("Madison", "Wisconsin");
    map.put("Winston-Salem", "North Carolina");
    map.put("Lubbock", "Texas");
    map.put("Baton Rouge", "Louisiana");
    map.put("Durham", "North Carolina");
    map.put("Garland", "Texas");
    map.put("Glendale", "Arizona");
    map.put("Reno", "Nevada");
    map.put("Hialeah", "Florida");
    map.put("Chesapeake ", "Virginia");
    map.put("Scottsdale", "Arizona");
    map.put("North Las Vegas", "Nevada");
    map.put("Irving", "Texas");
    map.put("Fremont", "California");
    map.put("Irvine", "California");
    map.put("Birmingham", "Alabama");
    map.put("Rochester", "New York");
    map.put("San Bernardino", "California");
    map.put("Spokane", "Washington");
    map.put("Gilbert", "Arizona");
    map.put("Arlington ", "Virginia");
    map.put("Montgomery", "Alabama");
    map.put("Boise", "Idaho");
    map.put("Richmond ", "Virginia");
    map.put("Des Moines", "Iowa");
    map.put("Modesto", "California");
    map.put("Fayetteville", "North Carolina");
    map.put("Shreveport", "Louisiana");
    map.put("Akron", "Ohio");
    map.put("Tacoma", "Washington");
    map.put("Aurora", "Illinois");
    map.put("Oxnard", "California");
    map.put("Fontana", "California");
    map.put("Yonkers", "New York");
    map.put("Augusta ", "Georgia");
    map.put("Mobile", "Alabama");
    map.put("Little Rock", "Arkansas");
    map.put("Moreno Valley", "California");
    map.put("Glendale", "California");
    map.put("Amarillo", "Texas");
    map.put("Huntington Beach", "California");
    map.put("Columbus", "Georgia");
    map.put("Grand Rapids", "Michigan");
    map.put("Salt Lake City", "Utah");
    map.put("Tallahassee", "Florida");
    map.put("Worcester", "Massachusetts");
    map.put("Newport News ", "Virginia");
    map.put("Huntsville", "Alabama");
    map.put("Knoxville", "Tennessee");
    map.put("Providence", "Rhode Island");
    map.put("Santa Clarita", "California");
    map.put("Grand Prairie", "Texas");
    map.put("Brownsville", "Texas");
    map.put("Jackson", "Mississippi");
    map.put("Overland Park", "Kansas");
    map.put("Garden Grove", "California");
    map.put("Santa Rosa", "California");
    map.put("Chattanooga", "Tennessee");
    map.put("Oceanside", "California");
    map.put("Fort Lauderdale", "Florida");
    map.put("Rancho Cucamonga", "California");
    map.put("Port Saint Lucie", "Florida");
    map.put("Ontario", "California");
    map.put("Vancouver", "Washington");
    map.put("Tempe", "Arizona");
    map.put("Springfield", "Missouri");
    map.put("Lancaster", "California");
    map.put("Eugene", "Oregon");
    map.put("Pembroke Pines", "Florida");
    map.put("Salem", "Oregon");
    map.put("Cape Coral", "Florida");
    map.put("Peoria", "Arizona");
    map.put("Sioux Falls", "South Dakota");
    map.put("Springfield", "Massachusetts");
    map.put("Elk Grove", "California");
    map.put("Rockford", "Illinois");
    map.put("Palmdale", "California");
    map.put("Corona", "California");
    map.put("Salinas", "California");
    map.put("Pomona", "California");
    map.put("Pasadena", "Texas");
    map.put("Joliet", "Illinois");
    map.put("Paterson", "New Jersey");
    map.put("Kansas City", "Kansas");
    map.put("Torrance", "California");
    map.put("Syracuse", "New York");
    map.put("Bridgeport", "Connecticut");
    map.put("Hayward", "California");
    map.put("Fort Collins", "Colorado");
    map.put("Escondido", "California");
    map.put("Lakewood", "Colorado");
    map.put("Naperville", "Illinois");
    map.put("Dayton", "Ohio");
    map.put("Hollywood", "Florida");
    map.put("Sunnyvale", "California");
    map.put("Alexandria ", "Virginia");
    map.put("Mesquite", "Texas");
    map.put("Hampton ", "Virginia");
    map.put("Pasadena", "California");
    map.put("Orange", "California");
    map.put("Savannah", "Georgia");
    map.put("Cary", "North Carolina");
    map.put("Fullerton", "California");
    map.put("Warren", "Michigan");
    map.put("Clarksville", "Tennessee");
    map.put("McKinney", "Texas");
    map.put("McAllen", "Texas");
    map.put("New Haven", "Connecticut");
    map.put("Sterling Heights", "Michigan");
    map.put("West Valley City", "Utah");
    map.put("Columbia", "South Carolina");
    map.put("Killeen", "Texas");
    map.put("Topeka", "Kansas");
    map.put("Thousand Oaks", "California");
    map.put("Cedar Rapids", "Iowa");
    map.put("Olathe", "Kansas");
    map.put("Elizabeth", "New Jersey");
    map.put("Waco", "Texas");
    map.put("Hartford", "Connecticut");
    map.put("Visalia", "California");
    map.put("Gainesville", "Florida");
    map.put("Simi Valley", "California");
    map.put("Stamford", "Connecticut");
    map.put("Bellevue", "Washington");
    map.put("Concord", "California");
    map.put("Miramar", "Florida");
    map.put("Coral Springs", "Florida");
    map.put("Lafayette", "Louisiana");
    map.put("Charleston", "South Carolina");
    map.put("Carrollton", "Texas");
    map.put("Roseville", "California");
    map.put("Thornton", "Colorado");
    map.put("Beaumont", "Texas");
    map.put("Allentown", "Pennsylvania");
    map.put("Surprise", "Arizona");
    map.put("Evansville", "Indiana");
    map.put("Abilene", "Texas");
    map.put("Frisco", "Texas");
    map.put("Independence", "Missouri");
    map.put("Santa Clara", "California");
    map.put("Springfield", "Illinois");
    map.put("Vallejo", "California");
    map.put("Victorville", "California");
    map.put("Athens ", "Georgia");
    map.put("Peoria", "Illinois");
    map.put("Lansing", "Michigan");
    map.put("Ann Arbor", "Michigan");
    map.put("El Monte", "California");
    map.put("Denton", "Texas");
    map.put("Berkeley", "California");
    map.put("Provo", "Utah");
    map.put("Downey", "California");
    map.put("Midland", "Texas");
    map.put("Norman", "Oklahoma");
    map.put("Waterbury", "Connecticut");
    map.put("Costa Mesa", "California");
    map.put("Inglewood", "California");
    map.put("Manchester", "New Hampshire");
    map.put("Murfreesboro", "Tennessee");
    map.put("Columbia", "Missouri");
    map.put("Elgin", "Illinois");
    map.put("Clearwater", "Florida");
    map.put("Miami Gardens", "Florida");
    map.put("Rochester", "Minnesota");
    map.put("Pueblo", "Colorado");
    map.put("Lowell", "Massachusetts");
    map.put("Wilmington", "North Carolina");
    map.put("Arvada", "Colorado");
    map.put("San Buenaventura (Ventura)", "California");
    map.put("Westminster", "Colorado");
    map.put("West Covina", "California");
    map.put("Gresham", "Oregon");
    map.put("Fargo", "North Dakota");
    map.put("Norwalk", "California");
    map.put("Carlsbad", "California");
    map.put("Fairfield", "California");
    map.put("Cambridge", "Massachusetts");
    map.put("Wichita Falls", "Texas");
    map.put("High Point", "North Carolina");
    map.put("Billings", "Montana");
    map.put("Green Bay", "Wisconsin");
    map.put("West Jordan", "Utah");
    map.put("Richmond", "California");
    map.put("Murrieta", "California");
    map.put("Burbank", "California");
    map.put("Palm Bay", "Florida");
    map.put("Everett", "Washington");
    map.put("Flint", "Michigan");
    map.put("Antioch", "California");
    map.put("Erie", "Pennsylvania");
    map.put("South Bend", "Indiana");
    map.put("Daly City", "California");
    map.put("Centennial", "Colorado");
    map.put("Temecula", "California");
    return map;
  }

  public static Map getStateCityMap() {
    Map<String, List<String>> map = new TreeMap<String, List<String>>();
    //<editor-fold defaultstate="collapsed" desc="States Lists">
    List<String> alabamaList = new ArrayList<String>();
    List<String> alaskaList = new ArrayList<String>();
    List<String> arizonaList = new ArrayList<String>();
    List<String> arkansasList = new ArrayList<String>();
    List<String> californiaList = new ArrayList<String>();
    List<String> coloradoList = new ArrayList<String>();
    List<String> connecticutList = new ArrayList<String>();
    List<String> districtofcolumbiaList = new ArrayList<String>();
    List<String> floridaList = new ArrayList<String>();
    List<String> georgiaList = new ArrayList<String>();
    List<String> hawaiiList = new ArrayList<String>();
    List<String> idahoList = new ArrayList<String>();
    List<String> illinoisList = new ArrayList<String>();
    List<String> indianaList = new ArrayList<String>();
    List<String> iowaList = new ArrayList<String>();
    List<String> kansasList = new ArrayList<String>();
    List<String> kentuckyList = new ArrayList<String>();
    List<String> louisianaList = new ArrayList<String>();
    List<String> marylandList = new ArrayList<String>();
    List<String> massachusettsList = new ArrayList<String>();
    List<String> michiganList = new ArrayList<String>();
    List<String> minnesotaList = new ArrayList<String>();
    List<String> mississippiList = new ArrayList<String>();
    List<String> missouriList = new ArrayList<String>();
    List<String> montanaList = new ArrayList<String>();
    List<String> nebraskaList = new ArrayList<String>();
    List<String> nevadaList = new ArrayList<String>();
    List<String> newhampshireList = new ArrayList<String>();
    List<String> newjerseyList = new ArrayList<String>();
    List<String> newmexicoList = new ArrayList<String>();
    List<String> newyorkList = new ArrayList<String>();
    List<String> northcarolinaList = new ArrayList<String>();
    List<String> northdakotaList = new ArrayList<String>();
    List<String> ohioList = new ArrayList<String>();
    List<String> oklahomaList = new ArrayList<String>();
    List<String> oregonList = new ArrayList<String>();
    List<String> pennsylvaniaList = new ArrayList<String>();
    List<String> rhodeislandList = new ArrayList<String>();
    List<String> southcarolinaList = new ArrayList<String>();
    List<String> southdakotaList = new ArrayList<String>();
    List<String> tennesseeList = new ArrayList<String>();
    List<String> texasList = new ArrayList<String>();
    List<String> utahList = new ArrayList<String>();
    List<String> virginiaList = new ArrayList<String>();
    List<String> washingtonList = new ArrayList<String>();
    List<String> wisconsinList = new ArrayList<String>();
    List<String> delawareList = new ArrayList<String>();
    List<String> maineList = new ArrayList<String>();
    List<String> vermontList = new ArrayList<String>();
    List<String> westvirginiaList = new ArrayList<String>();
    List<String> wyomingList = new ArrayList<String>();
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
    map.put("Alabama", alabamaList);
    map.put("Alaska", alaskaList);
    map.put("Arizona", arizonaList);
    map.put("Arkansas", arkansasList);
    map.put("California", californiaList);
    map.put("Colorado", coloradoList);
    map.put("Connecticut", connecticutList);
    map.put("Delaware", delawareList);
    map.put("District of Columbia", districtofcolumbiaList);
    map.put("Florida", floridaList);
    map.put("Georgia", georgiaList);
    map.put("Hawaii", hawaiiList);
    map.put("Idaho", idahoList);
    map.put("Illinois", illinoisList);
    map.put("Indiana", indianaList);
    map.put("Iowa", iowaList);
    map.put("Kansas", kansasList);
    map.put("Kentucky", kentuckyList);
    map.put("Louisiana", louisianaList);
    map.put("Maine", maineList);
    map.put("Maryland", marylandList);
    map.put("Massachusetts", massachusettsList);
    map.put("Michigan", michiganList);
    map.put("Minnesota", minnesotaList);
    map.put("Mississippi", mississippiList);
    map.put("Missouri", missouriList);
    map.put("Montana", montanaList);
    map.put("Nebraska", nebraskaList);
    map.put("Nevada", nevadaList);
    map.put("New Hampshire", newhampshireList);
    map.put("New Jersey", newjerseyList);
    map.put("New Mexico", newmexicoList);
    map.put("New York", newyorkList);
    map.put("North Carolina", northcarolinaList);
    map.put("North Dakota", northdakotaList);
    map.put("Ohio", ohioList);
    map.put("Oklahoma", oklahomaList);
    map.put("Oregon", oregonList);
    map.put("Pennsylvania", pennsylvaniaList);
    map.put("Rhode Island", rhodeislandList);
    map.put("South Carolina", southcarolinaList);
    map.put("South Dakota", southdakotaList);
    map.put("Tennessee", tennesseeList);
    map.put("Texas", texasList);
    map.put("Utah", utahList);
    map.put("Vermont", vermontList);
    map.put("Virginia", virginiaList);
    map.put("Washington", washingtonList);
    map.put("West Virginia", westvirginiaList);
    map.put("Wisconsin", wisconsinList);
    map.put("Wyoming", wyomingList);
    //</editor-fold>
    return map;
  }
  //</editor-fold>

  /**
   * Returns the opposite of what's given (if you give an abbreviation, it will return it's long name and vice
   * versa
   *
   * @param string
   * @return
   */
  public static String getState(String string) {
    return (string.length() > 2) ? longStatesMap.get(string) : shortStatesMap.get(string);
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
