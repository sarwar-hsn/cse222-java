
public class Ques1 {
	
	
	/**
	 * This function compares two string two find if they are equal 
	 * @param text is of type string
	 * @param target is of type string
	 * @return returns true if they are equal else false
	 */
	private static boolean exactMatch(String text, String target)
	{
	    if (text.length() !=  target.length())
	        return false;
	    if (target.length() == 0)
	        return true;
	  
	    if (text.charAt(0) == target.charAt(0))
	        return exactMatch(text.substring(1),target.substring(1));
	    return false;
	}
	
	
	/**
	 * finds ith occurrence of a sub-string in a given string, this a private helper method
	 * @param string is the text where we will look for substring
	 * @param index is of integer it's initial value is zero
	 * @param target is the targeted string
	 * @param count is to keep track of occurrence
	 * @param ithIndex is the occurrence we are looking for
	 * @return return -1 if not found else index
	 */
	private static int findSubstring(String string,int index , String target,int count,int ithIndex) {
		if(index > (string.length()-target.length())) {
			return -1;
		}
		if(exactMatch(string.substring(index, index+target.length()), target)){
			count+=1;
			if(ithIndex == count) return index;
		}
		return findSubstring(string, index+1, target,count,ithIndex);
	}
	
	
	/**
	 * 
	 * @param text is of type string
	 * @param target is of type string
	 * @param occurrenceValue is of type integer
	 * @return is index value of ith occurrence if found , else -1
	 */
	public static int findSubstring(String text,String target,int occurrenceValue) {
		return findSubstring(text,0,target,0,occurrenceValue);
	}

}
