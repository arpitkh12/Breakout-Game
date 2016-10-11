package com.p532.model;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.p532.constants.Constants;

public class Paddle implements Constants {
	private int paddleY = Constants.paddleY, paddleX = Constants.paddleX;
	private Rectangle paddle;
	private KeyEvent e;

	public Paddle() {
		setPaddle(new Rectangle(paddleX, paddleY, 80, 10));
	}

	public void movePaddle() {
		int keyCode = getE().getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			if (getPaddle().x > 0) {
				getPaddle().x -= 20;
			}
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (getPaddle().x + getPaddle().width < gamePanelWidth) {
				getPaddle().x += 20;
			}

		}
	}

	public KeyEvent getE() {
		return e;
	}

	public void setE(KeyEvent e) {
		this.e = e;
	}

	public Rectangle getPaddle() {
		return paddle;
	}

	public void setPaddle(Rectangle paddle) {
		this.paddle = paddle;
	}
}
