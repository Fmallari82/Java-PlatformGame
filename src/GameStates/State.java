package GameStates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {
	
	protected Game game;
	
	//super class for all game states
	public State(Game game) {
		this.game = game;
	}
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(),e.getY());
	}
	
	public Game getGame() {
		return game;
	}
	
}
