package package1;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

public class StringManipulator {

public static String findLongestWord(String sentence) {
        String longestWord = "";
        String currentWord = "";
        
        StringTokenizer st=new StringTokenizer(sentence);
		int n=st.countTokens();
		String longest="";
		for(int i=0;i<n;i++) {
		String now=st.nextToken();
		if(longest.length()<now.length()) {
			longest=now;
		}
		}
		return longest;

       
    }

    public static int countVowels(String sentence) {
        int vowelCount = 0;
        String vowels = "aeiouAEIOU";
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (vowels.indexOf(c) != -1) {
                vowelCount++;
            }
        }
        return vowelCount;
    }

     public static String reverseSentence(String sentence) {
        String reversedSentence = "";
        
        for (int i = sentence.length() - 1; i >= 0; i--) {
            reversedSentence += sentence.charAt(i);
        }

        return reversedSentence;
    }

    public static void main(String[] args) {
        String sentence = "Isha Jaswal";

        String longestWord = findLongestWord(sentence);
        int vowelCount = countVowels(sentence);
        String reversedSentence = reverseSentence(sentence);

        
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Number of Vowels: " + vowelCount);
        System.out.println("Reversed Sentence: " + reversedSentence);
    }
}
