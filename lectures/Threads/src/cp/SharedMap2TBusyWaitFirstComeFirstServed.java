package cp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// This is an example of broken code, not safe!
public class SharedMap2TBusyWaitFirstComeFirstServed
{
	private static class Counter {
		private int c = 0;
	}
	
	private static class Guard {
		private volatile boolean locked = false;
		private volatile boolean t1Done = false;
		private volatile boolean t2Done = false;
	}
	
	public static void main()
	{
		Map< String, Integer > results = new HashMap<>();
		Counter counter = new Counter();
		Guard guard = new Guard();
		
		Thread t1 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text1.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						while( guard.locked && !guard.t2Done ) {}
						guard.locked = true;
						results.merge( word, 1, Integer::sum );
						counter.c++;
						guard.locked = false;
					} );
				guard.t1Done = true;
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text2.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						while( guard.locked && !guard.t1Done ) {}
						guard.locked = true;
						results.merge( word, 1, Integer::sum );
						counter.c++;
						guard.locked = false;
					} );
				guard.t2Done = true;
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
//		results.forEach( (key, value) -> System.out.println( key + ": " + value ) );
//		System.out.println( "Total: " + counter.c );
	}
}
