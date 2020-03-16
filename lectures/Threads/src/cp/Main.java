package cp;

public class Main
{
	public static void main( String[] args )
	{
//		Utils.benchmark( () -> SpinlockMap2T.main() );
//		Utils.benchmark( () -> SynchronizedMap2T.main() );
		Utils.benchmark( SynchronizedMap::main );
	}
}
