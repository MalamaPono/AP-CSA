
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

//*****take note of this
//any clas you create, you can create a new .equals method and specify how you what to characterize your certain objects to be considered equal
//The default == method is used to check if the two objects are the same thing and refering to the same memory in data. However, in your specific classes you might what to change this, so for in example in the String class, the .equals method checks if a String has the exact same contents.

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("short-test_log");
        analyze.printAll();
    }
    
    public void testUniqueIps(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        int x = analyze.countUniqueIps();
        System.out.println("there are " + x + " unique ips");
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog1_log");
        System.out.println("*****");
        analyze.printAllHigherThanNum(400);
    }
    
    public void testUniqueIpsInOneDay(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        ArrayList<String> ipsOnCertainDate = analyze.uniqueIpVisitsOneDay("Sep 27");
        System.out.println(ipsOnCertainDate.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        int numberOfUniqueIps = analyze.countUniqueIpsInRange(200,299);
        System.out.println(numberOfUniqueIps);
        
    }
    
    public void countIPTester(){
        LogAnalyzer analyze = new LogAnalyzer();
        System.out.println("******");
        analyze.readFile("short-test_log");
        HashMap<String,Integer> count = analyze.countVisitsPerIP();
        System.out.println(count);
        /*
        for(String s : count.keySet()){
            System.out.println("The ip " + s + " had " + count.get(s) + " visits");
        }
        */
    }
    
    public void testMaxVisitsByCertainIp(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog1_log");
        HashMap<String,Integer> count = analyze.countVisitsPerIP();
        int max = analyze.maximumVisitsBySpecificIP(count);
        System.out.println(max);
    }
    
    public void testIpsWithMaxVisits(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        HashMap<String,Integer> count = analyze.countVisitsPerIP();
        ArrayList<String> ips = analyze.ipsWithMostVisits(count);
        for(String x : ips){
            System.out.println(x);
        }
    }
    
    public void testIpsForCertainDay(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> map = analyze.ipsForDays();
        System.out.println(map);
    }
    
    public void testDayWithMostVisits(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = analyze.ipsForDays();
        String day = analyze.dayWithMostVisits(map);
        System.out.println(day);
    }
    
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = analyze.ipsForDays();
        ArrayList<String> ips = analyze.iPsWithMostVisitsOnDay(map, "Sep 29");
        for(String x : ips){
            System.out.println(x);
        }
    }
}
