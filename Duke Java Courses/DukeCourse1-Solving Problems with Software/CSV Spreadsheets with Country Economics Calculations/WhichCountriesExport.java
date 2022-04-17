/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    public void tester(){
        System.out.println("new");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "cocoa");
        CSVParser parser3 = fr.getCSVParser();
        bigExporters(parser3, "$999,999,999,999");
        /*
        listExportersTwoProducts(parser, "cotton", "flowers");
        CSVParser parser2 = fr.getCSVParser();
        System.out.println(numberOfExporters(parser2, "gold") + " this is per country");
        CSVParser parser4 = fr.getCSVParser();
        System.out.println("country info");
        countryInfo(parser4, "Nauru");
        */
        
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                System.out.println(record.get("Country"));
        }
    }
}
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String money = record.get("Value (dollars)");
            String country = record.get("Country");
            if(amount.length() < money.length() - 1){
                System.out.println(country + " " + money);
              
            }
    }
}
    public void countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                System.out.println(record.get("Exports") + record.get("Value (dollars)"));
    }
}
}
}
