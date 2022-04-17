//for each way
public int countElectronicsByMaker(String maker){
    int counter = 0;
    for(Gizmo g : purchases){
        if(g.getMaker().equals(maker) && g.isElectronic() == true){
            counter++;
        }
    }
    return counter;
}

//for loop way
public int countElectronicsByMaker(String maker){
    int counter = 0;
    for(int i = 0; i < purchases.size(); i++){
        //can also initialize a gizmo object for index i to make code easier - Gizmo g = purchases.get(i);
        if(purchases.get(i).getMaker().equals(maker) && purchases.get(i).isElectronic() == true){
            counter++;
        }
    }
    return counter;
}


//comparing each index
public boolean hasAdjacentEqualPair(){
    if(purchases.size()<2){
        return false;
    }
    
    for(int i = 0; i < purchases.size()-1; i++){
        Gizmo first = purchases.get(i);
        Gizmo second = purchases.get(i+1);
        if(first.equals(second) == true){
            return true;
        }
    }
    return false;
}
