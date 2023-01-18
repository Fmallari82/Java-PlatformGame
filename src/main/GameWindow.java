package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();

		// to exit window on close(close application)
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.pack();
		// Should be on bottom
		jframe.setVisible(true);
		
		//if you tab out of the game window
		jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();

			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}
}