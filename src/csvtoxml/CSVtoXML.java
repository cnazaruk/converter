/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvtoxml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/**
 *
 * @author chloenazaruk
 */
public class CSVtoXML {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File test = new File("src/csvtoxml/Book2.csv");
        converter(test);
    }
    
    public static void converter(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/csvtoxml/test.xml"));
            System.out.println("File has been read into the system.");
            String header = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>";
            String firstTag = "first_tag";
            String line;
            String[] tags;
            String[] values;
            
            // Write header statement to CSV file
            writer.write(header);
            writer.newLine();
            writer.newLine();
            writer.write(firstTag);
            writer.newLine();
            writer.newLine();
            System.out.println("The header is: " + header);
            
            // Take out first line and assign to tags
            String firstLine = bufferedReader.readLine();
            tags = firstLine.split(",");
  
            // Clean data to be used as tags and print to screen 
            for(int i = 0; i < tags.length; i++) {
                tags[i] = tags[i].trim().replaceAll("\\s+","_");
                System.out.println(tags[i]);
            }

            
            // Read each line and print to XML file
            while ((line = bufferedReader.readLine()) != null) {
                values = line.trim().split(",");
                int count = values.length;
                for(int i = 0; i < count; i++) {
                    writer.write("   <" + tags[i].trim() + ">" + values[i].trim() + "</" + tags[i].trim() + ">");
                    writer.newLine();
                        }
                
            }
            
            // Mark end of file and close writer
            writer.newLine();
            writer.write(firstTag);
            writer.newLine();
            writer.newLine();
            writer.write("End of file.");
            writer.close();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
    
}
   
