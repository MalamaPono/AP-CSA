public Digits(int num){
    digitList = new ArrayList<Integer>();
    if(num == 0){
        digitList.add(0);
    }
    while(num!=0){
        digit = num%10;
        digiList.add(0,digit );
        num = num/10; 
    }
    
}
    public boolean isStrictlyInceasing(){

        for(int i = 0; i < digitList.size() - 1; i++){
            if(digitList.get(i) >= digitList.get(i+1)){
                return false;
            }
        }
        return true; 
    }
