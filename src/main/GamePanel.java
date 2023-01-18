package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel{
	//Global variables
	private MouseInputs mouseInputs;
	private Game game;

	
	public GamePanel(Game game) {
		this.game = game;
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}


	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println("size: " + GAME_WIDTH +" X "+ GAME_HEIGHT);
	}

	public void updateGame() {

	}
	
	//paintComponent = magic method - we never call
	//class Graphics - allows us to draw on JPanel (like a paint brush)
	//we need paintComponent and a graphics object as an input to draw
	public void paintComponent(Graphics g) {
		// call superclass of JPanel - cleaning the surface before we can paint
		super.paintComponent(g);
		game.render(g);

	}
	
	public Game getGame() {
		return game;
	}
	


}
