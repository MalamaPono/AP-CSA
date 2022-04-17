public LogMessage(String message){
    int index = message.indexOf(":");
    machineId = message.substring(0, index);
    description = message.substring(index+1);
}

public boolean containsWord(String keyword){
    
    if((descrption.substring(0, keyword.length()).equals("disk") || description.indexOf(" disk") != -1) && (descrption.substring(description.length() - keyword.length()).equals("disk") || desciption.indexOf("disk ") != -1)) {
        return true;
    }else{
        return false
    }
}

/*
public class MyClass{
    public static void main(String[] args){
        
        String description = "error on /dev/disk";
        String keyword = "disk";
        
        if((description.substring(0, keyword.length()).equals("disk") || description.indexOf(" disk") != -1) && (description.substring(description.length() - keyword.length()).equals("disk") || description.indexOf("disk ") != -1)) {
        System.out.println("true");
    }else{
        System.out.println("false");
    }
    
    }
}

this code shows how part b works
*/

public List<LogMessage> removeMessages(String keyword){
    
    List<LogMessage> newList = new ArrayList<LogMessage>();
    for(int i = 0; i < messageList.size(); i++){
        if(messageList.get(i).containsWord(keyword) == true){
            newList.add(messageList.get(i);
            messageList.remove(i);
            i--;
        }
    }
    return newList;
}

    //second way
    
    List<LogMessage> newList = new ArrayList<LogMessage>();
    for(SystemLog l : messageList){
        if(l.containsWord(keyword) == true){
            messageList.remove(l);
            newList.add(l);
        }
    }
    return newList;
}
