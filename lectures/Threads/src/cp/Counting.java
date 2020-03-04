package cp;

public class Counting
{
	private static class Counter {
		private int c = 0;
	}
	
	public static void main()
	{
		Counter counter = new Counter();
		
		Thread t1 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				synchronized( counter ) {
					counter.c++;
				}
			}
		});
		
		Thread t2 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				synchronized( counter ) {
					counter.c++;
				}
			}
		});
		
		Thread t3 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				synchronized( counter ) {
					counter.c++;
				}
			}
		});
		
		t1.start();
		// t1 finishes here?
		t2.start();
		t3.start();
		// t2 finishes here?
		// t1 finishes here?
		// t2 finishes here?
		try {
			// t2 finishes here?
			// t1 finishes here?
			// t2 finishes here?
			t1.join();
			// t1 has surely finished here :)
			// t2 finishes here?
			t2.join();
			t3.join();
			// t2 has surely finished here
			System.out.println( counter.c );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
