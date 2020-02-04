package cp;

public class MeasureLambda
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
	
	public static void main( String[] args )
	{
		Utils.doAndMeasure( () -> {
			System.out.println( "Sum: " + sum( 5 ) );
		} );
		Utils.doAndMeasure( () -> {
			System.out.println( "Mult: " + mult( 5 ) );
		} );
	}
}
