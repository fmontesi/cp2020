package cp;

public class SafeCounter
{
	private static class Counter {
		private Integer c = 0;
		
		public void increase()
		{
			synchronized( c ) {
				c++;
			}
		}
		
		public int get()
		{
			synchronized( c ) {
				return c;
			}
		}
	}
	
	public static void main()
	{
		Counter counter = new Counter();
		
		Thread t1 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				counter.increase();
			}
		});
		
		Thread t2 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				counter.increase();
			}
		});
		
		Thread t3 = new Thread( () -> {
			int i = 0;
			while( i++ < 1000 ) {
				counter.increase();
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
			System.out.println( counter.get() );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
