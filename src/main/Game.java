package main;

import java.awt.Graphics;

import GameStates.GameState;
import GameStates.Menu;
import GameStates.Playing;

public class Game implements Runnable{
	//Global variables
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;

	private Playing playing;
	private Menu menu;
	
	//to be able to change size with out breaking app
	public final static int TILES_DEFAULT_SIZE = 32;
	//scale will control change to entire screen size
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	
	
	public Game() {
		
		initClasses(); //to put all classes in method
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		//game loop should be last
		startGameLoop();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);;
		gameThread.start();
	}
	
	public void update() {

		switch(GameState.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
			System.exit(0);
			break;
		
		}
	}
	public void render(Graphics g) {

		switch(GameState.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);;
			break;
		default:
			break;
		
		}
	}
	
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0; //deltaUpdate
		double deltaF = 0; //deltaFrames
		
		while(true) {

			//for update
			long currentTime = System.nanoTime();
			
			//this will allow it to update instead of reset
			deltaU += (currentTime - previousTime)/timePerUpdate;
			deltaF += (currentTime - previousTime)/timePerFrame;
			
			previousTime = currentTime;
			//update 
			if(deltaU >=1) {
				update();
				updates++;
				deltaU--;
			}
			//render 
			if(deltaF >=1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
		
			
			if(System.currentTimeMillis()- lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames= 0;
				updates= 0;
			}
			
			
		}
		
	}
	
	public void windowFocusLost() {
		if(GameState.state == GameState.PLAYING)
			playing.getPlayer().resetDirBooleans();
	}
	public Menu getMenu() {
		return menu;
	}
	public Playing getPlaying() {
		return playing;
	}
}

