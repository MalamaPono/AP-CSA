// (11,29)
public class Solution {
	public static String solution(String x, String y) {
		
		int steps = 0;
		int mach = Integer.parseInt(x);
		int facula = Integer.parseInt(y);
		
		while(mach != 1 && facula != 1) {
			if(mach > facula) {
				steps += mach/facula;
				mach = mach % facula;
			}else {
				steps += facula/mach;
				facula = facula % mach;
			}
			
			if(mach <= 0 || facula <= 0) {
				return "impossible";
			}
			
		}
		
		
		if(mach == 1 && facula == 1) {
			return Integer.toString(steps);
		}else if(mach == 1 && facula != 1) {
			steps += facula - mach;
			return Integer.toString(steps);
		}else {
			steps += mach - facula;
			return Integer.toString(steps);
		}
		
	}
}
