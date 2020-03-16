package cp;

public class FirstThread
{
	public static void main()
	{
		Thread t1 = new Thread( () -> System.out.println( "Hello from t1" ) );
		t1.start();
		try {
			t1.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}