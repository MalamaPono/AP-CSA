public class MyClass {
    public static void main(String args[]) {
        
        
        String code = "Arire tbaan tvir lbh hc, arire tbaan yrg lbh qbja, arire tbaan eha nebhaq naq qrfreg lbh.";
        String answer = Caesar(code);
        System.out.println(answer);
        
        
        
    }
    
    public static String Caesar(String x){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String Ualphabet = alphabet.toUpperCase();
        //0-25 length 26
        int key = 13;
        String answer = "";
        for(int i = 0; i < x.length(); i++){
            String letter = x.substring(i,i+1);
            if(Ualphabet.indexOf(letter) != -1){
                int index = Ualphabet.indexOf(letter);
                index = (index + 13)%26;
                answer = answer + Ualphabet.substring(index,index+1);
            }else if(alphabet.indexOf(letter) != -1){
                int index = alphabet.indexOf(letter);
                index = (index + 13)%26;
                answer = answer + alphabet.substring(index,index+1);
            }else{
                answer = answer + letter;
            }
            
        }
        return answer;
    }
    
}
