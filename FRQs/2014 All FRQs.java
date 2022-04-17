
//departure of first to arrival of the last. If there are one or more flights.
//0. if no flights

public int getDuration(){
    
    if(flights.size() == 0){
        return 0;
    }
    
    Flight first = flights.get(0);
    Flight last = flights.get(flights.size()-1);
    
    Time departure = first.getDepartureTime();
    Time arrival = last.getArrivalTime();
    
    return departure.minutesUntil(arrival);
    
}

public int getShortestLayover(){
    
    if(flights.size() < 2){
        return -1;
    }
    
    Flight flight1 = flights.get(0);
    Flight flight2 = flights.get(1);
    int shortestLayover = flight1.getArrivalTime.minutesUntil(flight2.getDepartureTime());
    for(int i = 1; i < flights.size()-1; i++){
        Flight flight1 = flights.get(i);
        Flight flight2 = flights.get(i+1);
        int layover = flight1.getArrivalTime.minutesUntil(flight2.getDepartureTime());
        if(layover < shortestLayover){
            shortestLayover = layover;
        }
    }
    return shortestLayover;
}


//2
public static String apcsReplaceAll(String str, String oldStr, String newStr){
    ArrayList<Integer> indices = new ArrayList<Integer>();
    int x = str.indexOf(oldStr,0);
    while(x != -1){
        indices.add(x);
        x = str.indexOf(oldStr, x + oldStr.length());
    }
    
    if(indices.size()!=0){
    String firstPart = str.substring(0, indices.get(0));
    
    ArrayList<String> total = new ArrayList<String>();
    
    for(int i = 0; i < indices.size()-1; i++){
        String part = str.substring(indices.get(i) + oldStr.length(),indices.get(i+1));
        total.add(part);
    }
    
    String lastPart = str.substring(indices.get(indices.size()-1) + oldStr.length());
    
    String ret = "";
    ret = ret + firstPart;
    for(int j = 0; j < total.size(); j++){
        ret = ret + newStr + total.get(j);
    }
    
    ret = ret + newStr + lastPart;
    
    return ret;
    
    }
    
    return str;
    
}

/*
Other and better version for apcsReplaceAll that I now understand. 

public static String apcsReplaceAll(String str, String oldStr, String newStr){
        String firstPart = "";
        String lastPart = str;
        int pos = str.indexOf(oldStr);
        while(pos!=-1){
            firstPart += lastPart.substring(0,pos);
            firstPart += newStr;
            lastPart = lastPart.substring(pos + oldStr.length());
            pos = lastPart.indexOf(oldStr);
        }
        
        
        firstPart = firstPart + lastPart;
        
        return firstPart;
    }

*/

public static String replaceNameNickname(String str, List<Person> people){
    for(Person x : people){
        str = apcsReplaceAll(str, x.getFirstName(), x.getNickname());
    }
    return str;
}

//3
public class Cat extends Pet{
    
    public Cat(String petName){
        super(petName);
    }
    
    public String speak(){
        return "meow";
    }
    
}

public class LoudDog extends Dog{
    
    public LoudDog(String petName){
        super(petName);
    }
    
    public String speak(){
        return super.speak() + super.speak();
    }

}



public void allSpeak(){
    for(Pet x : petList){
        System.out.println(x.getName() + x.speak());
    }
}

//4
public static void shiftArray(int[] arr, int num){
    for(int i = arr.length-1; i > 0; i--){
        arr[i] = arr[i-1];
    }
    arr[0] = num;
}


public void shiftMatrix(int num){
    
    int temp = num;
    ArrayList<Integer> nums = new ArrayList<Integer>();
    int finalCol = matrix[0].length -1;
    for(int j = 0; j < matrix.length; j++){
        nums.add(matrix[j][finalCol]);
    }
    
    for(int i = 0; i < matrix.length; i++){
        ArrayUtil.shiftArray(matrix[i],temp);
        temp = nums.get(i);
    }
    
    
}

//************IMPORTNAT*****************
//static methods not in the same class, call them by using ClassName.method()


public void rotateMatrix(){
    shiftMatrix(matrix[matrix.length-1][matrix[0].length-1]);
}

