/**
 * Created by Steven A. Sarasin on 9/15/2014.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GameOfLifeTest
		extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public GameOfLifeTest(String testName)
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( GameOfLifeTest.class );
	}

	/**
	 * Rigorous Test :-)
	 */
	public void testApp()
	{
		assertTrue( true );
	}
}
