package tests;

import static org.junit.Assert.assertEquals;
import game.util.momento.GameSaveMomento;
import game.util.momento.Originator;

import org.junit.Test;

public class MomentoTests {
	
	@Test
	public void saveStateToMomentoTest()
	{
		Originator o = new Originator();
		o.setState("state");
		GameSaveMomento g = o.saveStateToMomento();
		
		assertEquals(g.getStateStr(), "state");
	}

}