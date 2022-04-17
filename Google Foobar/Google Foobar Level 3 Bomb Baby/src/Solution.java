/*
This solution applies the principle of BFS and I just need to add a simple check to break the while loop when a solution 
may be impossible while searching through the queue. One check is once reaching a bomb in the queue that has a greater
mach and facula number than the needed solution, go ahead and do a search through the rest of the queue for a bomb
with the proper solution for the mach and facula number. If that bomb is found, return the generations of that bomb, 
but if not return "impossible".

However this solution doesn't work when facula and mach numbers get huge like the limit of 10^50 listed in the problem
description. This is becuase our BFS algorithm will take too long and google foobar will not accept that solution and 
say that your solution fails. Thus we must find a new and different creative solution to solve the problem. 
 */

import java.util.LinkedList;
public class Solution {
    public static String solution(String x, String y) {
        
        LinkedList<Bomb> queue = new LinkedList<Bomb>();
        int mach = Integer.parseInt(x);
        int facula = Integer.parseInt(y);
        
        Bomb bomb = new Bomb(1, 1, 0);
        queue.add(bomb);
        
        while(true){
            Bomb check = queue.pollFirst();
            
            if(check.machNum == mach && check.facNum == facula){
                 return Integer.toString(check.generations);
            }else{
                Bomb cycleOne = new Bomb(check.machNum+check.facNum,check.facNum,check.generations+1);
                Bomb cycleTwo = new Bomb(check.machNum,check.facNum+check.machNum,check.generations+1);
                queue.add(cycleOne);
                queue.add(cycleTwo);
            }
        }
        
    }
    
}

class Bomb{
    int machNum;
    int facNum;
    int generations;
    
    public Bomb(int machNum, int facNum, int generations){
        this.machNum = machNum;
        this.facNum = facNum;
        this.generations = generations;
    }
    
}