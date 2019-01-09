/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvtoxml;

import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Converter {

    public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        try {
            String file = "src/csvtoxml/Book2.csv";
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            String[] tags;
            String[] values;
            String line;
            String rootName = "root_name";

            // Take out first line and assign to tags
            String firstLine = bufferedReader.readLine();
            tags = firstLine.split(",");

            // Clean data to be used as tags and print to screen 
            for (int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].trim().replaceAll("\\s+", "_");
            }

            // root element
            Element rootElement = doc.createElement(rootName);
            doc.appendChild(rootElement);

            while ((line = bufferedReader.readLine()) != null) {
                values = line.trim().split(",");
                int count = values.length;
                for (int i = 0; i < count; i++) {
                    Element element = doc.createElement(tags[i]);
                    rootElement.appendChild(element);
                    element.appendChild(doc.createTextNode(values[i]));
                    rootElement.appendChild(element);
                }

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/CSVtoXML/output.xml"));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
