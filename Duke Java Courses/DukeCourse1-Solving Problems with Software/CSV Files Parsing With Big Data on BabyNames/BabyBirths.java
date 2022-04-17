/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
    FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) { //this means there is no header row for the data columns, it just gets straight to the data
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    public void totalBirths(FileResource fr){
        CSVParser parser = fr.getCSVParser(false);
        int sumFemale = 0;
        int sumMale = 0;
        int totalBirths = 0;
        for(CSVRecord rec : parser){
            totalBirths += Integer.parseInt(rec.get(2));
            if(rec.get(1).equals("F")){
                sumFemale = sumFemale + Integer.parseInt(rec.get(2));
            }else{
                sumMale = sumMale + Integer.parseInt(rec.get(2));
            }
        }
        System.out.println("Male = " + sumMale);
        System.out.println("Female = " + sumFemale);
        System.out.println("Total = " + totalBirths);
        
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource("data/example-small.csv");
        totalBirths(fr);
    }
    public int getRank(int year, String name, String gender){
        String fun = Integer.toString(year);
        FileResource fr = new FileResource("data/yob" + fun + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rankMale = 1;
        int rankFemale = 1;
        for(CSVRecord rec : parser){
            
        if(gender.equals("M") && rec.get(1).equals("M")){
            if(rec.get(0).equals(name)){
                return rankMale;
            }else{
            rankMale++;
        }
        }
            
        if(gender.equals("F")){
            if(rec.get(0).equals(name) && rec.get(1).equals("F")){
                return rankFemale;
            }else{
            rankFemale++;
        }
        }
       
        }
        return -1;
    }
    public void testRank(){
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender){
        String fun = Integer.toString(year);
        FileResource fr = new FileResource("data/yob" + fun +".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rankCompare = 1;
        for(CSVRecord rec : parser){
            if(getRank(year, rec.get(0), gender) == rank){
                return rec.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void testName(){
        System.out.println(getName(1982, 450, "M"));
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rankForName = getRank(year, name, gender);
        String newName = getName(newYear, rankForName, gender);
        return newName;
    }
    
    public void testWhatIsNameInYear(){
        int year = 1972;
        int newYear = 2014;
        String name = "Susan";
        String gender = "F";
        System.out.println(name + " born in " + year + " would be " + whatIsNameInYear("Isabella", year, newYear, gender) + " in " + newYear);
        
    }
    
    public int getRankForCertainFile(CSVParser parser, String name, String gender){
        int rankMale = 1;
        int rankFemale = 1;
       
        for(CSVRecord rec : parser){
            if(gender.equals("M") && rec.get(1).equals("M")){
            if(rec.get(0).equals(name)){
                return rankMale;
            }else{
            rankMale++;
        }
        }
            
        if(gender.equals("F")){
            if(rec.get(0).equals(name) && rec.get(1).equals("F")){
                return rankFemale;
            }else{
            rankFemale++;
        }
        }
        }
        return -1;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rankSum = 0;
        int fileCount = 0;
        for(File x : dr.selectedFiles()){
            FileResource fr = new FileResource(x);
            CSVParser parser = fr.getCSVParser(false);
            int rank = getRankForCertainFile(parser, name, gender);
            if(rank != -1){
                rankSum += rank;
            }
            fileCount++;
        }
        if(rankSum == 0){
            return -1.0;
        }else{
        return (double)rankSum/ fileCount;
    }
    }
    
    public void testAverageRank(){
        System.out.println(getAverageRank("Mason", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fun = Integer.toString(year);
        FileResource fr = new FileResource("yob" + fun + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        CSVParser parser2 = fr.getCSVParser(false);
        int totalBirths = 0;
        if(getRankForCertainFile(parser, name, gender) == 1){
            return 0;
        }
        for(CSVRecord rec : parser2){
            if(gender.equals("F") && rec.get(1).equals("F")){
                if(!rec.get(0).equals(name)){
                    totalBirths += Integer.parseInt(rec.get(2));
                }else{
                    return totalBirths;
                }
             
            }
            
            if(gender.equals("M") && rec.get(1).equals("M")){
                if(!rec.get(0).equals(name)){
                    totalBirths += Integer.parseInt(rec.get(2));
                }else{
                    return totalBirths;
                }
             
            }
        }
        
        return -1;
    }
    public void testGetTotalBirthsRankedHigher(){
        int year = 2014;
        String name = "Emma";
        String gender = "F";
        int birthsNumberBefore = getTotalBirthsRankedHigher(year, name, gender);
        if(birthsNumberBefore == -1){
            System.out.println(name + " is not a valid name in this file");
        }else if(birthsNumberBefore == 0){
            System.out.println(name + " is the highest ranking name for that specific gender. There are no births before her");
        }else{
            System.out.println("There are " + birthsNumberBefore + " births before " + name + " in this file");
        }
    }
    public int numGenderInFile(int year, String gender){
        String fun = Integer.toString(year);
        FileResource fr = new FileResource("data/yob" + fun + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int countFemale = 0;
        int countMale = 0;
        for(CSVRecord rec : parser){
            if(gender.equals("F") && rec.get(1).equals("F")){
                countFemale++;
            }
            if(gender.equals("F") && rec.get(1).equals("M")){
                return countFemale;
            }
            
            if(gender.equals("M") && rec.get(1).equals("M")){
                countMale++;
            }
            
        }
        if(gender.equals("M")){
            return countMale;
        }
        return -1;
    }
    
    public void testNumberOfGenderInFile(){
        int year = 1905;
        String gender = "M";
        System.out.println(numGenderInFile(year, gender));
    }
    public int highestRankingYearForName(int startYear, int EndYear, String name, String gender){
        int highestRank = Integer.MAX_VALUE;
        for(int i = startYear; i <= EndYear; i++){
            FileResource fr = new FileResource("data/yob" + i + ".csv");
            CSVParser parser = fr.getCSVParser(false);
            
            int currentRank = getRankForCertainFile(parser, name, gender);
            if(currentRank != -1 && currentRank < highestRank){
                 highestRank = currentRank;
            }
            
        }
        return highestRank;
    }
    
    public void testHighestRankingYearForName(){
        int startYear = 1880;
        int endYear = 2014;
        String name = "Genevieve";
        String gender = "F";
        int highestRank = highestRankingYearForName(startYear, endYear, name, gender);
        if(highestRank == Integer.MAX_VALUE){
            System.out.println(name + " is not found in this file");
        }else{
            System.out.println("The highest rank for " + name + "in these files is " + highestRank);
        }
    }
     public int getTotalNamesOfSameGenderRankedHigher(int year, String name, String gender){
        String fun = Integer.toString(year);
        FileResource fr = new FileResource("yob" + fun + "-small.csv");
        CSVParser parser = fr.getCSVParser(false);
        CSVParser parser2 = fr.getCSVParser(false);
        int names = 0;
        if(getRankForCertainFile(parser, name, gender) == 1){
            return 0;
        }
        for(CSVRecord rec : parser2){
            if(gender.equals("F") && rec.get(1).equals("F")){
                if(!rec.get(0).equals(name)){
                    names++;
                }else{
                    return names;
                }
             
            }
            
            if(gender.equals("M") && rec.get(1).equals("M")){
                if(!rec.get(0).equals(name)){
                    names++;
                }else{
                    return names;
                }
             
            }
        }
        
        return -1;
    }
    public void testGetTotalNamesOfSameGenderRankedHigher(){
        int year = 2014;
        String name = "Jacob";
        String gender = "M";
        int numPeopleBefore = getTotalNamesOfSameGenderRankedHigher(year, name, gender);
        
        if(numPeopleBefore == 0){
            System.out.println(name + " is the top ranking name, nobody is before her");
        }else if(numPeopleBefore == -1){
            System.out.println(name + " is not even found in this file");
        }else{
            System.out.println(numPeopleBefore + " are ranked higher then " + name);
        }
    }
}
       
        //proud of u kocha u did this very well


