package com.kentcdodds.javahelper.helpers;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Kent
 */
public class XMLHelper {

  /**
   * Uses the DocumentBuilderFactory to create a new doc and returns it
   *
   * @return Document object called doc
   */
  public static Document createDoc() {
    Document doc = null;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.newDocument();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
    return doc;
  }

  /**
   * This method will transform an XML doc to a String. Returns null if there's an error.
   *
   * @param doc
   * @return xmlString
   */
  public static String transformDoc(Document doc) {
    String xmlString = null;
    try {
      DOMSource domSource = new DOMSource(doc);
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.transform(domSource, result);
      xmlString = writer.toString();
    } catch (TransformerException ex) {
      ex.printStackTrace();
    }
    return xmlString;
  }

  /**
   * This method will Read the XML and act accordingly
   *
   * @param xmlString - the XML String
   * @return the list of elements within the XML
   */
  public static Document readXML(String xmlString) {
    Document doc = null;
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      InputSource xmlStream = new InputSource();
      xmlStream.setCharacterStream(new StringReader(xmlString));
      doc = dBuilder.parse(xmlStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return doc;
  }
}
