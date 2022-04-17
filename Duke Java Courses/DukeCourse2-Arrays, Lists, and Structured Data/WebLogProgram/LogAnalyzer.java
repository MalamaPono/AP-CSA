
/**
 * Write a description of LogAnalyzerClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

//WebLogParser.parseEntry();
public class LogAnalyzer{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            WebLogParser parse = new WebLogParser();
            LogEntry parsed = parse.parseEntry(line);
            records.add(parsed);
           
        }
    }
    
    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le); //this already uses toString method in LogEntry class since it is printing a logEntry
        }
    }
    
    public int countUniqueIps(){
        HashMap<String,Integer> unique = new HashMap<String,Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!unique.containsKey(ip)){
                unique.put(ip,1);
            }else{
                unique.put(ip,unique.get(ip)+1);
            }
        }
        return unique.size();
    }
    
    public void printAllHigherThanNum(int num){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(LogEntry le : records){
            if(le.getStatusCode() > num){
               if(temp.indexOf(le.getStatusCode()) == -1){
                System.out.println(le);
                temp.add(le.getStatusCode());
            }
            }
        }
    }
    
    public ArrayList<String> uniqueIpVisitsOneDay(String day){
        ArrayList<String> unique = new ArrayList<String>();
        for(LogEntry le : records){
            String str = le.getAccessTime().toString();
            str = str.substring(4,10);
            if(str.equals(day)){
                if(unique.indexOf(le.getIpAddress()) == -1){
                    unique.add(le.getIpAddress());
                }
            }
        }
        return unique;
   }
   
   public int countUniqueIpsInRange(int low, int high){
      ArrayList<String> uniqueIpsInRange = new ArrayList<String>();
      for(LogEntry le : records){
          if(le.getStatusCode() >= low && le.getStatusCode() <=high){
              if(uniqueIpsInRange.indexOf(le.getIpAddress()) == -1){
                  uniqueIpsInRange.add(le.getIpAddress());
                }
          }
      }
      return uniqueIpsInRange.size();
   }
   
   public HashMap<String,Integer> countVisitsPerIP(){
       HashMap<String,Integer> count = new HashMap<String,Integer>();
       for(LogEntry le : records){
           String ip = le.getIpAddress();
           if(!count.containsKey(ip)){
               count.put(ip,1);
           }else{
               count.put(ip,count.get(ip)+1);
           }
       }
       return count;
   }
   
   public int maximumVisitsBySpecificIP(HashMap<String,Integer> map){
       int max = 0;
       for(String a : map.keySet()){
           if(map.get(a) > max){
               max = map.get(a);
            }
       }
       return max;
   }
   
   public ArrayList<String> ipsWithMostVisits(HashMap<String,Integer> map){
       ArrayList<String> ipsWithMaxVisits = new ArrayList<String>();
       int max = maximumVisitsBySpecificIP(map);
       for(String s : map.keySet()){
           if(map.get(s) == max){
               ipsWithMaxVisits.add(s);
            }
        }
        return ipsWithMaxVisits;
   }
   
   public HashMap<String,ArrayList<String>> ipsForDays(){
       HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
       for(LogEntry le : records){
           String date = le.getAccessTime().toString();
           date = date.substring(4,10);
           String ip = le.getIpAddress();
           if(!map.containsKey(date)){
               ArrayList<String> ips = new ArrayList<String>();
               ips.add(ip);
               map.put(date,ips);
            }else{
                map.get(date).add(ip);
                    //right here if you didn't want a certain day to map to duplicate ips, then you can jsut add code here to make sure the ip doesn't already exist in the arrayList that is mapped from a specific date.
                map.put(date,map.get(date));
            }
        }
        return map;
    }
    
   public String dayWithMostVisits(HashMap<String,ArrayList<String>> map){
       int max = 0;
       String date = "";
       for(String day : map.keySet()){
           if(map.get(day).size() > max){
               max = map.get(day).size();
               date = day;
            }
        }
        return date; 
    }
    
   public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day){
       HashMap<String,Integer> count = new HashMap<String,Integer>();
       //we don't need to break out of the loop or anything because there is only one matching day in the HashMap anyways so data will not be contaminated as it won't even pass this first if statement
       for(String s : map.keySet()){
           if(s.equals(day)){
               for(String ip : map.get(day)){
                   if(!count.containsKey(ip)){
                       count.put(ip,1);
                    }else{
                        count.put(ip,count.get(ip)+1);
                    }
                  
                }
            }
        }
       return ipsWithMostVisits(count);
   }
}
