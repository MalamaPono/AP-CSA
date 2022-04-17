public int getValueAt(int row, int col){
    for(int i = 0; i < entries.size(); i++){
        if(entries.get(i).getRow() == row && entries.get(i).getCol() == col){
            return entries.get(i).getValue();
        }
    }
    return 0;
}

public void removeColumn(int col){
    for(int i = 0; i < entries.size(); i++){
        if(entries.get(i).getCol() == col){
            entries.remove(i);
            i--;
        }else if(entries.get(i).getCol() > col){
            SpareArrayEntry temp = entries.remove(i);
            entries.add(new SpareArrayEntry(temp.getRow(), temp.getCol() - 1, temp.getValue()));
        //if order of entries mattered, we would have to set the specific index to a new object with certain parameters. But since order doesn't matter in this question, we can just add this new entry anywhere.
        }
    }
    numCols--;
}
