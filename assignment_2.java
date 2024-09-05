/*
 *TASK--> When the program is run, the user will first enter a string. After that, the program should ask the user which operation they want to execute on the string. The available operations are
listed below: > Append                             > Split                                                 
              > CountWords                         > MaxRepeatingCharacter
              > Replace                            > Sort
              > isPalindrome                       > Shift 
              > Splice                             > Reverse
*Each method should perform its respective operation based on the user's input.
*Owner-->Pratul_Agarwal
*DATE-->04/09/2024
 */
package assignment_java_programming;
import java.util.Scanner;
public class assignment_2 {
	// method to append string
	public static String appendString(String s,String toAppend)
	{
		char[] result = new char[s.length() + toAppend.length()];
		int i=0;
		for(;i < s.length();i++)
		{
			result[i] = s.charAt(i);
		}
		for (int j = 0; j < toAppend.length(); j++, i++) {
		result[i] = toAppend.charAt(j);
		}
		return new String(result);
	}
	// method to count words
	public static int countWords(String s)
	{
		int count = 0;
		boolean inword = false;
		int endword = s.length() - 1;
		for (int i = 0;i < s.length();i++)
		{
			if (Character.isLetter(s.charAt(i)) && i != endword)
					{
				inword = true;
					}
			else if ( !Character.isLetter(s.charAt(i)) && inword)
			{
				count ++;
				inword = false;
			}
			else if (Character.isLetter(s.charAt(i)) && i == endword)
			{
				count ++;
			}
		}
		return count ;
	}

	// method to replace
public static String replaceChar(String original,char oldchar,char newchar)
	
	{
		char[] result = new char[original.length()];
		
		for(int i = 0;i < original.length();i++)
			
		   {
			if(original.charAt(i) == oldchar)
			{
				result[i] = newchar;
			}
			else 
			{
				result[i] = original.charAt(i);
			}
			}
		return new String(result);
		}
	public static boolean isPalindrome(String str)
	{
		if(str == null || str.length() == 0)
		{
			return true;
		}
		
		for(int i = 0; i <= str.length()/2;i++)
		{
			char start = str.charAt(i);
			char end =str.charAt(str.length() - 1 - i);
		
		if (start != end)
		{
			return false;
		}
		}
		return true;
	}
	
	//method to splice string
    public static String stringSplicer(String str ,int start,int end )
    {
    	int newlen = str.length() - end ;
    	char[] result = new char [newlen];
    	int in = 0;
    	for (int i = 0 ;i < start;i++ )
    	{
    		result[in++] = str.charAt(i);
    	}
    	for (int i = start + end ;i < str.length();i++)
    	{
    		result[in++] = str.charAt(i);
    		
    	}
    		return new String(result);
      }
    
    // method to split string
    public static String[] stringSplitter(String str,String limit)
    {
    	return str.split(limit);    	
    }
    public static char maxRepeat(String str)
    {
    	int maxcount = 0;
    	char maxChar = '\0';
    	for (int i = 0;i < str.length();i++)
    	{
    		 char currentChar = str.charAt(i);
             int count = 0;
             for (int j = 0; j < str.length(); j++) {
                 if (str.charAt(j) == currentChar) {
                     count++;
                 }
             }
             if (count > maxcount) {
                maxcount = count;
                 maxChar = currentChar;
             }
         }
         return maxChar;
     }
    
    // method to sort string
    public static String stringSorting(String str)
    {
    	char temp;
    	char string[] = str.toCharArray();
    	for(int i = 0 ;i < string.length; i++)
    	{
    		for(int j = i + 1;j < string.length; j++ )
    		{
    			if(string[i]>string[j])
    			{
    				temp = string[i];
    				string[i] = string[j];
    				string[j] = temp;
     			}
    		}
    	}
    	return new String(string);
    }
    
    // method to shift string
    public static String stringShifter(String str, int shift) {
        int length = str.length();
        shift = shift % length;      
        char[] shifted = new char[length];
        for (int i = 0; i < length; i++) {
            int newPosition = (i + shift) % length;
            shifted[newPosition] = str.charAt(i);
        }
        return new String(shifted);
    }
	   public static String stringReverse(String str) {
	        char[] result = new char[str.length()];
	        for (int i = 0; i < str.length(); i++) {
	            result[i] = str.charAt(str.length() - 1 - i);
	        }
	        return new String(result);
	    }
	   
public static void main(String[] args) {
		
  Scanner input =new Scanner(System.in);
  String response;
  do
  {
  System.out.println(Constant2.INPUTSTR);
  String str = input.nextLine();
  System.out.println(Constant2.INDEX);
  System.out.println(Constant2.CHOICE); 
 int choice = input.nextInt();
 input.nextLine();
 
 switch (choice)
 {
 case 1:
	 System.out.println(Constant2.APPEND);
     String toAppend = input.nextLine();
     System.out.println("Appended String: " + appendString(str, toAppend));
	 break;
 case 2:
	 System.out.println("Word count: " + countWords(str));
	 break;
 case 3:
	   System.out.println(Constant2.REPLACE);
       char oldChar = input.nextLine().charAt(0);
       System.out.println(Constant2.NEW);
       char newChar = input.nextLine().charAt(0);
       System.out.println("New String " + replaceChar(str, oldChar, newChar));
	 break;
 case 4:
	  System.out.println(Constant2.PALINDROME + isPalindrome(str));
	 break;
 case 5:
     System.out.println(Constant2.SPLICE);
     int start = input.nextInt();
     System.out.println(Constant2.REMOVE);
     int length = input.nextInt();
     input.nextLine(); 
     System.out.println("The spliced string :" + stringSplicer(str, start, length));

	 break;
 case 6:
	 
	 String delimiter = " ";
	    String[] words = stringSplitter(str, delimiter);

	    System.out.println("Original String: " + str);
	    System.out.println("Split Strings:");
	    for (String word : words) {
	        System.out.println(word);
	    }
	 
	 break;
 case 7:
	  System.out.println(Constant2.MAXSTR + maxRepeat(str));
	 break;
 case 8:
	 System.out.println(Constant2.SORTEDSTR + stringSorting(str));
	 break;
 case 9:

     System.out.println(Constant2.POSISHIFT);
     int shift = input.nextInt();
     String shiftedString = stringShifter(str, shift);
     System.out.println(Constant2.SHIFTEDSTR + shiftedString);
     input.nextLine();
	 break;
 case 10:
	 System.out.println("Reversed string: " + stringReverse(str));
	 break;
 default:
		 System.out.println("Invalid choice");
		 break;
 }
 System.out.println( Constant2.ASKAGAIN);
 
 response = input.nextLine();
  }
  while(response.equalsIgnoreCase("yes"));
 input.close();

	}
}
