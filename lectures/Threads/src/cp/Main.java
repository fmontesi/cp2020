package cp;

public class Main
{
	public static void main( String[] args )
	{
//		Utils.doAndMeasure( Walk::main );
//		Utils.doAndMeasure( WalkBlockingDeque::main );
		Utils.doAndMeasure( WalkExecutor::main );
	}
}
