
/**
 * Write a description of HelloWorldJavaNIO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.nio.*;
import java.io.*;
import java.nio.file.*;
import java.net.URL;


public class HelloWorldJavaNIO {

    public void readAndPrint() throws IOException{
        Path p = Paths.get("hello_unicode.txt");
        BufferedReader reader = Files.newBufferedReader(p);
        while(true){
            String line = reader.readLine();
            if(line == null){
                break;
            }
            System.out.println(line);
        }
        
    }
    
    public void readAndPrintURL() throws IOException{
        URL source = new URL("http://dukelearntoprogram.com/java/hello_unicode.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(source.openStream()));
        while(true){
            String line = reader.readLine();
            if(line == null){
                break;
            }
            System.out.println(line);
        }
    }
    
    public static void main(String[] args) throws IOException{
        HelloWorldJavaNIO test = new HelloWorldJavaNIO();
        //test.readAndPrint();
        test.readAndPrintURL();
    }
        
    
}


