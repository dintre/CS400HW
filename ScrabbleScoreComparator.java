import java.util.Comparator;

public class ScrabbleScoreComparator implements Comparator<String> {
	/*
	 * compares two Strings based on their Scrabble Score
	 * the lower Scrabble score comes first
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String s1, String s2) {
		return computeScrabbleScore(s1) - computeScrabbleScore(s2);
	}
	
	private int computeScrabbleScore(String s) {
		s = s.toLowerCase();
		int result = 0;
		for (int i=0; i < s.length(); i++) {
			if ("aeiounstlr".contains(s.substring(i, i+1))){
				result += 1;
			} else if ("dg".contains(s.substring(i, i+1))){
				result += 2;
			}else if ("bcmp".contains(s.substring(i, i+1))){
				result += 3;
			}else if ("fhvwy".contains(s.substring(i, i+1))){
				result += 4;
			}else if ("k".contains(s.substring(i, i+1))){
				result += 5;
			}else if ("jx".contains(s.substring(i, i+1))){
				result += 8;
			}else if ("qz".contains(s.substring(i, i+1))){
				result += 10;
			}
		}
		return result;
	}
	
	
	
	
	
	
} // class

