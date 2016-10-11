package com.p532.View;

import java.awt.Color;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CanvasView {
	public GamePanel gamePanel;
	public TimerPanel timerPanel;
	JFrame gameWindow = new JFrame("Breakout");
	private JPanel mainPanel = new JPanel();

	public CanvasView() {

		gameWindow.setSize(900, 900);
		gamePanel = new GamePanel();
		gameWindow.setBackground(new Color(41, 182, 255));
		timerPanel = new TimerPanel();
		/* Game view border color */
		mainPanel.setBackground(new Color(20, 22, 90));
		mainPanel.add(timerPanel);
		mainPanel.add(gamePanel);
		gameWindow.add(mainPanel);
		gameWindow.pack();
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(true);
		gameWindow.setFocusable(true);
		gameWindow.setFocusTraversalKeysEnabled(false);
		gameWindow.setAlwaysOnTop(true);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);

	}

	public void addPaddleMovement(KeyListener key) {
		gameWindow.addKeyListener(key);
	}

}
