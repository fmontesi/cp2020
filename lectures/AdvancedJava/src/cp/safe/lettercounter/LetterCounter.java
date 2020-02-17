package cp.safe.lettercounter;

import java.util.HashMap;
import java.util.Map;

public class LetterCounter
{
	public static void main()
	{
		final String text = "Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there 1212123123 Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there 1212123123 Hello there Hello thereHello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there 1212123123 Hello there Hello thereHello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there Hello there 1212123123 Hello there Hello there";
		
		Map<Character, Integer> occurrences = new HashMap<>();
		
//		for( int i = 0; i < text.length(); i++ ) {
//			final char c = text.charAt( i );
//			if ( occurrences.containsKey( c ) ) {
//				final int n = occurrences.get( c );
//				occurrences.put( c, n + 1 );
//			} else {
//				occurrences.put( c, 1 );
//			}
//		}
		
		// Does exactly the same as the loop above
		for( int i = 0; i < text.length(); i++ ) {
			final char c = text.charAt( i );
			occurrences.merge( c, 1, (currValue, value) -> currValue + value );
		}
		
		for( Character c : occurrences.keySet() ) {
			System.out.println( c + ": " + occurrences.get( c ) );
		}
	}
}
