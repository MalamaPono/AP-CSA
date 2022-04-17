//part a
public static int getCheck(int num){
    int sum = 0;
    for(int i = 1; i <= getNumberOfDigits(num); i++){
        int mutiple = 7;
        sum = sum + multiple * getDigit(num, i);
        mutiple--;
    }
    int chceckDigit = sum%10;
    return checkDigit;
}

//part b
public static boolean isValid(int numWithCheckDigit){
    
    int check = numWithCheckDigit%10;
    int num = numWithCheckDigit/10;
    
    if(getCheck(num) == check){
        return true
    }else{
        return false;
    }
    
    
}


PART C
//in another method/class
if(isValid == false){
    counter++;
}

/*
//In order to find out how many times the isValid method returned false, you would 
first need to define a new integer counter variable in the CheckDigit class.
This counter variable can be incremented either in the else statement in the isValid 
method before it returns false, or whenever the isValid method returns false within 
another class or method. If this field will be accessed within another class,
it should be a public field rather than a private field.


*/
