private int getIndexForFit(NumberTile tile){
    if(board.size() == 0){
        return 0; 
    }
    int right = tile.getRight();
    int left = tile.getLeft();
    for(int i = 0; i < board.size(); i++){
        if(i == 0){
            if(board.get(0).getLeft() == right){
                return 0;
            }
        }
        if(i == board.size() - 1){
            if(board.get(board.size()-1).getRight() == left){
                return board.size();
            }
        }
        while(i != 0 || i != board.size()-1){
        if(board.get(i).getRight() == left && board.get(i+1).getLeft() == right){
            return i+1;
        }
    }
    }
    return -1;
}

public boolean insertTile(NumberTile tile){
    for(int i = 0; i < 4; i++){
        if(getIndexForFit(tile) != -1){
            int x = getIndexForFit(tile);
            board.add(x, tile);
            return true;
        }
        tile.rotate();
    }
    return false;
}
