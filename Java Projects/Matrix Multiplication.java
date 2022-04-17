
import java.util.*;

public class MyClass {
    public static void main(String args[]) {
        
        int[][] matrixOne = {
            {4,3,2,8},
            {-3,-4,-9,0},
            {2,3,10,3}
            
        };
        
        int[][] matrixTwo = {
            
            {9,8,19},
            {-9,-10,-11},
            {4,6,9},
            {2,1,4}
            
        };
        
        int[][] multiply = matrixMultiplication(matrixOne, matrixTwo);
        System.out.println(Arrays.deepToString(multiply));
        
    }
    
    public static int[][] matrixMultiplication(int[][] one, int[][] two){
        
        int[][] answer = new int[one.length][two[0].length];
        
        for(int row = 0; row < one.length; row++){
            for(int col = 0; col < two[0].length; col++){
                int[] rowArr = one[row];
                int[] colArr = getCol(two, col);
                int dotProduct = dotProduct(rowArr,colArr);
                answer[row][col] = dotProduct;
            }
        }
        
        return answer;
        
    }
    
    public static int[] getCol(int[][] arr, int col){
        int[] column = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            column[i] = arr[i][col];
        }
        return column;
    }
    
    public static int dotProduct(int[] one, int[] two){
        int sum = 0;
        for(int i = 0; i < one.length; i++){
            sum+= one[i] * two[i];
        }
        return sum;
    }
    
}