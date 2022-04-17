import java.util.Arrays;

public class Tester {
	public static void main(String[] args) {
		
		int answer = Solution.solution(17,4);
		System.out.println(answer);
		
		System.out.println(Solution.getWorkers(5));
		System.out.println(Arrays.toString(Solution.getIds(17,4)));
	}
}