import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Files {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		System.out.println("I will return the 10 most frequent words in a text file");		
		System.out.println("Enter a file name: ");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine();
		
		File file = new File(filename);
//		File file = new File("/Users/malamapono/desktop/text.txt");
		Scanner scan = new Scanner(file);
		
		HashMap<String,Integer> wordFrequencies = new HashMap<String,Integer>();
		ArrayList<Integer> frequencies = new ArrayList<Integer>();
		
		
		//building the entire hash map
		while(scan.hasNextLine() == true) {
			String line = scan.nextLine();
			line = line.replaceAll("\\s"," "); //replacing all extralines and spaces of whitespace with a single space 
			String[] words = line.split(" +"); //instead which will be recognized by the split method to ensure we get every single word properly.
			//System.out.println(Arrays.toString(words));
			for(String word: words) {
				if(word.equals("") == false) {
					
					if(wordFrequencies.containsKey(word) == false) {
						wordFrequencies.put(word,1);
					}else {
						wordFrequencies.put(word, wordFrequencies.get(word)+1);
					}
					
				}
					
			}
			
		}
		
		for(String key : wordFrequencies.keySet()) {
			if(frequencies.contains(wordFrequencies.get(key)) == false) {
				frequencies.add(wordFrequencies.get(key));
			}
		}
		
		
		//quickSort the frequencies from greatest to least as we want the top 10 frequent words to be in our HashMap
		//and in descending order
		quickSort(frequencies,0,frequencies.size()-1);

//		System.out.println(frequencies);
//		System.out.println(wordFrequencies);
		
		ArrayList<String> tenMostCommon = tenMostCommon(wordFrequencies,frequencies);
		for(String k : tenMostCommon) {
			System.out.println(k + " appeared " + wordFrequencies.get(k) + " times");
		}
		
		//making a list containing only the top 10 words in the file from the data in the entire HashMap
		//Now just print the most frequent words in order allong with their frequencies with the help of the HashMap
		//The frequencies list is simply to help us build the top 10 String list of most frequent words
		
	}
	
	private static ArrayList<String> tenMostCommon(HashMap<String,Integer> wordFrequencies, ArrayList<Integer> frequencies){
		
		ArrayList<String> tenMostCommon = new ArrayList<String>();
		int index = 0;
		A:while(true) {
			int nextMax = frequencies.get(index);
			for(String key : wordFrequencies.keySet()) {
				
				if(tenMostCommon.size() == 10) {
					break A;
				}
				if(wordFrequencies.get(key) == nextMax) {
					tenMostCommon.add(key);
				}
			}
			index++;
		}
		
		return tenMostCommon;
		
	}
	
	//quickSort is all <=
	private static void quickSort(ArrayList<Integer> nums, int left, int right) {
		
		if(left >= right) {
			return;
		}
		
		int pivot = nums.get((left+right)/2);
		int partitionIndex = partitionIndex(nums,left,right,pivot);
		quickSort(nums,left,partitionIndex-1);
		quickSort(nums,partitionIndex,right);
		
	}
	
	private static int partitionIndex(ArrayList<Integer> nums, int left, int right, int pivot) {
		
		while(left <= right) {
			
			while(nums.get(left) > pivot) {
				left++;
			}
			
			while(nums.get(right) < pivot) {
				right--;
			}
			
			if(left <= right) {
				int temp = nums.get(left);
				nums.set(left, nums.get(right));
				nums.set(right, temp);
				left++;
				right--;
			}
			
		}
		
		return left;
		
	}

}

//Python code for this same functionality as you can see it is a lot more simple
/*
# 10 most common words in a file

filename = input("Enter a file name: ")
file = open(filename)

dic = {}

for line in file:
    words = line.split()
    for word in words:
        dic[word] = dic.get(word,0) + 1

if(len(dic) < 10):
    print("There is not even 10 distinct words in the file")
    quit()

# return the answer as a list of tuples sorted in order of frequency with the word as the
# first index of tuple and the frequency as the second index of tuple

# word_frequency = sorted(dic.items(),reverse=True,key=lambda item:item[1])
word_frequency = sorted([(v,k) for (k,v) in dic.items()],reverse=True)
# print(word_frequency[0:10])
*/

