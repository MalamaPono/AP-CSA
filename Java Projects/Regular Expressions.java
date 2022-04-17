import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MyClass {
    public static void main(String args[]) {
        
        String s = "one (1), two (2), three (3)";
        ArrayList<String> my = getTokens("[a-z]+|[()0-9]+",s);
        System.out.println(my);
        
    }
    
    
    public static ArrayList<String> getTokens(String pattern, String s)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(s);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
    
}
