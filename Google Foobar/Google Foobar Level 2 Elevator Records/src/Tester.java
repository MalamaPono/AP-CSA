import java.util.*;

public class Tester {
	public static void main(String[] args) {
		
	
		String[] records = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
		
		records = Solution.solution(records);
		System.out.println(Arrays.toString(records));
		
		
	}
}
