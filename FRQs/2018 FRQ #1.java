public boolean stimulate(){
    int position = 0;
    for(int i = 0; i < maxHops; i++){
        position += hopDistance();
        if(position < 0){
            return false;
        }
        if(position >= goalDistance){
            return true;
        }
    }
    return false;
}

public double runSimulations(int num){
    int counter = 0;
    for(int i = 0; i < num; i++){
        if(stimulate() == true){
            counter++;
        }
    } 
    
    return (double)count/num;
}
