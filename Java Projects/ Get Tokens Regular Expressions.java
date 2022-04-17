import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyClass {
    public static void main(String args[]) {

        String text = "Small rug fits the small dog with the Room-tiering rug who falls through the Small Room-tiering ru";
        ArrayList<String> tokens = getTokens(text,"(Small|Room-tiering) rug");
        System.out.println(tokens);
        
    }
    
    public static ArrayList<String> getTokens(String text, String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
    
}

