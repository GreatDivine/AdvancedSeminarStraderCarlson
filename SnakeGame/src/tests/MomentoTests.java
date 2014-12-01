package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import game.util.momento.CareTaker;
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
	
	@Test
	public void getMomentoTest()
	{
		Originator o = new Originator();
		GameSaveMomento g = new GameSaveMomento("hello");
		
		assertEquals(o.getStateFromMomento(g), "hello");
		
	}
	
	@Test
	public void testCaretaker() throws IOException
	{
		CareTaker c = new CareTaker("testFile.txt", false);
		
		GameSaveMomento g = new GameSaveMomento("Hello World");
		
		c.add(g);
		
		c.save();
		
		c.load("testFile.txt");
		
		assertEquals(c.getCurMomento().getStateStr(), "Hello World");
	}

}