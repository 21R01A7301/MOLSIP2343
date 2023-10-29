// Java Programming intern
// MicrOrbital Labs Internship Works Batch SIP2343
// Task 2 - Word count tool
//Project code - MOLAP623
//Word count application, which reads the contents from the text file, and display the number of words,
//characters etc.
// LinkedIn profile - https://www.linkedin.com/in/aditi-bengani/

import java.util.Scanner;
public class wood_count {
    static final int IN =1;
    static final int OUT =1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();
        System.out.println("Number of words: " +word_count(str));
        System.out.println("Number of numbers: " +count_num(str));
        System.out.println("Number of special characters: " +char_count(str));
    }
    public static int word_count(String str){
        int state = OUT;
        int word_cnt = 0;
        int i=0;
        while(i < str.length()){
            if(str.charAt(i) == ' ' || str.charAt(i) == '\n' || str.charAt(i) == '\t')
                state = OUT;
            else if(state == OUT){
                state = IN;
                word_cnt++;
            }
            i++;
        }
        return word_cnt;
    }
    public static int count_num(String str){
        int num_cnt = 0;
        for(int i=0;i<str.length();i++){
            boolean flag = Character.isDigit(str.charAt(i));
            if(flag)
                num_cnt++;
        }
        return num_cnt;
    }
    public static int char_count(String str){
        int spe_cnt = 0;
        for(int i=0;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i)) &&
               !Character.isLetter(str.charAt(i)) &&
               !Character.isWhitespace(str.charAt(i))){
                spe_cnt++;
            }
        }
        return spe_cnt;
    }
}
