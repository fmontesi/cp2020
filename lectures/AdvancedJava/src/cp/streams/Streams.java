package cp.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Streams
{
	public static void main()
	{
		List<String> names = List.of( "Kim", "Inge", "Lasse", "Joan", "Christian", "Kasper", "Annette", "Susanne", "Caroline", "Holger Danske", "Carlsberg", "Albani", "Faxe", "Coca Cola", "Pepsi", "Odense", "Royal Beer", "Skovlyst", "Svenninge" );
		
		printNamesStartingWithK( names.stream() );
		
		// Print the count of names that start with a K
		System.out.println(
			names.stream()
				.filter( s -> s.startsWith( "K" ) )
				.count()
		);

		names.stream()
			.filter( s -> s.length() < 5 ) // Only names shorter than 5 characters
			.sorted( (s1, s2) -> s1.compareTo( s2 ) ) // Sort the stream
			.forEach( System.out::println ); // Print each element
		
		// Print the concatenation of all names
		System.out.println( names.stream()
			.reduce( "", (s1, s2) -> s1 + " " + s2 ) );

		// Print the length of each name
		names.stream()
			.map( s -> s.length() )
			.forEach( System.out::println );
		
		names.stream()
			.map( s -> s.toUpperCase() ) // Transform each name into its uppercase version
			.forEach( System.out::println );
		
		System.out.println( names.stream()
			.map( s -> s.toLowerCase() ) // Transform each name to lowercase
			.filter( s -> s.contains( "i" ) ) // Only names that start with i
			.count() ); // Count
		
		// We gotta remember to close the file, will see how to do that in week 10
		try {
			System.out.println(
				Files.lines( Paths.get( "text1.txt" ) )
					.map( line -> line.split( " " ) )
					.map( words -> {
						int n = 0;
						for( String word : words ) {
							if ( word.equals( "et" ) ) {
								n++;
							}
						}
						return n;
					} )
					.reduce( 0, (n1, n2) -> n1 + n2 )
			);
		
			System.out.println(
				Files.lines( Paths.get( "text1.txt" ) )
					.flatMap( line -> Stream.of( line.split( " " ) ) )
					.map( word -> word.equals( "et" ) ? 1 : 0 )
					.reduce( 0, (n1, n2) -> n1 + n2 )
			);
			
			System.out.println(
				Files.lines( Paths.get( "text1.txt" ) )
					.flatMap( line -> Stream.of( line.split( " " ) ) )
					.filter( word -> word.equals( "et" ) )
					.count()
			);
			
			Map< String, Integer > results = new HashMap<>();
			Files.lines( Paths.get( "text1.txt" ) )
				.flatMap( s -> Stream.of( s.split( " " ) ) )
				.forEach( word -> results.merge( word, 1, Integer::sum ) );
			results.forEach( (key, value) -> System.out.println( key + ": " + value ) );
			
			Files.lines( Paths.get( "text1.txt" ) )
				.flatMap( s -> Stream.of( s.split( " " ) ) )
				.map( s -> {
					HashMap< String, Integer > m = new HashMap<>();
					m.put( s, 1 );
					return m;
				})
				.reduce( new HashMap<String, Integer>(), Streams::merge )
				.forEach( (key, value) -> System.out.println( key + ": " + value ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private static HashMap< String, Integer > merge( HashMap< String, Integer > m1, HashMap< String, Integer > m2 )
	{
		HashMap< String, Integer > result = new HashMap<>();
		result.putAll( m1 );
		m2.forEach( (key, value) -> result.merge( key, value, Integer::sum ) );
		return result;
	}
	
	private static void printNamesStartingWithK( Stream<String> stream )
	{
		stream
			.filter( name -> name.startsWith( "K" ) ) // Now we have a stream with names starting with K
			.forEach( System.out::println ); // Print every element
	}
	
	private static void printARandomNumber()
	{
		Optional<Integer> result =
			Stream.generate( () -> new Random().nextInt() ) // Infinite stream of random numbers
			.filter( n -> n > 0 ) // all numbers bigger than 0
			.filter( n -> n > 100_000 ) // all numbers bigger than 100_00
			.findAny(); // return the first number found in the stream
	
		if ( result.isPresent() ) {
			System.out.println( result.get() );
		}
	}
}
