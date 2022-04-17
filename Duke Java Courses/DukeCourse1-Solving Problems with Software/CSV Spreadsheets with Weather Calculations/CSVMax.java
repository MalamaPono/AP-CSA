/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }else{
               largestSoFar = hottestOfTwo(currentRow, largestSoFar);
            }
            //Otherwise
            
        
    }
    return largestSoFar;
}
    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(parser);
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File x : dr.selectedFiles()){
            FileResource fr = new FileResource(x);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = hottestHourInFile(parser);
            largestSoFar = hottestOfTwo(currentRow, largestSoFar);
    }

    return largestSoFar;
}
    public CSVRecord hottestOfTwo(CSVRecord current, CSVRecord largestSoFar){
        if(largestSoFar == null){
            largestSoFar = current;
        }else{
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp > largestTemp){
                largestSoFar = current;
            }
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
}
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF")); //since spreadsheet is like an array with indeces of rows and columns, the parser goes through each and every row, and each row has their are specific columns that you can grab data from depending on what index/name of the column you call. 
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currentTemp != -9999){
            if(currentTemp < smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        }
        return smallestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = getColdestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
}
    public void testColdestHour(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.get("TemperatureF"));
    }
    
    public boolean isSwitched(CSVRecord currentRow, CSVRecord smallestSoFar){
        
        if(smallestSoFar == null){
            return true;
        }
        
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double smallTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
        
        if(currentTemp < smallTemp){
            return true;
        }else{
            return false;
        }
        
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        String smallestFile = null;
        for(File x : dr.selectedFiles()){
            String currentFile = x.getName();
            FileResource fr = new FileResource(x);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            
            if(isSwitched(currentRow, smallestSoFar) == true){
                smallestFile = currentFile;
            }
            smallestSoFar = getColdestOfTwo(currentRow, smallestSoFar);
            
        }
        return smallestFile;
            
}
    public void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        FileResource fr = new FileResource("data/" + "2015" + "/" + fileName); //switch year string in this line depending on what year you are searching the files for.
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = coldestHourInFile(parser);
        System.out.println("Coldest day was " + smallest.get("DateUTC"));
        System.out.println("The temperature that day was " + smallest.get("TemperatureF"));
        System.out.println("All the rest of the temepratures on this day (file) was: ");
        CSVParser parser2 = fr.getCSVParser();
        for(CSVRecord record : parser2){
            System.out.println(record.get("TemperatureF") + " " + record.get("DateUTC"));
        }
    }
    
    public void getAverageTempInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureInFile(parser));
        
    }
    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double sum = 0;
        for(CSVRecord current : parser){
            sum += Double.parseDouble(current.get("TemperatureF"));
            count++;
        }
        return sum/count;
    }
    public double averageTemperatureWithHighHumidity(CSVParser parser, int value){
        double sum = 0;
        int count = 0;
        for(CSVRecord current : parser){
            
                int humidity = Integer.parseInt(current.get("Humidity"));
                if(humidity >= value){
                    double temp = Double.parseDouble(current.get("TemperatureF"));
                    count++;
                    sum = sum + temp;
                }
            
        }
        return sum/count;
    }
    public void testAverageTemperatureWithHighHumidity(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureWithHighHumidity(parser, 80));
}
    public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }   
        else{
            if(!currentRow.get("Humidity").equals("N/A")){
            int currentTemp = Integer.parseInt(currentRow.get("Humidity"));
            int smallestTemp = Integer.parseInt(smallestSoFar.get("Humidity"));
            if(currentTemp < smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        }
        return smallestSoFar;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = getLowestHumidityOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println(record.get("Humidity") + " " + record.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File x : dr.selectedFiles()){
            FileResource fr = new FileResource(x);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            smallestSoFar = getLowestHumidityOfTwo(currentRow, smallestSoFar);
            
        }
        return smallestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println(lowest.get("Humidity") + " " + lowest.get("DateUTC"));
    }
}
