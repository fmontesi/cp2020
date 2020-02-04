package cp;

public class MeasureDedicatedClasses
{
	public static int sum( int n )
	{
		if ( n < 1 ) {
			return 0;
		}
		return n + sum( n - 1 ) + sum( n - 1 );
	}
	
	public static long mult( long n )
	{
		if ( n < 1 ) {
			return 1;
		}
		return n * mult( n - 1 ) * mult( n - 1 );
	}
	
	private static class Sum5Runnable implements Runnable {
		public void run()
		{
			System.out.println( "Sum: " + sum( 5 ) );
		}
	}
	
	private static class Mult5Runnable implements Runnable {
		public void run()
		{
			System.out.println( "Mult: " + mult( 5 ) );
		}
	}
	
	public static void main( String[] args )
	{
		Utils.doAndMeasure( new Sum5Runnable() );
		Utils.doAndMeasure( new Mult5Runnable() );
	}
}
