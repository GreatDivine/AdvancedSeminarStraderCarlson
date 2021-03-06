package menus;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	private final int MENU_WIDTH = 200;
	private final int MENU_HEIGHT = 200;
	
	protected JPanel mMenuPanel;
	
	public Menu()
	{
		mMenuPanel = new JPanel();
		mMenuPanel.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
		init();
	}
	
	private void init()
	{
		add(mMenuPanel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
		setVisible(true);
	}

}
