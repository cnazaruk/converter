/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvtoxml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    
    public static String converter(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/csvtoxml/test.xml"));
            System.out.println("File has been read into the system.");
            String header = "<?xml version = \"1.0\"?>";
            String line;
            String[] tags;
            String[] values;
            
            // Write header statement to CSV file
            writer.write(header);
            writer.newLine();
            System.out.println(header);
            
            // Take out first line and assign to tags
            String firstLine = bufferedReader.readLine();
            tags = firstLine.trim().split(",");
            
            // Check tags
            for(String tag : tags) {
            System.out.println(tag);
            }
            
            // Read each line and process
            while ((line = bufferedReader.readLine()) != null) {
                values = line.trim().split(",");
                int count = values.length;
                for(int i = 0; i < count; i++) {
                    writer.write("<" + tags[i] + ">" + values[i] + "</" + tags[i] + ">");
                    writer.newLine();
                        }
            }
            writer.close();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    }
   
